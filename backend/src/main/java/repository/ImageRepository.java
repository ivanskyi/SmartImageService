package repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import model.Image;

@ApplicationScoped
public class ImageRepository implements PanacheRepository<Image> {

}
