package projectnewsaggregator.dto;

import lombok.Data;

import java.util.List;
@Data
public class UpdatePreferencesRequestDto {
    private List<String> preferences;
}
