package mvc.codejava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mvc.codejava.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
