package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ParserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParserApplication.class, args);
    }

//    public static void main(String[] args) {
//        Parser parser = new Parser();
//
//        Book book = parser.getBook(new File("Fray_Vse-skazki-starogo-Vilnyusa_1_Vse-skazki-starogo-Vilnyusa-Nachalo.oX330Q.555685.fb2"));
//
//        System.out.println(book);
//
//    }
}