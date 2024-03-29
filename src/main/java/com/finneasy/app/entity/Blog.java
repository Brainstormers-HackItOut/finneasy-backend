package com.finneasy.app.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String publisher;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date publishedDate;

    @Column(columnDefinition = "TEXT")
    private String body;

    private Integer likes;

    private Integer dislikes;

    @ManyToOne
    private User user;

    public Blog() {
    }

    public Blog(String title, String publisher, Date publishedDate, String body, Integer likes, Integer dislikes, User user) {
        this.title = title;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.body = body;
        this.likes = likes;
        this.dislikes = dislikes;
        this.user = user;
    }
}
