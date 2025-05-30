package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Author;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class AuthorDaoJDBCTemplate implements AuthorDao {

    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoJDBCTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Author getById(Long id) {
        return null;
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        return null;
    }

    @Override
    public Author saveNewAuthor(Author author) {
        return null;
    }

    @Override
    public Author updateAuthor(Author author) {
        return null;
    }

    @Override
    public List<Author> findAuthorsByLastName(Pageable pageable, String lastName) {
        String sql = "SELECT * FROM author where last_name = ? order by first_name " + pageable
                .getSort().getOrderFor("first_name").getDirection().name()
                + " limit ? offset ?";

        System.out.println(sql);

        return jdbcTemplate.query(sql, getAuthorMapper(), lastName, pageable.getPageSize(), pageable.getOffset());
    }

    private AuthorMapper getAuthorMapper() {
        return new AuthorMapper();
    }

    @Override
    public void deleteAuthorById(Long id) {

    }


}
