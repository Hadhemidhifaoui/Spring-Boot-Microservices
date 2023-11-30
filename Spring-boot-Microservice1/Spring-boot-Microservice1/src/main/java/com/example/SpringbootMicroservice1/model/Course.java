package com.example.SpringbootMicroservice1.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Duration;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "course")
public class Course
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 100, nullable = true)
    private String title;

    @Column(name = "subtitle", length = 100, nullable = true)
    private String subtitle;

    @Column(name = "duree", length = 100, nullable = true)
    private  String  duree;

    @Column(name = "price", nullable = true)
    private Double price;

    @Column(name = "image", nullable = true)
    private String image;

    @Column(name = "lien", nullable = true)
    private String lien;

    /*@OneToOne(mappedBy = "course")
    private Test test;*/
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Test> tests = new ArrayList<>();



    @CreationTimestamp
    @Column(name = "create_time", nullable = false, updatable = false)
    private LocalDateTime createTime;

    public void addTest(Test test) {
        tests.add(test);
        test.setCourse(this);
    }


}