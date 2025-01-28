package projectnewsaggregator.service;

import projectnewsaggregator.model.Article;
import projectnewsaggregator.model.ArticleIndex;

import java.util.List;

public interface ElasticsearchService {
    void save (Article article);
    ArticleIndex searchById(String id);
    List<ArticleIndex> searchByKeywords(String keywords, int page, int size);
    List<ArticleIndex> getLatestArticles(int page, int size);

}
