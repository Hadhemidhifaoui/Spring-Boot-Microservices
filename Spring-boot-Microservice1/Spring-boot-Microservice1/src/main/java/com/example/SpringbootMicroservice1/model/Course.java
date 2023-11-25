package com.example.SpringbootMicroservice1.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.Duration;


import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "course")
public class Course
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "duree", length = 100, nullable = false)
    private  String  duree;

    @Column(name = "price", nullable = false)
    private Double price;
    @Column(name = "image")
    private String image;
    @Column(name = "lien", nullable = false)
    private String lien;
    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;
}