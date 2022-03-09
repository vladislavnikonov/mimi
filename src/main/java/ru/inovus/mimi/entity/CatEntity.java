package ru.inovus.mimi.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cats")
public class CatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long upvote;
    private String image;
}
