package com.project.shopapp.controller;

import com.project.shopapp.dtos.CategoryDTO;
import com.project.shopapp.dtos.OrderDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/orders")
public class OrderController {
    @PostMapping("")
    public ResponseEntity<?> createOrder(@RequestBody @Valid OrderDTO orderDTO, BindingResult result){
        try{
            if(result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();

                return ResponseEntity.badRequest().body(errorMessages);//tra ve phia client
                }
            return ResponseEntity.ok("Create Orders successfully");
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{user_id}")
    //Them duong dan lay ra detail don hang cua user
    //http://localhost:8080/api/v1/4
    public ResponseEntity<?> createOrder(@Valid @PathVariable("user_id") Long userId ){
        try{
//            if(result.hasErrors()) {
//                List<String> errorMessages = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
//
//                return ResponseEntity.badRequest().body(errorMessages);//tra ve phia client
//            }
            return ResponseEntity.ok("Get OrderList from userID" + userId);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    // Cap nhat thong tin don hang
    //http://localhost:8080/api/v1/4
    //công việc của admin
    public ResponseEntity<?> updateOrder(@RequestBody @Valid OrderDTO orderDTO, @Valid @PathVariable Long id){
        return ResponseEntity.ok("update orderDetail" + id);
    }
    @DeleteMapping("/{id}")
    //xoa mem => cap nhat truong active = false
    public ResponseEntity<?> deleteOrder(@Valid @PathVariable Long id){
        return ResponseEntity.ok("Delete successfully" + id);
    }
}