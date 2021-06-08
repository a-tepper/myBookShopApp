package com.example.MyBookShopApp.data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import com.example.MyBookShopApp.data.Genre;

@Entity
@Table(name = "parent_genre")
public class ParentGenre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "parent")
    private List<Genre> childrenGenres = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Genre> getChildrenGenres() {
        return childrenGenres;
    }

    public void setChildrenGenres(List<Genre> childrenGenres) {
        this.childrenGenres = childrenGenres;
    }

    @Override
    public String toString() {
        return "ParentGenre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}