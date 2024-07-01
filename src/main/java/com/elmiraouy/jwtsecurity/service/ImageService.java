package com.elmiraouy.jwtsecurity.service;

import com.elmiraouy.jwtsecurity.entities.Image;
import com.elmiraouy.jwtsecurity.handlerException.EntityNotFoundException;

import java.util.Optional;

public interface ImageService {
    public Optional<Image> getOne(Long id);

    public Image save(Image image);

    public void delete(Long id);

    public boolean exists(Long id);
    public Image findById(Long id) throws EntityNotFoundException;
}
