package com.bilgeadam.commentAppJava2023.repository;

import com.bilgeadam.commentAppJava2023.repository.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

    /**************************************************************************************************************/
    //7.Ürün fiyatı belli bir fiyatın üzerindekileri getirin
    //Greater than = büyük olanı getir
    List<Product> findAllByPriceGreaterThan(double price);

    /**************************************************************************************************************/
    //8.Son Kullanma tarihi geçmiş ürünleri listeleyen metot.
    List<Product> findAllByExpirationDateBefore(LocalDate date);


    /**************************************************************************************************************/
    //9.Son kullanma tarihi geçmemiş ürünleri listeleyen bir metot yazınız.(Son kullanma tarihi boş olanlar da gelmeli.)

    List<Product> findAllByExpirationDateAfterOrExpirationDateIsNull(LocalDate date);

    /**************************************************************************************************************/
    //10.Belli bir fiyatta ki üründen kaç tane olduğunu ve o fiyatı beraber getiriniz(Query).
    @Query("select p.price,count(p.price) from Product as p group by p.price")
    Object[] searchByProductPrice();

    @Query("select p.price,count(p.price) from Product as p group by p.price")
    List<Object> searchByProductPrice2();

    /**************************************************************************************************************/
    //11.Belli bir son kullanma tarihine sahip kaç ürün vardır ?
    //count =saymak
    int countAllByExpirationDate(LocalDate date);

    /**************************************************************************************************************/
    //12.Ürün fiyatları içerisinde 100,700,50 olan ürünleri getiriniz.
    //In diyoruz ki veridiğimiz değerleri içine alarak arasın
    List<Product> findAllByPriceIn(List<Double> prices);

    /**************************************************************************************************************/
    //13. Ürün 1 ve Ürün 2 ismine sahip ürünleri getiriniz.
    List<Product> findAllByNameIn(String[] names);

    /**************************************************************************************************************/
    /*14.Son kullanma tarihi geçmemiş ürünlerin arasından 6 ay içerisinde bitecek ürünlere %10 indirim yapan
    fonsiyonu yazınız service de*/

    //Bu gibi sorularda 6 ay içerisinde bitecek ürünleri bulan query yazılıp listelenir, indirimi service de tanımlanır.
    // between = arasında, expirationdate = son kullanma tarihe göre
    List<Product> findAllByExpirationDateBetween(LocalDate now, LocalDate nextDate);
    /**************************************************************************************************************/


}
