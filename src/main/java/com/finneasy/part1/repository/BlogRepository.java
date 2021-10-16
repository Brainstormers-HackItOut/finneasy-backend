package com.finneasy.part1.repository;

import com.finneasy.part1.entity.Blog;
import com.finneasy.part1.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends CrudRepository<Blog, Long> {

    List<Blog> findAllByUser(User user);

}
