package projectnewsaggregator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import projectnewsaggregator.dto.ArticleWithCategoryDto;
import projectnewsaggregator.service.ArticleService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping(value = "/api/articles")
public class ArticleController {

    private final ArticleService articleService;
    private static final String query = "politics AND economy AND business " +
     "AND science AND technology AND culture AND sport AND society AND nature AND ecology AND entertainment";

    @GetMapping("/fetch-news")
    public String fetchNews() {
        articleService.fetchAndSaveArticles("query" );
        return "News fetched and saved!";
    }

    @GetMapping("/category")
    public List<ArticleWithCategoryDto> getArticlesByCategories(@RequestParam List<String> categories, Pageable pageable) {
        return articleService.findByCategories(categories, pageable);
    }
}
