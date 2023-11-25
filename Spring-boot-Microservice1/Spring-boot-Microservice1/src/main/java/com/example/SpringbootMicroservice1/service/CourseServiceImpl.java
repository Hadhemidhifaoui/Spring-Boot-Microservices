package com.example.SpringbootMicroservice1.service;

import com.example.SpringbootMicroservice1.model.Course;
import com.example.SpringbootMicroservice1.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService
{
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository)
    {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course saveCourse(Course course)
    {
        course.setCreateTime(LocalDateTime.now());

        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long courseId)
    {
        courseRepository.deleteById(courseId);
    }

    @Override
    public List<Course> findAllCourses()
    {
        return courseRepository.findAll();
    }

    @Override
    public Course updateCourse(Long courseId, Course updatedCourse) {
        Course existingCourse = courseRepository.findById(courseId).orElse(null);

        if (existingCourse != null) {
            existingCourse.setTitle(updatedCourse.getTitle());
            existingCourse.setPrice(updatedCourse.getPrice());
            existingCourse.setCreateTime(updatedCourse.getCreateTime());

            return courseRepository.save(existingCourse);
        }

        return null;
    }

    @Override
    public Course findCourseById(Long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        return course.orElse(null);
    }
}
