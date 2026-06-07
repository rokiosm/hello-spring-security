package kr.ac.hansung.repository;

import kr.ac.hansung.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {
    long countByStockEquals(int stock);
}

@Query("""
    select p
    from Product p
    where :keyword is null
       or :keyword = ''
       or lower(p.name) like lower(concat('%', :keyword, '%'))
       or lower(p.description) like lower(concat('%', :keyword, '%'))
""")
Page<Product> searchProducts(@Param("keyword") String keyword, Pageable pageable);