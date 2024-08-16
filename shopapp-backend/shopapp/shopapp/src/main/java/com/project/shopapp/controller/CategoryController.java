package com.project.shopapp.controller;

import com.project.shopapp.dtos.CategoryDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
//@Validated
public class CategoryController {
    //Hien thi tat ca categories
    //Neu tham so truyen vao la 1 object??  -> Data TransferObject  = RequestObject
    @GetMapping("") //http://localhost:8080/api/v1/categories?page=1&limit=10
    public ResponseEntity<String> getAllCategories(
            @RequestParam("page") int page, //lay trang so may, (page) param gui qua trinh duyet
            @RequestParam("limit") int limit    //lay bao nhieu ban ghi
    ){   //tra ve 1 list
        return ResponseEntity.ok(String.format("getAllCategories, page = %d, limit = %d", page, limit));
    }
    @PostMapping("")
    public ResponseEntity<?> insertCategories(
            @Valid @RequestBody CategoryDTO categoryDTO,
            BindingResult result ){   //them moi 1 category
        if(result.hasErrors()) {
            //duyet qua list chi lay 1 truong data
            List<FieldError> fileErrorList = result.getFieldErrors();
            List<String> errorMessages = fileErrorList.stream().map(FieldError::getDefaultMessage).toList();

            return ResponseEntity.badRequest().body(errorMessages);//tra ve phia client
        }
        return ResponseEntity.ok("This is categories" + categoryDTO);
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
