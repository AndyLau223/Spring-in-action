package com.example.cachingdatawithspring;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class SimpleBookRepository implements BookRepository{


    /**
     * @Cacheable("books"): Annotation indicating that the result of invoking a method (or all methods in a class) can be cached.
     * @param isbn
     * @return
     */
    @Override
    @Cacheable("books")
    public Book getByIsbn(String isbn) {
        simulateSlowService();
        return new Book(isbn, "Some Book");
    }

    private void simulateSlowService(){
       try{
           long time =3000L;
           Thread.sleep(time);
       } catch (InterruptedException e){
           throw new IllegalStateException(e);
       }
    }
}
