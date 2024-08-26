package com.project.shopapp.controller;

import com.project.shopapp.dtos.CategoryDTO;
import com.project.shopapp.repositories.CategoryRepository;
import com.project.shopapp.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/categories")
//@Validated
//Dependence Injection
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("")
    public ResponseEntity<?> createCategories(
            @Valid @RequestBody CategoryDTO categoryDTO,
            BindingResult result ){   //them moi 1 category
        if(result.hasErrors()) {
            //duyet qua list chi lay 1 truong data
            List<FieldError> fileErrorList = result.getFieldErrors();
            List<String> errorMessages = fileErrorList.stream().map(FieldError::getDefaultMessage).toList();

            return ResponseEntity.badRequest().body(errorMessages);//tra ve phia client
        }
        categoryService.createCategory(categoryDTO);
        return ResponseEntity.ok("This is categories" + categoryDTO);
    }

    //Hien thi tat ca categories
    //Neu tham so truyen vao la 1 object??  -> Data TransferObject  = RequestObject
    @GetMapping("") //http://localhost:8080/api/v1/categories?page=1&limit=10
    public ResponseEntity<String> getAllCategories(
            @RequestParam("page") int page, //lay trang so may, (page) param gui qua trinh duyet
            @RequestParam("limit") int limit    //lay bao nhieu ban ghi
    ){   //tra ve 1 list
        return ResponseEntity.ok(String.format("getAllCategories, page = %d, limit = %d", page, limit));
    }



    @PutMapping("/{id}")   //lay id de cap  nhat
    public ResponseEntity<String> updateCategories(@PathVariable long id){   //Cap nhat du lieu
        return ResponseEntity.ok("Insert category with id = "+id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategories(@PathVariable long id){   //Xoa 1 category, phai lay duoc id do
        return ResponseEntity.ok("delete category with id = " + id);
    }
}
