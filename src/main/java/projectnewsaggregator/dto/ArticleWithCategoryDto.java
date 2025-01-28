package projectnewsaggregator.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleWithCategoryDto {
    private String id;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private Date publishedAt;
    private String content;
    private String category;
}
