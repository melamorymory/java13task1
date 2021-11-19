package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    ProductRepository repository = new ProductRepository();
    Product book1 = new Book(1, "Dune", 300, "Gerbert");
    Product book2 = new Book(2, "Naruto", 500, "Kishimoto");
    Product smartphone1 = new Smartphone(11, "Galaxy", 30_000, "Samsung");
    Product smartphone2 = new Smartphone(22, "IPhone", 50_000, "Apple");
    Product cat = new Product(1, "Sirius", 0);

    @BeforeEach
    public void setUp() {
        repository.save(book1);
        repository.save(book2);
        repository.save(smartphone1);
        repository.save(smartphone2);
    }

    @Test
    void shouldRemoveByExistentId() {
        repository.removeById(2);
        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{book1, smartphone1, smartphone2};
        assertArrayEquals(actual, expected);
    }

    @Test
    void shouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () -> {repository.removeById(1000);});
    }
}