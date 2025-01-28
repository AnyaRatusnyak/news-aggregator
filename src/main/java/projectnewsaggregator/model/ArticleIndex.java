package projectnewsaggregator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Data
@Document(indexName = "articles")
public class ArticleIndex {
    @Id
    @Field(type = FieldType.Keyword)
    private String id;
    @Field(type = FieldType.Text, analyzer = "standard")
    private String author;
    @Field(type = FieldType.Text, analyzer = "standard")
    private String title;
    @Field(type = FieldType.Text, analyzer = "standard")
    private String description;
    private String url;
    private String urlToImage;
    @Field(type = FieldType.Date, format = DateFormat.date_time)
    private Date publishedAt;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String content;

    public ArticleIndex(String id, String author, String title, String description, String url, String urlToImage, Date publishedAt, String content) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
    }

    public ArticleIndex() {
    }
}
