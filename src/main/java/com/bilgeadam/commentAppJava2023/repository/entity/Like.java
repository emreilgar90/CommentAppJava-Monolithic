package com.bilgeadam.commentAppJava2023.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_like")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //private long userId; //hangi kullanıcı like attı, User user2ı yaptıktan sonra yoruma aldım.
    private long productId;  //hangi ürün like landı.

    @Builder.Default
    private LocalDate likedDate = LocalDate.now(); //now ile kullanıldığı zamanı alabiliyoruz.

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user; //bir like'ın bir user ı olacak
}
