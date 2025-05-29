package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Book;
import guru.springframework.jdbc.repositories.BookRepository;
import jakarta.persistence.EntityNotFoundException;

public class BookDaoImpl implements BookDao {

    private BookRepository bookRepository;

    public BookDaoImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Book findBookByTitle(String title) {
        return bookRepository.findByTitle(title).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Book saveNewBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book saved) {
       Book bookFromDB = bookRepository.findById(saved.getId()).orElseThrow(EntityNotFoundException::new);
       bookFromDB.setTitle(saved.getTitle());
       bookFromDB.setAuthorId(saved.getAuthorId());
       bookFromDB.setPublisher(saved.getPublisher());
       return bookRepository.save(bookFromDB);
    }

    @Override
    public void deleteBookById(Long id) {

//        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
//        }
    }
}
