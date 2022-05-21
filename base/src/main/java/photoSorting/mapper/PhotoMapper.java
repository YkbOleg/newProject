package photoSorting.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import photoSorting.dto.PhotoDto;
import photoSorting.model.Photo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper
public interface PhotoMapper {

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "name", source = "entity.name")
    @Mapping(target = "create", source = "entity.create")
    @Mapping(target = "size", source = "entity.size")
    @Mapping(target = "location", source = "entity.location")
    PhotoDto toDto(Photo entity);

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "create", source = "dto.create")
    @Mapping(target = "size", source = "dto.size")
    @Mapping(target = "location", source = "dto.location")
    Photo toEntity(PhotoDto dto);

    default List<PhotoDto> toDtos(List<Photo> entities){
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }
}
