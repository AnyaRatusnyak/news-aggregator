package projectnewsaggregator.service.impl;

import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.request.EverythingRequest;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import projectnewsaggregator.dto.ArticleWithCategoryDto;
import projectnewsaggregator.mapper.CategoryMapper;
import projectnewsaggregator.model.Article;
import projectnewsaggregator.repository.ArticleRepository;
import projectnewsaggregator.service.ArticleService;
import projectnewsaggregator.service.ElasticsearchService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final ElasticsearchService elasticsearchService;
    private final CategoryMapper categoryMapper;
    private final NewsApiClient newsApiClient = new NewsApiClient("${NEWS_API_KEY}");
    private final LocalDateTime lastFetchTime = LocalDateTime.now().minusHours(1);
    private static final String query = "politics AND economy AND business " +
            "AND science AND technology AND culture AND sport AND society AND nature AND ecology AND entertainment";

    @Override
    public void fetchAndSaveArticles(String query) {
        newsApiClient.getEverything(
                new EverythingRequest.Builder().q(query).pageSize(100).build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        List<Article> articles = response.getArticles().stream()
                                .map(article -> {
                                    Date publishedDate = null;
                                    try {
                                        if (article.getPublishedAt() != null) {
                                            Instant instant = Instant.parse(article.getPublishedAt());
                                            publishedDate = Date.from(instant);
                                        }
                                    } catch (DateTimeParseException e) {
                                        throw new IllegalArgumentException("Invalid date format", e);
                                    }

                                    return new Article(
                                            article.getSource().getName(),
                                            article.getAuthor(),
                                            article.getTitle(),
                                            article.getDescription(),
                                            article.getUrl(),
                                            article.getUrlToImage(),
                                            publishedDate,
                                            article.getContent()
                                    );
                                })
                                .collect(Collectors.toList());
                        List<Article> savedArticles = articleRepository.saveAll(articles);
                        savedArticles.forEach(elasticsearchService::save);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.err.println("Failed to fetch articles: " + throwable.getMessage());
                    }
                }
        );
    }

    @Override
    public List<ArticleWithCategoryDto> findByCategories(List<String> categories, Pageable pageable) {
        return articleRepository.findByCategoryIn(categories, pageable)
                .stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public void fetchAndSaveTopHeadlinesArticlesByCategory(String category) {
        newsApiClient.getTopHeadlines(
                new TopHeadlinesRequest.Builder()
                        .q(category)
                        .language("en")
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        List<Article> articles = response.getArticles().stream()
                                .map(article -> {
                                    Date publishedDate = null;
                                    try {
                                        if (article.getPublishedAt() != null) {
                                            Instant instant = Instant.parse(article.getPublishedAt());
                                            publishedDate = Date.from(instant);
                                        }
                                    } catch (DateTimeParseException e) {
                                        throw new IllegalArgumentException("Invalid date format", e);
                                    }

                                    return new Article(
                                            article.getSource().getName(),
                                            article.getAuthor(),
                                            article.getTitle(),
                                            article.getDescription(),
                                            article.getUrl(),
                                            article.getUrlToImage(),
                                            publishedDate,
                                            article.getContent(),
                                            category
                                    );
                                })
                                .collect(Collectors.toList());
                        List<Article> savedArticles = articleRepository.saveAll(articles);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                    }
                }
        );
    }

    @Scheduled(fixedRate = 86400000)
    public void scheduleFetching() {
        try {
            fetchAndSaveArticles(query);
            fetchAndSaveTopHeadlinesArticlesByCategory("business");
            fetchAndSaveTopHeadlinesArticlesByCategory("entertainment");
            fetchAndSaveTopHeadlinesArticlesByCategory("general");
            fetchAndSaveTopHeadlinesArticlesByCategory("health");
            fetchAndSaveTopHeadlinesArticlesByCategory("science");
            fetchAndSaveTopHeadlinesArticlesByCategory("sports");
            fetchAndSaveTopHeadlinesArticlesByCategory("technology");
            System.out.println("Scheduled fetching executed successfully!");
        } catch (Exception e) {
            System.err.println("Error during scheduled fetching: " + e.getMessage());
        }
    }
}
