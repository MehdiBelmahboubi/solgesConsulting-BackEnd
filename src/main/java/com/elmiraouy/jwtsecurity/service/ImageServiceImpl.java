package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.entities.Company;
import com.elmiraouy.jwtsecurity.entities.Image;
import com.elmiraouy.jwtsecurity.handlerException.CompanyException;
import com.elmiraouy.jwtsecurity.handlerException.EntityNotFoundException;
import com.elmiraouy.jwtsecurity.repository.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ImageServiceImpl implements ImageService{

    private final ImageRepository imageRepository;

    @Override
    public Optional<Image> getOne(Long id){
        return imageRepository.findById(id);
    }
    @Override
    public Image save(Image image){
        image.setDateCreation(LocalDateTime.now());
        return imageRepository.save(image);
    }
    @Override
    public void delete(Long id){
        imageRepository.deleteById(id);
    }
    @Override
    public boolean exists(Long id){
        return imageRepository.existsById(id);
    }

    @Override
    public Image findById(Long id) throws EntityNotFoundException {
        return imageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Image With id [%s] not ".formatted(id)));
    }
}
