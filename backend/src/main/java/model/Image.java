package model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity(name = "images")
@Table(name = "images")
public class Image extends PanacheEntity {

    public String name;

    @Column(name = "file_data")
    public byte[] file;

    @Column(name = "created_at")
    public LocalDateTime createdAt;

    public String status;
}
