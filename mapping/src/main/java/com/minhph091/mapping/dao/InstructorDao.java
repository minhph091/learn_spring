package com.minhph091.mapping.dao;

import com.minhph091.mapping.entity.Instructor;

public interface InstructorDao {

    Instructor findInstructorById(Integer integer);

    void addInstructor(Instructor instructor);

    Instructor updateInstructor(Instructor instructor);

    void deleteInstructor(Integer id);
    public void testOrphanRemoval();
}
