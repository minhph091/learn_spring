package com.minhph091.mapping.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull
    private String lastName;
    @NonNull
    private String firstName;
    @NonNull
    private String email;
    @ManyToMany(mappedBy = "students", cascade = {
            CascadeType.PERSIST, CascadeType.MERGE
    })
    List<Course> courses;

}
