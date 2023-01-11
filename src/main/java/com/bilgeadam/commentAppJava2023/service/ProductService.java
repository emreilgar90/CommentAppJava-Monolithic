package com.bilgeadam.commentAppJava2023.service;

import com.bilgeadam.commentAppJava2023.exception.CommentAppException;
import com.bilgeadam.commentAppJava2023.exception.ErrorType;
import com.bilgeadam.commentAppJava2023.repository.IProductRepository;
import com.bilgeadam.commentAppJava2023.repository.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final IProductRepository productRepository;

    public void saveAll(List<Product> product) {

        productRepository.saveAll(product);
    }

    /**************************************************************************************************************/

    //7.Ürün fiyatı belli bir fiyatın üzerindekileri getirin
    //Greater than = büyük olanı getir
    public List<Product> findAllByGreaterThan(double price) {
        return productRepository.findAllByPriceGreaterThan(price);
    }

    /**************************************************************************************************************/
    //8.Son Kullanma tarihi geçmiş ürünleri listeleyen metot.
    public List<Product> findAllByExpirationDate() {
        //service'in kendi metodu,repository den alıyor,dışarıdan veri almadan bunu
        return productRepository.findAllByExpirationDateBefore(LocalDate.now());
    }

    //*Şu tarihten önceki leri getir dersek ?
    //dışarıdan veri alarak bunu kullanıyoruz.
    public List<Product> findAllByExpirationDate2(String date) {
        //String date i local date e çevirme yöntemi.
        LocalDate newDate = LocalDate.parse(date);
        return productRepository.findAllByExpirationDateBefore(newDate);
    }

    /**************************************************************************************************************/
    //9.Son kullanma tarihi geçmemiş ürünleri listeleyen bir metot yazınız.(Son kullanma tarihi boş olanlar da gelmeli.)
    public List<Product> findAllByExpirationDateAfterOrExpirationDateIsNull(String date) {
        LocalDate newDate = LocalDate.parse(date);
        return productRepository.findAllByExpirationDateAfterOrExpirationDateIsNull(newDate);
    }

    /**************************************************************************************************************/
    //10.Belli bir fiyatta ki üründen kaç tane olduğunu ve o fiyatı beraber getiriniz(Query).
    public Object[] searchByProductPrice() {
        return productRepository.searchByProductPrice();
    }

    public List<Object> searchByProductPrice2() {

        return productRepository.searchByProductPrice2();
    }

    /**************************************************************************************************************/
    //11.Belli bir son kullanma tarihine sahip kaç ürün vardır ?
    //dışarıdan değer alacağımız için String date olarak girdik.
    public int countAllByExpirationDate(String date) {
        return productRepository.countAllByExpirationDate(LocalDate.parse(date));
    }

    /**************************************************************************************************************/

    //12.Ürün fiyatları içerisinde 100,700,50 olan ürünleri getiriniz.
    public List<Product> findAllByPriceIn(List<Double> prices) {
        return productRepository.findAllByPriceIn(prices);
    }

    /**************************************************************************************************************/
    //13. Ürün 1 ve Ürün 2 ismine sahip ürünleri getiriniz.
    public List<Product> findAllByNameIn(String[] names) {
        return productRepository.findAllByNameIn(names);
    }

    /**************************************************************************************************************/
    /*14.Son kullanma tarihi geçmemiş ürünlerin arasından 6 ay içerisinde bitecek ürünlere %10 indirim yapan
    fonsiyonu yazınız service de*/
    //29.12.2022 dersi 1.50 de çözüyor!!!!!!!
    public List<Product> findAllByExpirationDateBetween() {
        LocalDate now = LocalDate.now();
        LocalDate nextDate = LocalDate.now().plusMonths(6);
        //.plus dedikten sonra ister ay ister gün ister yıl
        List<Product> products = productRepository.findAllByExpirationDateBetween(now, nextDate);
        products.stream().forEach(x -> {
            x.setPrice(x.getPrice() * 0.9); //fiyatı güncelledik
            productRepository.save(x); //güncellediğimiz fiyatı db e kaydettik,burası önemli 1
        });
        return products;
        //   return products.stream().map(y -> {
        //     y.setPrice(y.getPrice() * 0.9);
        //     return productRepository.save(y);
        // }).collect(Collectors.toList());

    }

    /************************************************************************************************************/
    /*FindAll metot*/
    public List<Product> findall() {
        return productRepository.findAll();

    }

    /*FindById*/
    public Optional<Product> findById(Long id) {

        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product;
        } else {
            throw new CommentAppException(ErrorType.PRODUCT_NOT_FOUND);
        }
    }

    public Product deleteById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new CommentAppException(ErrorType.PRODUCT_NOT_FOUND);
        }


    }

    public Product save(String name, double price, String expirationDate) {
        try {
            if (expirationDate.isBlank()) {
                return productRepository.save(Product.builder().name(name).price(price).build());
            } else {
                return productRepository.save(Product.builder().name(name).price(price).expirationDate(LocalDate.parse(expirationDate)).build());
            }
        } catch (Exception e) {
            throw new CommentAppException(ErrorType.PRODUCT_NOT_CREATED);
        }

    }


}



