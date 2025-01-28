package projectnewsaggregator.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import projectnewsaggregator.model.ArticleIndex;

@Repository
public interface ArticleIndexRepository extends ElasticsearchRepository<ArticleIndex, String> {
}
