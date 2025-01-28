package projectnewsaggregator.mapper;

import org.mapstruct.Mapper;
import projectnewsaggregator.config.MapperConfig;
import projectnewsaggregator.dto.UserPreferencesDto;
import projectnewsaggregator.dto.UserRegistrationRequestDto;
import projectnewsaggregator.dto.UserResponseDto;
import projectnewsaggregator.model.User;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toDto(User user);

    User toModel(UserRegistrationRequestDto request);

    UserPreferencesDto preferencesToDto (User user);
}
