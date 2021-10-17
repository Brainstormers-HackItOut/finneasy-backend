package com.finneasy.app.service;

import com.finneasy.app.model.BlogModel;

import java.util.List;

public interface BlogService {

    BlogModel createBlog(BlogModel blogModel);

    BlogModel getBlog(Long id);

    List<BlogModel> getAllBlogsOfUser(Long userId);

    List<BlogModel> getAllBlogs();

    BlogModel likeBlog(Long id);

    BlogModel dislikeBlog(Long id);

}
