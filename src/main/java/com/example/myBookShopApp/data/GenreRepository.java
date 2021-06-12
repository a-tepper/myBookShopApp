package com.example.MyBookShopApp.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface GenreRepository extends JpaRepository<Genre, Integer> {

    List<Genre> findByParent_Id(Integer id);
}
