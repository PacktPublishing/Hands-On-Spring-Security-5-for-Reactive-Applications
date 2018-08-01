package com.packtpub.book.ch05.springsecurity;


import com.packtpub.book.ch05.springsecurity.model.Movie;
import com.packtpub.book.ch05.springsecurity.webclient.WebClientTestInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Run implements CommandLineRunner {

    @Autowired
    WebClientTestInterface webClient;

    public static void main(String[] args) {
        SpringApplication.run(Run.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // get all movies
        System.out.println("Get All Movies");
        webClient.listMovies().subscribe(System.out::println);

        Thread.sleep(3000);

        // get a movie
        System.out.println("Get a Movie with id = 1");
        webClient.getMovieById(new Long(1)).subscribe(System.out::println);

        Thread.sleep(3000);

        // post a movie
        System.out.println("Save a Movie");
        webClient.saveMovie(new Movie(Long.valueOf(5), "Deadpool 2", "Fantasy/Science")).subscribe(System.out::println);

        Thread.sleep(3000);

        // update a movie
        System.out.println("Update a Movie");
        webClient.putMovie(new Long(3), new Movie(new Long(3), "Black Panther", "Fantasy/Science")).subscribe(System.out::println);

        Thread.sleep(3000);

        // delete a movie
        System.out.println("Delete a Movie with id = 2");
        webClient.deleteMovie(new Long(2)).subscribe(response -> System.out.println("Delete Successful!"));

        Thread.sleep(3000);

        // get all movies
        System.out.println("Get All Movies again");
        webClient.listMovies().subscribe(System.out::println);

    }
}
