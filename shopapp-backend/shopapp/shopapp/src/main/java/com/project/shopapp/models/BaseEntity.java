package com.project.shopapp.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor //method khoi tao co tham so
@NoArgsConstructor  //method khoi tao khong co tham so
@MappedSuperclass
//@Builder
public class BaseEntity {
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @PrePersist //Gia tri tu dong cap nhat
    protected void onCreate(){
        createdAt = LocalDateTime.now();
        updateAt = LocalDateTime.now();
    }
//    @PrePersist
//    protected void onUpdate(){
//
//        updateAt = LocalDateTime.now();
//    }
}
