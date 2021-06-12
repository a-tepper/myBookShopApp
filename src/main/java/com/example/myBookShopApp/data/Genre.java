package com.example.MyBookShopApp.data;

import javax.persistence.*;
import com.example.MyBookShopApp.data.ParentGenre;

@Entity
@Table(name = "genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private ParentGenre parent;
    private String slug;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ParentGenre getParent() {
        return parent;
    }

    public void setParent(ParentGenre parent) {
        this.parent = parent;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", parent=" + parent +
                ", slug='" + slug + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}