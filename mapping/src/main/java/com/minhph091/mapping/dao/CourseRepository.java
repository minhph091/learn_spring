package com.minhph091.mapping.dao;

import com.minhph091.mapping.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query("select u from Course u join fetch u.reviews " +
            "where u.id =:id")
    Course findCourseByIdFetch(@Param("id") Integer id);
}
