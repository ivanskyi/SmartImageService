package mapper;

import dto.ImageDto;
import model.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface ImageMapper {

    @Mapping(source = "name", target = "name")
    ImageDto toDto(Image image);
}
