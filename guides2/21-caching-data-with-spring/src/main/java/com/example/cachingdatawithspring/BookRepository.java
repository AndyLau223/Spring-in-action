package com.example.cachingdatawithspring;

public interface BookRepository {
    Book getByIsbn(String isbn);
}
