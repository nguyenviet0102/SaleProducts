package com.project.shopapp.repositories;

import com.project.shopapp.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    //Category save(Category category);
    //Thay vi su dung cau lenh SQL thi Jpa xay dung san cac method cho

}
