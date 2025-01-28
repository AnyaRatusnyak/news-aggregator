package projectnewsaggregator.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.Nullable;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@Document(collection = "articles")
public class Article {
    @Id
    private String id;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private Date publishedAt;
    private String content;
    @Nullable
    private String category;



    public Article(String name, String author, String title, String description, String url, String urlToImage, Date publishedAt, String content) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
        this.category = null;
    }

    public Article(String name, String author, String title, String description, String url, String urlToImage, Date publishedAt, String content,String category) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
        this.category = category;
    }
}
