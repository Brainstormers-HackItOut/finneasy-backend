package com.finneasy.part1.service;

import com.finneasy.part1.entity.Blog;
import com.finneasy.part1.model.BlogModel;

import java.util.List;

public interface BlogService {

    BlogModel createBlog(BlogModel blogModel);

    BlogModel getBlog(Long id);

    List<BlogModel> getAllBlogsOfUser(Long userId);

}
