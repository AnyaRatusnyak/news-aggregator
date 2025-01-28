package projectnewsaggregator.mapper;

import org.mapstruct.Mapper;
import projectnewsaggregator.config.MapperConfig;
import projectnewsaggregator.dto.SourceDto;
import projectnewsaggregator.dto.UserRegistrationRequestDto;
import projectnewsaggregator.model.Source;
import projectnewsaggregator.model.User;

@Mapper(config = MapperConfig.class)
public interface SourceMapper {
    SourceDto toDto(Source source);
}
