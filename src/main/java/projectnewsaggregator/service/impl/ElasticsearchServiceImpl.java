package projectnewsaggregator.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.search.Hit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projectnewsaggregator.exception.EntityNotFoundException;
import projectnewsaggregator.model.Article;
import projectnewsaggregator.model.ArticleIndex;
import projectnewsaggregator.repository.ArticleIndexRepository;
import projectnewsaggregator.service.ElasticsearchService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ElasticsearchServiceImpl implements ElasticsearchService {
    private final ElasticsearchClient elasticsearchClient;
    private final ArticleIndexRepository repository;

    @Override
    public void save(Article article) {
        ArticleIndex articleIndex = new ArticleIndex(
                article.getId(),
                article.getAuthor(),
                article.getTitle(),
                article.getDescription(),
                article.getUrl(),
                article.getUrlToImage(),
                article.getPublishedAt(),
                article.getContent()
        );
        repository.save(articleIndex);
    }

    @Override
    public ArticleIndex searchById(String id) {
        return repository.findById(id).orElseThrow(()-> new EntityNotFoundException("Can`t find a article with id: " + id));
    }

    @Override
    public List<ArticleIndex> searchByKeywords(String keywords, int page, int size) {
        try {
            int from = page * size;
            var searchResponse = elasticsearchClient.search(s -> s
                            .index("articles")
                            .query(q -> q
                                    .multiMatch(m -> m
                                            .fields("title", "description", "content")
                                            .query(keywords)
                                            .fuzziness("AUTO")
                                    )
                            )
                    .from(from)
                    .size(size),
            ArticleIndex.class
            );

            return searchResponse.hits().hits().stream()
                    .map(Hit::source)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Failed to search articles by keywords in Elasticsearch", e);
        }
    }

    @Override
    public List<ArticleIndex> getLatestArticles(int page, int size) {
        try {
            int from = page * size;
            var searchResponse = elasticsearchClient.search(s -> s
                            .index("articles")
                            .query(q -> q.matchAll(m -> m))
                            .sort(sort -> sort.field(f -> f
                                    .field("publishedAt")
                                    .order(co.elastic.clients.elasticsearch._types.SortOrder.Desc)
                            ))
                            .from(from)
                            .size(size),
                    ArticleIndex.class
            );

            return searchResponse.hits().hits().stream()
                    .map(Hit::source)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Failed to fetch latest articles from Elasticsearch", e);
        }
    }
}
