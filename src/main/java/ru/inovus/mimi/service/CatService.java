package ru.inovus.mimi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.inovus.mimi.model.Cat;
import ru.inovus.mimi.model.Pair;
import ru.inovus.mimi.entity.CatEntity;
import ru.inovus.mimi.repository.CatRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CatService {
    private CatRepository catRepository;

    @Autowired
    public CatService(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public void getRandomSet(Set<Pair> pairs) {
        Random random = new Random();
        long first = random.nextLong(1, 11);
        long second = first;
        while (second == first)
            second = random.nextLong(1, 11);
        Pair key = new Pair(first, second);
        if (pairs.size() == 45)
            return;
        pairs.add(key);
        getRandomSet(pairs);
    }

    public List<Cat> getTop() {
        List<Cat> cats = new ArrayList<>();
        Iterable<CatEntity> catEntities = catRepository.findAll();
        for (CatEntity cat : catEntities)
            cats.add(new Cat(cat));
        List<Cat> sortedCats = cats.stream().sorted((o1, o2) -> {
            if (o1 == o2)
                return 0;
            return (o1.getUpvote() > o2.getUpvote() ? -1 : 1);
        }).limit(10).collect(Collectors.toList());
        return sortedCats;
    }

    public void updateVote(Long id) {
        CatEntity catEntity = catRepository.findById(id).get();
        catEntity.setUpvote(catEntity.getUpvote() + 1);
        catRepository.save(catEntity);
    }

    public List<Cat> getPair(Pair pair) {
        List<Cat> cats = new ArrayList<>();
        Iterable<CatEntity> catEntities = catRepository.findByLastnameOrFirstname(pair.getFirst(), pair.getSecond());
        catEntities.forEach(System.out::println);
        for (CatEntity cat : catEntities)
            cats.add(new Cat(cat));
        return cats;
    }
}
