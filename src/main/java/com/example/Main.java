package com.example;

import com.example.parser.Parser;
import com.example.save.Book;

import java.io.File;

public class Main {

//    public static void main(String[] args) {
//        SpringApplication.run(Main.class);
//    }

    public static void main(String[] args) {
        Parser parser = new Parser();

        Book book = parser.getBook(new File("Fray_Vse-skazki-starogo-Vilnyusa_1_Vse-skazki-starogo-Vilnyusa-Nachalo.oX330Q.555685.fb2"));

        System.out.println(book);

    }

}