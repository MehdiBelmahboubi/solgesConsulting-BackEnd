package com.elmiraouy.jwtsecurity.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Data
@Builder
@Table(name = "images")
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name_image")
    private String name;

    @Column(name = "url_image")
    private String url;
    private String imageId;
    private LocalDateTime dateCreation;
    private LocalDateTime dateUpdate;
    private LocalDateTime dateDeleted;

    public Image(String name, String url, String imageId) {
        this.name=name;
        this.url=url;
        this.imageId = imageId;
    }

    public Image() {
    }
}
