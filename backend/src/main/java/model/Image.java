package model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import java.time.LocalDateTime;

@Entity
@Table(name = "images")
public class Image extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "name", nullable = false)
    public String name;

    @Column(name = "file_data")
    public byte[] fileData;

    @Column(name = "thumbnail")
    public byte[] thumbnail;

    @Column(name = "created_at")
    public LocalDateTime createdAt;

    @Column(name = "status")
    public String status;
}
