package com.finneasy.part1.controller;

import com.finneasy.part1.model.BlogModel;
import com.finneasy.part1.service.BlogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {

    private BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/create")
    public ResponseEntity<BlogModel> createBlog(@RequestBody BlogModel blogModel){
        return ResponseEntity.ok(blogService.createBlog(blogModel));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogModel> getBlog(@PathVariable Long id){
        return ResponseEntity.ok(blogService.getBlog(id));
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<BlogModel>> getAllBlogsOfUser(@PathVariable Long userId){
        return ResponseEntity.ok(blogService.getAllBlogsOfUser(userId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<BlogModel>> getAllBlogs(@PathVariable Long userId){
        return ResponseEntity.ok(blogService.getAllBlogs());
    }

    @GetMapping("/like/{id}")
    public ResponseEntity<BlogModel> likeBlog(@PathVariable Long id){
        return ResponseEntity.ok(blogService.likeBlog(id));
    }

    @GetMapping("/dislike/{id}")
    public ResponseEntity<BlogModel> dislikeBlog(@PathVariable Long id){
        return ResponseEntity.ok(blogService.dislikeBlog(id));
    }
}
