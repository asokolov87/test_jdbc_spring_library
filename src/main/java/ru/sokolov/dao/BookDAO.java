package ru.sokolov.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.sokolov.models.Book;
import ru.sokolov.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book",new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id},new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null) ;
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(name, date, author) VALUES (?,?,?)", book.getName(), book.getDate(), book.getAuthor());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book SET name=?, date=?, author=? WHERE id=?", updatedBook.getName(), updatedBook.getDate(),updatedBook.getAuthor(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?",id);
    }

    public Optional<Person> getBooksOwner (int id){
        return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.person_id = Person.id " +
                      "WHERE Book.id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    public void assign(int book_id, Person selectedPerson){
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?", selectedPerson.getId(), book_id);
    }
    public void release(int book_id){
        jdbcTemplate.update("UPDATE Book SET person_id=NULL WHERE id=?", book_id);
    }
}
