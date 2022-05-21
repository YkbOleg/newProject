package photoSorting.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import photoSorting.dto.PhotoDto;
import photoSorting.mapper.PhotoMapper;
import photoSorting.repository.PhotoRepository;
import photoSorting.service.PhotoService;
import photoSorting.service.PhotoSortingService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PhotoImpl implements PhotoService {

    private final PhotoRepository photoRepository;
    private final PhotoMapper photoMapper;
    private final PhotoSortingService photoSortingService;

    @Override
    @Transactional(readOnly = true)
    public List<PhotoDto> allPhoto(){
        return photoMapper.toDtos(photoRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PhotoDto> findYear(Integer year){
        return photoMapper.toDtos(photoRepository.findYear(year));
    }

    @Override
    public void deleteById(Long photoId){
        photoSortingService.deleteFile(photoId);
        photoRepository.deleteById(photoId);
    }
}
