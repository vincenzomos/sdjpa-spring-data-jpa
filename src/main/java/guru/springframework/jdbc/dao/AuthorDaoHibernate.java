package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Author;
import guru.springframework.jdbc.domain.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class AuthorDaoHibernate implements AuthorDao {

    private final EntityManagerFactory emf;

    public AuthorDaoHibernate(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Author> findAllAuthorsByLastName(String lastname, Pageable pageable) {
        EntityManager em = getEntityManager();

        StringBuilder sb = new StringBuilder("SELECT a FROM Author a where a.lastName = :lastName ");

        if (pageable.getSort().getOrderFor("firstname") != null) {
            sb.append("order by a.firstName ").append(pageable.getSort()
                    .getOrderFor("firstname").getDirection().name());
        }
        try {
            TypedQuery<Author> query = em.createQuery(sb.toString(), Author.class);
            query.setParameter("lastName", lastname);
            query.setFirstResult(Math.toIntExact(pageable.getOffset()));
            query.setMaxResults(pageable.getPageSize());
            return query.getResultList();
        } finally {
            em.close();
        }
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
    public void deleteAuthorById(Long id) {

    }
    private EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

}
