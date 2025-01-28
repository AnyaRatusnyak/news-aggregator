package projectnewsaggregator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import projectnewsaggregator.model.Source;

public interface SourceRepository extends MongoRepository<Source, String> {
}
