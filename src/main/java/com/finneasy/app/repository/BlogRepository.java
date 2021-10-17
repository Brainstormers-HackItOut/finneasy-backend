package com.finneasy.app.repository;

import com.finneasy.app.entity.Blog;
import com.finneasy.app.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends CrudRepository<Blog, Long> {

    List<Blog> findAllByUser(User user);

}
