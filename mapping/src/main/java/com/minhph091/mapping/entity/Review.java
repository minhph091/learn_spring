package com.minhph091.mapping.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private  Course course;
    public Review(String comment,Course course){
        this.comment = comment;
        this.course = course;
    }

    @Override
    public String toString() {
        return "Review{" +
                "comment='" + comment + '\'' +
                ", id=" + id +
                '}';
    }
}
