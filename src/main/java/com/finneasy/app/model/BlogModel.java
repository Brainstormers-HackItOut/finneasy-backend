package com.finneasy.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BlogModel {

    private Long id;

    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date publishedDate;

    private String body;

    private Long userId;

    private Integer likes;

    private Integer dislikes;

    private String publisher;

}
