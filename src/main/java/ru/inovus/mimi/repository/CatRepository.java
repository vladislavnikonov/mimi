package ru.inovus.mimi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.inovus.mimi.entity.CatEntity;

@Repository
public interface CatRepository extends JpaRepository<CatEntity, Long> {
    @Query(value = "select u from CatEntity u where u.id=?1 or u.id=?2")
    Iterable<CatEntity> findByLastnameOrFirstname(Long first, Long second);
}
