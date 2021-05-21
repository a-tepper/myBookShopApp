package com.example.myBookShopApp.data;
import javax.persistence.*;

@Entity
@Table(name = "book_review")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String dateTime;
    private String email;
    private String name;
    private String subject;
    private String text;

}
