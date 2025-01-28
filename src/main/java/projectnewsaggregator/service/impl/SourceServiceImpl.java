package projectnewsaggregator.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import projectnewsaggregator.dto.SourceDto;
import projectnewsaggregator.mapper.SourceMapper;
import projectnewsaggregator.repository.SourceRepository;
import projectnewsaggregator.service.SourceService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SourceServiceImpl implements SourceService {
    private final SourceRepository sourceRepository;
    private final SourceMapper sourceMapper;
    @Override
    public List<SourceDto> findAll(Pageable pageable) {
        return sourceRepository.findAll().stream().map(sourceMapper::toDto).toList();
    }
}
