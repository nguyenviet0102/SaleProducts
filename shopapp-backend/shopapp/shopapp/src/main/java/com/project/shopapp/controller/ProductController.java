package com.project.shopapp.controller;

import com.project.shopapp.dtos.ProductDTO;
import jakarta.validation.*;
import jakarta.validation.constraints.Null;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)    //update anh len duoc chuyen thanh tung phan
    //http://localhost:8080/api/v1/products
    public ResponseEntity<?> createProduct(
            @Valid
            @ModelAttribute ProductDTO productDTO,
//            @RequestBody ProductDTO productDTO,
//            @RequestPart("file") MultipartFile file,

            BindingResult result
        ){

        try {
            if(result.hasErrors()) {
                //duyet qua list chi lay 1 truong data
                List<FieldError> fileErrorList = result.getFieldErrors();
                List<String> errorMessages = fileErrorList.stream().map(FieldError::getDefaultMessage).toList();

                return ResponseEntity.badRequest().body(errorMessages);//tra ve phia client
            }
            //save the product
//            ProducService.createProduct(productDTO);

            MultipartFile file = productDTO.getFile();
            if (file != null){
                //Kiem tra kich thuoc file img va dinh dang
                if (file.getSize() > 10*1024*1024){ //kich thuoc file >10mb
//                throw new ResponseStatusException(HttpStatus.PAYLOAD_TOO_LARGE, "File is too large, Max just 10MB");
                    //Giong ket qua ben tren
                    return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("File is too large, Max just 10MB");
                }
                String contentType = file.getContentType(); //lay ra dinh dang file
                if (contentType == null || !contentType.startsWith("image/")){  //kiem tra dinh dang, va co bat dau bang chu image khong?
                    return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("File must be an image");
                }
                //Luu file va cap nhat anh trong DTO
                String fileName = storeFile(file);  //Thay the ham nay voi code cua ban di
                //Luu vao doi tuong trong DB
            }
//            {
//                "name": "ipad pro 2023",
//                    "price": 812.34,
//                    "tumbnail": "",
//                    "description": "This is a test product",
//                    "category_id": 1
//
//            }

            return  ResponseEntity.ok("Product created successflly");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    private String storeFile(MultipartFile file) throws IOException{
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());    //lay ten file goc, tranh khi 2 nguoi update file cung ten bi @override
        //Them UUID vao truoc ten file de dam bao ten file la duy nhat, noi vao ten file cu
        String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;
        //Duong dan den thu muc muon luu file
        java.nio.file.Path uploadDir = Paths.get("uploads");
        //Kiem tra va tao thu muc neu no khong ton tai
        if (!Files.exists(uploadDir)){  //FALSE neu chua ton tai
            Files.createDirectories(uploadDir);
        }
        //Duong dan day du den file
        java.nio.file.Path destination = Paths.get(uploadDir.toString(), uniqueFileName);
        //Sao chep file vao thu muc dich
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFileName;
    }

    @GetMapping("") //http://localhost:8080/api/v1/products?page=1&limit=10
    public ResponseEntity<String> getProduct(
            @RequestParam("page") int page, //lay trang so may, (page) param gui qua trinh duyet
            @RequestParam("limit") int limit    //lay bao nhieu ban ghi
    ){   //tra ve 1 list
        return ResponseEntity.ok("Get product here!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getProductById(@PathVariable("id") String productId){
        return ResponseEntity.ok("Product with ID:" +productId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable long id){   //Xoa 1 product, phai lay duoc id do
        return ResponseEntity.status(HttpStatus.OK).body(String.format("Product with id: %d delete succesflly", id)); //giong cau lenh return o tren
    }
}
