package com.example.SpringbootMicroservice1.service;

import com.example.SpringbootMicroservice1.model.Course;
import com.example.SpringbootMicroservice1.repository.CourseRepository;
import com.example.SpringbootMicroservice1.utils.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class CourseServiceImpl implements CourseService
{


    @Autowired
    private StorageService storageService;
    private final CourseRepository courseRepository;

    /*@Override
    public Course saveCourse(Course course)
    {
        course.setCreateTime(LocalDateTime.now());

        return courseRepository.save(course);
    }*/

    public CourseServiceImpl(CourseRepository courseRepository)
    {
        this.courseRepository = courseRepository;
    }

    public Course saveCourse(Course course, MultipartFile image) {
        String filename = StringUtils.cleanPath(image.getOriginalFilename());
        course.setImage(filename);
        storageService.store(image);
        return courseRepository.save(course);
    }
    @Transactional
    public Course getCourseDetails(Long courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }
    /*@Transactional
    public Course getCourseDetails(Long courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);

        if (course != null) {

            String courseName = course.getTitle();
            String courseImage = course.getImage();

            course.setTitle(courseName);
            course.setImage(courseImage);


        }

        return course;
    }*/

    public void deleteCourse(Long courseId)
    {
        courseRepository.deleteById(courseId);
    }


    public List<Course> findAllCourses()
    {
        return courseRepository.findAll();
    }


    public Course updateCourse(Long courseId, Course updatedCourse, MultipartFile image) {
        Course existingCourse = courseRepository.findById(courseId).orElse(null);
        if (existingCourse != null) {
            existingCourse.setTitle(updatedCourse.getTitle());
            existingCourse.setDuree(updatedCourse.getDuree());
            existingCourse.setPrice(updatedCourse.getPrice());
            existingCourse.setLien(updatedCourse.getLien());


                if (image != null && !image.isEmpty()) {
                    // Delete the existing image before storing the new one
                   // storageService.delete(existingCourse.getImage());

                    // Store the new image and update the course with the filename
                    String filename = StringUtils.cleanPath(image.getOriginalFilename());
                    existingCourse.setImage(filename);
                    storageService.store(image);
                }


            return courseRepository.save(existingCourse);
        } else {
            return null; // Course not found
        }
    }


    public Course findCourseById(Long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        return course.orElse(null);
    }
    @Override
    public Course addCourse(String title, String subtitle, String duree, Double price, String image, String lien) {
        Course newCourse = new Course();
        newCourse.setTitle(title);
        newCourse.setSubtitle(subtitle); // Make sure to set the subtitle
        newCourse.setDuree(duree);
        newCourse.setPrice(price);
        newCourse.setImage(image);
        newCourse.setLien(lien);
        newCourse.setCreateTime(LocalDateTime.now());

        return courseRepository.save(newCourse);
    }
}
