package com.minhph091.mapping.dao;

import com.minhph091.mapping.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {

    @Query("select u from Instructor u join fetch u.courses where u.id =:id")
    Instructor getInstructorById(@Param("id") Integer id);
}
