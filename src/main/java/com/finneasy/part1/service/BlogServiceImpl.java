package com.finneasy.part1.service;

import com.finneasy.part1.entity.Blog;
import com.finneasy.part1.entity.User;
import com.finneasy.part1.model.BlogModel;
import com.finneasy.part1.repository.BlogRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService{

    private UserService userService;
    private BlogRepository blogRepository;

    public BlogServiceImpl(UserService userService, BlogRepository blogRepository) {
        this.userService = userService;
        this.blogRepository = blogRepository;
    }

    @Override
    public BlogModel createBlog(BlogModel blogModel) {
        User user = userService.getUser(blogModel.getUserId());
        Blog blog = new Blog(
                blogModel.getTitle(),
                user.getFirstName() + " " + user.getLastName(),
                blogModel.getPublishedDate(),
                blogModel.getBody(),
                0,
                0,
                user
        );

        blogRepository.save(blog);
        blogModel.setId(blog.getId());
        return blogModel;
    }

    @Override
    public BlogModel getBlog(Long id) {
        Blog blog = blogRepository.findById(id).get();
        return blogToBlogModel(blog);
    }

    @Override
    public List<BlogModel> getAllBlogsOfUser(Long userId) {
        User user = userService.getUser(userId);
        return blogRepository.findAllByUser(user).stream().map(blog -> blogToBlogModel(blog)).collect(Collectors.toList());
    }

    @Override
    public List<BlogModel> getAllBlogs() {
        return ((List<Blog>)blogRepository.findAll()).stream().map(blog -> blogToBlogModel(blog)).collect(Collectors.toList());
    }

    @Override
    public BlogModel likeBlog(Long id) {
        Blog blog = blogRepository.findById(id).get();
        blog.setLikes(blog.getLikes()+1);
        blogRepository.save(blog);
        return blogToBlogModel(blog);
    }

    @Override
    public BlogModel dislikeBlog(Long id) {
        Blog blog = blogRepository.findById(id).get();
        blog.setDislikes(blog.getDislikes()+1);
        blogRepository.save(blog);
        return blogToBlogModel(blog);
    }

    private BlogModel blogToBlogModel(Blog blog){
        BlogModel blogModel = new BlogModel();
        BeanUtils.copyProperties(blog, blogModel);
        blogModel.setUserId(blog.getUser().getId());
        return blogModel;
    }
}
