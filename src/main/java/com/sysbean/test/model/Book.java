package com.sysbean.test.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "tb_book")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookId;

    private String bookName;

    private String bookDescription;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "book")
    private Set<Author> authors = new HashSet<>();

    public Book(String bookName, String bookDescription, List<String> authors) {
        this.bookName = bookName;
        this.bookDescription = bookDescription;

        if(authors != null) {
            for(String author : authors) {
                this.authors.add(new Author(this, author));
            };
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookDescription='" + bookDescription + '\'' +
                ", authors=" + authors.size() +
                '}';
    }
}
