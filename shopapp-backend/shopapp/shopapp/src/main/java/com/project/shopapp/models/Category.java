package com.project.shopapp.models;

import jakarta.persistence.*;
import lombok.*;

@Entity //khai bao entity trong DB
@Table(name = "categories") //anh xa lai ten trong Db
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor //method khoi tao co tham so
@NoArgsConstructor  //method khoi tao khong co tham so
public class Category {
    @Id //Khai bao khoa chinh
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Khong co ban ghi nao giong nhau
//    @Column(name = "id")  match  ten voi DB
    private Long id;

    @Column(name = "name", nullable = false)    //Bat buoc phai nhap
    private String name;
}
