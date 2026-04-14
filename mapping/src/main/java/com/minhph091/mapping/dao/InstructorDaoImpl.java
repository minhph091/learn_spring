package com.minhph091.mapping.dao;

import com.minhph091.mapping.entity.Course;
import com.minhph091.mapping.entity.Instructor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InstructorDaoImpl implements InstructorDao {
    private final EntityManager entityManager;

    @Autowired
    public InstructorDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional(readOnly = true)
    public Instructor findInstructorById(Integer id) {
        TypedQuery<Instructor> typedQuery = entityManager.createQuery("SELECT u FROM Instructor u JOIN fetch" +
                "  u.courses where u.id=:id", Instructor.class);
        typedQuery.setParameter("id", id);

        return typedQuery.getSingleResult();
    }

    @Override
    @Transactional
    public void addInstructor(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    @Transactional
    public void deleteInstructor(Integer id) {
        Instructor instructor = findInstructorById(id);
        if (instructor != null) {
            entityManager.remove(instructor);
        }
    }

    @Transactional
    public void testOrphanRemoval() {
        Instructor instructor = new Instructor("Minh", "Pham", "minhph091");

        List<Course> courses = new ArrayList<>();
        courses.add(new Course("Math4", instructor));
        courses.add(new Course("Biology4", instructor));

        instructor.setCourses(courses);

        entityManager.persist(instructor);

        instructor.getCourses().removeFirst();
    }

    @Override
    @Transactional
    public Instructor updateInstructor(Instructor instructor) {
        return entityManager.merge(instructor);
    }
}
