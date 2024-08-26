package com.project.shopapp.controller;

import com.project.shopapp.dtos.OrderDTO;
import com.project.shopapp.dtos.OrderDetailDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/orders_details")
public class OrderDetailController {
    //Them moi 1 orderdetail
    @PostMapping("")
    public ResponseEntity<?> createOrder(@RequestBody @Valid OrderDetailDTO orderDetailDTO, BindingResult result){
        try{
            if(result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();

                return ResponseEntity.badRequest().body(errorMessages);//tra ve phia client
            }
            return ResponseEntity.ok("Create Orders here");
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    //Them duong dan lay ra detail don hang cua user
    //http://localhost:8080/api/v1/4
    public ResponseEntity<?> getOrderDetail(@Valid @PathVariable("id") Long id ){
        try{
            return ResponseEntity.ok("Get OrderList with id = " + id);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/order/{orderId}")
    //Lấy ra danh sách orderDetail cua 1 order
    public ResponseEntity<?> getOrderDetails(@Valid @PathVariable("orderId") Long orderId){
        //List<OrderDetailDTO> orderDetails = orderDetailService.getOrderDetails(orderId);
        return ResponseEntity.ok("Get OrderDetails with id = " + orderId);
    }

    @PutMapping("/{id}")
    // Cap nhat thong tin don hang
    //http://localhost:8080/api/v1/4
    //công việc của admin
    public ResponseEntity<?> updateOrder(@RequestBody @Valid OrderDetailDTO newOrderDetailData,
                                         @Valid @PathVariable Long id){
        return ResponseEntity.ok("update orderDetail with id = " + id + ",newOrderDetailDTO" + newOrderDetailData);
    }

    @DeleteMapping("/{id}")
    //xoa mem => cap nhat truong active = false
    public ResponseEntity<Void> deleteOrderDetail(@Valid @PathVariable Long id){
        return ResponseEntity.noContent().build();//ok("Delete successfully" + id);
    }
}
