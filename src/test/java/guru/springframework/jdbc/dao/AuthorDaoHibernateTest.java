package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Author;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"guru.springframework.jdbc.dao"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthorDaoHibernateTest {

    @Autowired
    EntityManagerFactory emf;

    AuthorDao authorDao;

    @BeforeEach
    void setUp() {
        authorDao = new AuthorDaoHibernate(emf);
    }

    @Test
    void testFindAllAuthorsByLastNameWithSort() {

        List<Author> authors = authorDao.findAllAuthorsByLastName("Smith", PageRequest.of(0, 10,
                Sort.by(Sort.Order.asc("firstname"))));

        assertThat(authors).isNotNull();
        assertThat(authors.size()).isEqualTo(10);
        assertThat(authors.get(0).getFirstName()).isEqualTo("Ahmed");
    }

    @Test
    void testFindAllAuthorsByLastNameWithoutSort() {

        List<Author> authors = authorDao.findAllAuthorsByLastName("Smith", PageRequest.of(0, 10));

        assertThat(authors).isNotNull();
        assertThat(authors.size()).isEqualTo(10);
        System.out.println(" TESTLOG : " + authors.get(0).getFirstName());
        assertThat(authors.get(0).getFirstName()).isNotEqualTo("Ahmed");
    }
}
