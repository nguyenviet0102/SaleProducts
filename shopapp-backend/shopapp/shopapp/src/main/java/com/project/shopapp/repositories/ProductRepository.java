package com.project.shopapp.repositories;

import com.project.shopapp.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);  //Tim xem san pham voi name co ton tai hay khong
    Page<Product> findAll(Pageable pageable);  //Phan trang theo ban ghi

}
