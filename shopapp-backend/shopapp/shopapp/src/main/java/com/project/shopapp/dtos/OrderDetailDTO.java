package com.project.shopapp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.*;

@Data   //Tu goi ham ToString de show ra tat ca thuoc tinh, gia tri thuoc tinh
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetailDTO {
    @JsonProperty("order_id")  //gui tren postmen
    @Min(value = 1, message = ("Order's must be > 0"))
    private Long orderId;   //khai bao theo java

    @JsonProperty("product_id")
    @Min(value = 1, message = ("Product's must be > 0"))
    private Long productId;

    @Min(value = 0, message = ("Price's must be >= 0"))
    private Long price;

    @JsonProperty("number_of_product")
    @Min(value = 1, message = ("number_of_product's must be >= 1"))
    private int numberOfProduct;

    @JsonProperty("total_money")
    @Min(value = 0, message = ("Must be >= 0"))
    private int totalMoney;
    private String color;
}
