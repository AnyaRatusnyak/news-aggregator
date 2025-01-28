package projectnewsaggregator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projectnewsaggregator.model.ArticleIndex;
import projectnewsaggregator.service.ElasticsearchService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/search")
@CrossOrigin(origins = "http://localhost:5173")
public class SearchController {
    private final ElasticsearchService elasticsearchService;

    @GetMapping("/{id}")
    public ArticleIndex searchArticleById(@PathVariable String id) {
        return elasticsearchService.searchById(id);
    }

    @GetMapping
    public ResponseEntity<List<ArticleIndex>> searchArticles(
            @RequestParam String keywords,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<ArticleIndex> articles = elasticsearchService.searchByKeywords(keywords, page, size);
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/latest")
    public ResponseEntity<List<ArticleIndex>> getLatestArticles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<ArticleIndex> latestArticles = elasticsearchService.getLatestArticles(page, size);
        return ResponseEntity.ok(latestArticles);
    }
}
