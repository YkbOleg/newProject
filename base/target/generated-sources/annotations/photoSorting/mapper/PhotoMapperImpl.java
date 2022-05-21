package photoSorting.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import photoSorting.dto.PhotoDto;
import photoSorting.model.Photo;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-15T17:38:55+0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class PhotoMapperImpl implements PhotoMapper {

    @Override
    public PhotoDto toDto(Photo entity) {
        if ( entity == null ) {
            return null;
        }

        PhotoDto photoDto = new PhotoDto();

        photoDto.setId( entity.getId() );
        photoDto.setName( entity.getName() );
        photoDto.setCreate( entity.getCreate() );
        photoDto.setSize( entity.getSize() );
        photoDto.setLocation( entity.getLocation() );

        return photoDto;
    }

    @Override
    public Photo toEntity(PhotoDto dto) {
        if ( dto == null ) {
            return null;
        }

        Photo photo = new Photo();

        photo.setId( dto.getId() );
        photo.setName( dto.getName() );
        photo.setCreate( dto.getCreate() );
        photo.setSize( dto.getSize() );
        photo.setLocation( dto.getLocation() );

        return photo;
    }
}
