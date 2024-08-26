package com.project.shopapp.models;

import jakarta.persistence.*;
import lombok.*;

@Entity //khai bao entity trong DB
@Table(name = "product_images") //anh xa lai ten trong Db
@Getter
@Setter
@Builder
@AllArgsConstructor //method khoi tao co tham so
@NoArgsConstructor  //method khoi tao khong co tham so
public class ProductImage {
    @Id //Khai bao khoa chinh
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne  //n-1
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "image_url", length = 300)
    private String imageUrl;
}
