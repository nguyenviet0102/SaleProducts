package com.project.shopapp.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Getter //toString hien thi thong tin chi tiest object
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    @NotEmpty(message = "category's can't empty")
    private String name;    //Ten cua danh muc sp
}
