package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Author;
import guru.springframework.jdbc.domain.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {"guru.springframework.jdbc.dao"})
class AuthorDaoJDBCTemplateTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    AuthorDao authorDao;

    @BeforeEach
    void setUp() {
        authorDao = new AuthorDaoJDBCTemplate(jdbcTemplate);
    }


    @Test
    void testFindAuthorByLastName() {
        List<Author> authors = authorDao.findAuthorsByLastName(PageRequest.of(0, 10,
                Sort.by(Sort.Order.asc("first_name"))), "Smith");

        assertThat(authors).isNotNull();
        assertThat(authors.size()).isEqualTo(10);
    }

    @Test
    void findAllBooksPage1_pageable() {
        List<Author> authors = authorDao.findAuthorsByLastName(PageRequest.of(0, 10,
                Sort.by(Sort.Order.asc("first_name"))), "Smith");

        assertThat(authors).isNotNull();
        assertThat(authors.size()).isEqualTo(10);

        authors.stream().forEach(author -> System.out.println(author));
        Author author = authors.get(0);
        assertThat(author.getFirstName()).isEqualTo("Ahmed");
    }

    @Test
    void findAllBooksPage2_pageable() {
        List<Author> authors = authorDao.findAuthorsByLastName(PageRequest.of(2, 10,
                Sort.by(Sort.Order.asc("first_name"))), "Smith");

        assertThat(authors).isNotNull();
        assertThat(authors.size()).isEqualTo(10);
        authors.stream().forEach(author -> System.out.println(author));
        Author author = authors.get(0);
        System.out.println(" TESTLOG firstname "  + author.getFirstName());
        assertThat(author.getFirstName()).isEqualTo("Ahmed");
    }
}