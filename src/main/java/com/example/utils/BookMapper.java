package com.example.utils;

import com.example.dtos.BookDto;
import com.example.entities.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {

    BookDto toBookDto(Book book);

    Book toBook(BookDto bookDto);

    List<BookDto> toBookDtoList(List<Book> books);
}
