package projectnewsaggregator.dto;

import lombok.Data;

@Data
public class SourceDto {
    private String id;
    private String name;
    private String description;
    private String url;
    private String category;
    private String language;
    private String country;
}
