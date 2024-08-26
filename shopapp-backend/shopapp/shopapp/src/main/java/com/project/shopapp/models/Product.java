package com.project.shopapp.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity //khai bao entity trong DB
@Table(name = "products") //anh xa lai ten trong Db
//@Data   //ToString
@Getter
@Setter
@Builder
@AllArgsConstructor //method khoi tao co tham so
@NoArgsConstructor  //method khoi tao khong co tham so
public class Product extends BaseEntity{
    @Id //Khai bao khoa chinh
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Khong co ban ghi nao giong nhau
//    @Column(name = "id")  match  ten voi DB
    private Long id;

    @Column(name = "name", nullable = false, length = 350)    //Bat buoc phai nhap
    private String name;

    private Float price;

    @Column(name = "thumbnail", length = 300)  //Khong yeu cau phai nhap ngay
    private String thumbnail;

    @Column(name = "description")
    private String description;

    @ManyToOne  //n-1
    @JoinColumn(name = "category_id")
    private Category CategoryId;
}
