package projectnewsaggregator.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import projectnewsaggregator.model.Article;

@Repository
public interface ArticleRepository extends MongoRepository<Article, String> {
    List<Article> findByCategoryIn(List<String> categories, Pageable pageable);
}
