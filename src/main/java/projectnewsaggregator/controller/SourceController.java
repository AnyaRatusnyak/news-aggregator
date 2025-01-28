package projectnewsaggregator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projectnewsaggregator.dto.SourceDto;
import projectnewsaggregator.service.SourceService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/sources")
@CrossOrigin(origins = "http://localhost:5173")
public class SourceController {
    private final SourceService sourceService;

    @GetMapping
    public List<SourceDto> getAll(Pageable pageable) {
        return sourceService.findAll(pageable);
    }
}
