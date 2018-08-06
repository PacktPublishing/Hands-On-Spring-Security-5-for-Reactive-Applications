package com.packtpub.book.ch06.springsecurity.model;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    private Long id;
    private String title;
    private String genre;

}
