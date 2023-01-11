package com.bilgeadam.commentAppJava2023.controller;

import com.bilgeadam.commentAppJava2023.repository.entity.Product;
import com.bilgeadam.commentAppJava2023.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    /**************************************************************************************************************/
    //7.Ürün fiyatı belli bir fiyatın üzerindekileri getirin
    //Greater than = büyük olanı getir
    @GetMapping("/gthan")
    public ResponseEntity<List<Product>> gthan(double price) {
        return ResponseEntity.ok(productService.findAllByGreaterThan(price));
    }

    /**************************************************************************************************************/

    //8.Son Kullanma tarihi geçmiş ürünleri listeleyen metot.
    @GetMapping("/expirationdatebefore")
    public ResponseEntity<List<Product>> expirationDateBefore() {
        return ResponseEntity.ok(productService.findAllByExpirationDate());
    }

    @GetMapping("/expirationdatebefore2")
    public ResponseEntity<List<Product>> expirationDateBefore2(String date) {
        return ResponseEntity.ok(productService.findAllByExpirationDate2(date));
    }

    /**************************************************************************************************************/
    //9.Son kullanma tarihi geçmemiş ürünleri listeleyen bir metot yazınız.(Son kullanma tarihi boş olanlar da gelmeli.)
    @GetMapping("/expirationdateafterornull")
    public ResponseEntity<List<Product>> findAllByExpirationDateAfterOrExpirationDateIsNull(String date) {
        return ResponseEntity.ok(productService.findAllByExpirationDateAfterOrExpirationDateIsNull(date));
    }

    /**************************************************************************************************************/

    //10.Belli bir fiyatta ki üründen kaç tane olduğunu ve o fiyatı beraber getiriniz(Query).
    @GetMapping("/searchbyprice")
    public ResponseEntity<Object[]> searchByProductPrice() {
        return ResponseEntity.ok(productService.searchByProductPrice());
    }

    @GetMapping("/searchbyprice2")
    public ResponseEntity<List<Object>> searchByProductPrice2() {
        return ResponseEntity.ok(productService.searchByProductPrice2());
    }

    /**************************************************************************************************************/
    //11.Belli bir son kullanma tarihine sahip kaç ürün vardır ?
    @GetMapping("/countbyexpirationdate")
    public ResponseEntity<Integer> countAllByExpirationDate(String date) {
        return ResponseEntity.ok(productService.countAllByExpirationDate(date));
    }

    /**************************************************************************************************************/
    //12.Ürün fiyatları içerisinde 100,700,50 olan ürünleri getiriniz.
    @GetMapping("/findbypricelist")
    public ResponseEntity<List<Product>> findAllByPriceIn() {
        List<Double> prices = new ArrayList<>();
        prices.add(100D);
        prices.add(700D);
        return ResponseEntity.ok(productService.findAllByPriceIn(prices));
    }

    //NOT Swagger dan değilde url den sorgulama yapacaksak List şeklinde alamıyoruz Array şeklind alabiliyoruz.
    //onun için List leri sırayla Array e çevirip yazman gerekiyor.
    @GetMapping("/findbypricelist2")
    public ResponseEntity<List<Product>> findAllByPriceIn(List<Double> prices) {
        return ResponseEntity.ok(productService.findAllByPriceIn(prices));
    }

    /**************************************************************************************************************/
    //13. Ürün 1 ve Ürün 2 ismine sahip ürünleri getiriniz.
    @GetMapping("/findproductbynames")
    public ResponseEntity<List<Product>> findAllByNameIn() {
        //parametreyi içerden verdik
        String[] names = {"Conon Yaizci", "Pak  Maya"};
        return ResponseEntity.ok(productService.findAllByNameIn(names));
    }

    @GetMapping("/findproductbynames2")                  //parametreyi dışarıdan verdik
    public ResponseEntity<List<Product>> findAllByNameIn(String[] names) {
        return ResponseEntity.ok(productService.findAllByNameIn(names));
    }

    /**************************************************************************************************************/
    /*14.Son kullanma tarihi geçmemiş ürünlerin arasından 6 ay içerisinde bitecek ürünlere %10 indirim yapan
    fonsiyonu yazınız service de*/
    @GetMapping("/expirationdatebetweendiscount")
    public ResponseEntity<List<Product>> findAllByExpirationDateBetween() {
        return ResponseEntity.ok(productService.findAllByExpirationDateBetween());
    }

    /**************************************************************************************************************/

    //findall metodu
    @GetMapping("/findall")
    public ResponseEntity<List<Product>> findall() {
        return ResponseEntity.ok(productService.findall());

    }

    //findbyid metot
    @GetMapping("/findbyid/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));

    }

    //delete metot
    @DeleteMapping("/delete")
    public void deleteById(Long id) {
        productService.deleteById(id);
    }

    //save metot
    @GetMapping("/save")
    public ResponseEntity<Product> save(String name, double price, String expirationDate) {
        return ResponseEntity.ok(productService.save(name, price, expirationDate));
    }

}



