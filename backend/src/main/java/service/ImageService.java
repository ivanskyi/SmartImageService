package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import model.Image;
import repository.ImageRepository;

import java.util.List;

@ApplicationScoped
public class ImageService {

    @Inject
    ImageRepository imageRepository;

    @Transactional
    public List<Image> getAllImages() {
        return imageRepository.listAll();
    }
}
