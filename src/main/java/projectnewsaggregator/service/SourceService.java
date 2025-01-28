package projectnewsaggregator.service;

import org.springframework.data.domain.Pageable;
import projectnewsaggregator.dto.SourceDto;

import java.util.List;

public interface SourceService {
    List<SourceDto> findAll(Pageable pageable);
}
