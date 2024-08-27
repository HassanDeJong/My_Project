package com.example.Project.tables;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "house_image")
@Data
public class HouseImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long house_image_id;
    private String imageName;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] imageUrl;
    @ManyToOne
    @JoinColumn(name = "house_id", nullable = false,referencedColumnName = "house_id")
    private House house;
}
