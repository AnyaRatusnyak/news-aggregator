package projectnewsaggregator.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserPreferencesDto {
    private List<String> preferences;
}
