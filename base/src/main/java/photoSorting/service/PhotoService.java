package photoSorting.service;

import photoSorting.dto.PhotoDto;

import java.util.List;

public interface PhotoService {

    List<PhotoDto> findYear(Integer year);
    List<PhotoDto> allPhoto();
    void deleteById(Long photoId);
}
