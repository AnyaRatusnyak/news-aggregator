package projectnewsaggregator.mapper;

import org.mapstruct.Mapper;
import projectnewsaggregator.config.MapperConfig;
import projectnewsaggregator.dto.ArticleWithCategoryDto;
import projectnewsaggregator.model.Article;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    ArticleWithCategoryDto toDto(Article article);
}
