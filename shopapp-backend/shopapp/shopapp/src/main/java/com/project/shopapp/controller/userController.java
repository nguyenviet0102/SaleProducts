package com.project.shopapp.controller;

import com.project.shopapp.dtos.UserDTO;
import com.project.shopapp.dtos.UserLoginDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/users")
public class userController {
    @PostMapping("/register") //cac thong so gui len khong thong ua trinh duyet
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO, BindingResult result){
        try {
            if(result.hasErrors()) {
                //duyet qua list chi lay 1 truong data
                List<FieldError> fileErrorList = result.getFieldErrors();
                List<String> errorMessages = fileErrorList.stream().map(FieldError::getDefaultMessage).toList();

                return ResponseEntity.badRequest().body(errorMessages);//tra ve phia client
            }
            if (!userDTO.getPassword().equals(userDTO.getRetypePassword())){
                return ResponseEntity.badRequest().body("Password does not match!");
            }
            return ResponseEntity.ok("Rigister successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login") //cac thong so gui len khong thong qua trinh duyet
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDTO userLoginDTO){
        //kiểm tra thônng tin đăng nhâp và sinh token
        //Tra về token trong respone
        return ResponseEntity.ok("Some token");

    }
}
