package projectnewsaggregator.service;

import org.springframework.data.domain.Pageable;
import projectnewsaggregator.dto.ArticleWithCategoryDto;


import java.util.List;

public interface ArticleService {
    void fetchAndSaveArticles(String query);

    void scheduleFetching();

    List<ArticleWithCategoryDto>findByCategories(List<String> categories, Pageable pageable);

    void fetchAndSaveTopHeadlinesArticlesByCategory(String category);

}
