package com.spring.rest.api.mybooks.repo;

import com.spring.rest.api.mybooks.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    //https://javascopes.com/spring-data-jpa-modifying-annotation-5f3586d5/
    @Modifying
    @Query("UPDATE Book SET code=:code WHERE id=:id")
    Integer updateBookCodeById(String code,Long id);

    @Override
    <S extends Book> List<S> saveAll(Iterable<S> entities);

    @Override
    void deleteAll(Iterable<? extends Book> entities);

}
