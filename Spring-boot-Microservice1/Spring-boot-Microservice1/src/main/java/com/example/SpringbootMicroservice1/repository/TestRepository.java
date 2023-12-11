package com.example.SpringbootMicroservice1.repository;

import com.example.SpringbootMicroservice1.model.Course;
import com.example.SpringbootMicroservice1.model.Test;
import com.example.SpringbootMicroservice1.model.TestWithDetails;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findByCourseId(Long courseId);
    boolean existsByCourse(Course course);
    boolean existsByNameAndCourse_Id(String name, Long course_id);



    List<Test> findAll();

    @Query("SELECT DISTINCT t FROM Test t JOIN FETCH t.questions q JOIN FETCH q.suggestions JOIN FETCH t.course")
    List<Test> findAllWithQuestionsAndSuggestionsAndCourse();

    @Query("SELECT t FROM Test t JOIN FETCH t.course")
    List<Test> findAllWithDetails();



}

