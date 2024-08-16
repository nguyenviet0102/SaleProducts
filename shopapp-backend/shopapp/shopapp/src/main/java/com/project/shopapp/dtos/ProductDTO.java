package com.project.shopapp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
@Getter //toString hien thi thong tin chi tiest object
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO implements Serializable {
    @NotBlank(message = "Title is required")    //Khong duoc de trong bat phai nhap, gui cho message
    @Size(min = 3, max = 200, message = "Title must be between 3 and 200 characters")
    private String name;

    //Gia ca lon hon 0
    @Min(value = 0, message = "Price must be greater than or equal to 0")
    @Max(value = 10000000, message = "Price must be less than or equal 10.000.000")
    private Float price;
    private String thumbnail;
    private  String description;

    @JsonProperty("category_id")    //mapping sao cho phu voi truong du lieu trong DB
    private String categoryId;
    private MultipartFile file;
}
