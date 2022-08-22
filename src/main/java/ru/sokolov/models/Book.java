package ru.sokolov.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {

    private int id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String author;
    @Min(value = 0, message = "Age should be greater than 0")
    private int date;

    public Book() {
    }

    public Book(int id, String name, String author, int date) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.date = date;
    }

    public int getId() {return id;    }
    public void setId(int id) {        this.id = id;    }
    public String getName() {        return name;    }
    public void setName(String name) {        this.name = name;    }
    public String getAuthor() {        return author;    }
    public void setAuthor(String author) {        this.author = author;    }
    public int getDate() {        return date;    }
    public void setDate(int date) {        this.date = date;    }

}
