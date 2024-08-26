package com.project.shopapp.services;

import com.project.shopapp.dtos.CategoryDTO;
import com.project.shopapp.models.Category;
import com.project.shopapp.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor    //Kiem tra thuoc tinh nao la final se tu tao ra contructor
public class CategoryService implements ICategoryService{
    private final CategoryRepository categoryRepository;
//    public CategoryService(CategoryRepository categoryRepository){
//        this.categoryRepository=categoryRepository;
//    }

    @Override
    public Category createCategory(CategoryDTO categoryDTO) {
        Category newCategory = Category.builder().name(categoryDTO.getName()).build();  //Khoi tao object rong, sau do ta khoi tao tung thanh phan
        return categoryRepository.save(newCategory);
    }

    @Override
    public Category getCategoryById(long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        //Tim Cate co id truyen vao neu khong thay tra ve exception(bieu thuc Lambda)
    }

    @Override
    public List<Category> getAllCategories() {

        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(long categoryId, CategoryDTO categoryDTO) {
        Category existingCategory = getCategoryById(categoryId);    //Tim ra Cate voi id
        existingCategory.setName(categoryDTO.getName());   //Sua doi
        return existingCategory;
    }

    @Override
    public void deleteCategory(long id) {
        //Xoa cung
        //Xoa mem -> cap nhat truong active = false
        categoryRepository.deleteById(id);
    }
}
