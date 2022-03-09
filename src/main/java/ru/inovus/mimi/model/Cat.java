package ru.inovus.mimi.model;

import lombok.Data;
import ru.inovus.mimi.entity.CatEntity;

@Data
public class Cat {
    private Long id;
    private String name;
    private Long upvote;
    private String image;

    public Cat(CatEntity catEntity) {
        this.id = catEntity.getId();
        this.name = catEntity.getName();
        this.upvote = catEntity.getUpvote();
        this.image = catEntity.getImage();
    }
}
