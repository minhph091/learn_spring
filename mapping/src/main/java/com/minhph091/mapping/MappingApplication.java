package com.minhph091.mapping;

import com.minhph091.mapping.dao.*;
import com.minhph091.mapping.entity.Course;
import com.minhph091.mapping.entity.Instructor;
import com.minhph091.mapping.entity.InstructorDetail;
import com.minhph091.mapping.entity.Review;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MappingApplication {
    private final InstructorDao instructorDao;
    private final InstructorDetailRepository instructorDetailRepository;
    private final InstructorRepository instructorRepository;

    public MappingApplication(InstructorDao instructorDao, InstructorDetailRepository instructorDetailRepository,
                              InstructorRepository instructorRepository) {
        this.instructorDao = instructorDao;
        this.instructorDetailRepository = instructorDetailRepository;
        this.instructorRepository = instructorRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(MappingApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(CourseRepository courseRepository) {
        return runner -> {
//            InstructorDetail instructorDetail = new InstructorDetail();
//            instructorDetail.setHobby("Reading");
//            instructorDetail.setYoutubeChannel("minph091");
//            Instructor instructor = new Instructor();
//            instructor.setEmail("minhph091");
//            instructor.setFirstName("Minh");
//            instructor.setLastName("Pham");
//
//            List<Course> courseList = new ArrayList<>();
//            courseList.add(new Course("Math", instructor));
//            courseList.add(new Course("Biology", instructor));
//            courseList.add(new Course("Physics", instructor));
//            Course course = courseList.getFirst();
//            List<Review> reviews = new ArrayList<>();
//            reviews.add(new Review("OK", course));
//            reviews.add(new Review("Bad", course));
//            course.setReviews(reviews);
//
//            instructor.setInstructorDetail(instructorDetail);
//            instructor.setCourses(courseList);
//            instructorDao.addInstructor(instructor);
//            System.out.println(instructor);

//            Instructor instructor = instructorRepository.getInstructorById(12);
//            System.out.println(instructor);
            Course course = courseRepository.findCourseByIdFetch(10);
            System.out.println(course);
            courseRepository.deleteById(10);

        };
    }

}
