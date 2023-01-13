package com.bilgeadam.commentAppJava2023.service;

import com.bilgeadam.commentAppJava2023.exception.CommentAppException;
import com.bilgeadam.commentAppJava2023.exception.ErrorType;
import com.bilgeadam.commentAppJava2023.repository.IUserRepository;
import com.bilgeadam.commentAppJava2023.repository.entity.Product;
import com.bilgeadam.commentAppJava2023.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {
    //isPresent = varsa
    private final IUserRepository userRepository;
    private final ProductService productService;

    //1.Kullanıcıları isme göre sıralı getir.
    public List<User> findOrderByName() {
        return userRepository.findAllByOrderByName();

    }

    //2.Dışarıdan girilen değer hangi kullanıcıların isimlerinde mevcuttur.
    public Optional<List<User>> findAllByNameContains(String value) {
        Optional<List<User>> list = userRepository.findAllOptionalByNameContainingIgnoreCase(value);
        if (list.get().size() == 0) {
            throw new CommentAppException(ErrorType.VALUE_NOT_FOUND_IN_NAMES);
        } else {
            //Optional.empty();
            //return Optional.empty();
            return list;
        }

    }

    //3.Emaillerinin içinde belirlediğimiz değer geçen kullanıcılar.
    public Optional<List<User>> findAllByEmailContains(String value) {
        Optional<List<User>> list = userRepository.findAllOptionalByEmailContainingIgnoreCase(value);
        if (list.get().size() == 0) {
            throw new CommentAppException(ErrorType.VALUE_NOT_FOUND_IN_EMAILS);
        } else {
            //Optional.empty();
            //return Optional.empty();
            return list;
        }

    }

    //4.Emailleri belirlediğimiz değere göre biten kullanıcılar. Ending With= Bununla bitiyorsa...
    public Optional<List<User>> findAllByEmailEnding(String value) {
        Optional<List<User>> list = userRepository.findAllOptionalByEmailContainingIgnoreCase(value);
        if (list.get().size() == 0) {
            throw new CommentAppException(ErrorType.VALUE_NOT_FOUND_IN_EMAILS);
        } else {
            //Optional.empty();
            //return Optional.empty();
            return list;
        }

    }

    //5.Email ve Passworde göre kullanıcı kontrolü LOGIN
    public Optional<User> login(String email, String password) {
        Optional<User> optionalUser = userRepository.findOptionalByEmailAndPassword(email, password);
        if (optionalUser.isPresent()) {
            return optionalUser;
        }
        throw new CommentAppException(ErrorType.USER_NOT_FOUND);
    }

    //6.Passwordunun uzunluğu belirlediğimiz sayıdan buyuk olanlar(QUERY=sorgu)
    public List<User> passwordControl(int length) {
        return userRepository.controlPassword(length);
    }

    public List<User> passwordControl2(int length) {
        return userRepository.controlPassword2(length);
    }

    /*public void saveAll(List<User> user) {
        userRepository.saveAll(user);  //saveAll jpaRepository den gelen otomatik metotlardan biri
    }*/

    /*************USER için SAVE METODU **************************/
    public User save(String name, String surName, String email, String password) {
        try {
            User user = userRepository.save(User.builder().name(name).surName(surName).email(email).password(password).build());
            return userRepository.save(user);
        } catch (Exception e) {
            throw new CommentAppException(ErrorType.USER_NOT_CREATED);
        }

    }

    /*************USER için FINDALL METODU **************************/

    public List<User> findall() {
        return userRepository.findAll();  //userRepository de findAll metodu var
    }

    /*************USER için FINDBYID METODU **************************/

    public Optional<User> findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user;

        } else {
            throw new CommentAppException(ErrorType.USER_NOT_FOUND);
        }
    }


    /*************USER için DeleteById METODU **************************/
    public void deleteById(Long id) {
        Optional<User> user = userRepository.findById(id);//verilen id ye göre ara
        if (user.isPresent()) {  //isPresent yani varsa id'ye göre siliyoruz.
            userRepository.deleteById(id);
        } else {  //yoksa da hata fırlatıyoruz.
            throw new CommentAppException(ErrorType.USER_NOT_FOUND);
        }

    }//önce findById buluyoruz varsa siliyoruz, bulamadıysak kullanıcı bulunamadı hatası atıyoruz.

    /************************************************************************/
    public void saveAll(List<User> user) {

        userRepository.saveAll(user);
    }


    /************************************************************************/
    //Her User ın farklı favori listesi var bunun için metot yazıyoruz.Listeliyor.
    public List<Long> getFavProduct(long userId) {
        Optional<User> user = userRepository.findById(userId);//verilen id ye göre ara
        if (user.isEmpty()) { //isEmpty = yoksa hata fırlatsın.
            throw new CommentAppException(ErrorType.USER_NOT_FOUND); //yoksa hata fırlat
        } else {                                                      //varsa getir.
            return user.get().getFavProduct();
        }


    }


    public void addFavProduct(long userId, long productId) {
        Optional<Product> product = productService.findById(productId);
        Optional<User> user = userRepository.findById(userId);//verilen id ye göre ara
        if (user.isEmpty()) { //isEmpty = yoksa hata fırlatsın.
            throw new CommentAppException(ErrorType.USER_NOT_FOUND);
        }
        if (product.isEmpty()) {
            throw new CommentAppException(ErrorType.PRODUCT_NOT_FOUND);
        }

        //FAVORİYE DAHA ÖNCE ALINAN ÜRÜNÜ UYARAN METOT,ÜRÜN ZATEN VAR UYARISI VERİYOR.
        if (user.get().getFavProduct().contains(productId)) {
            throw new CommentAppException(ErrorType.PRODUCT_ALREADY_EXİSTS_IN_FAVORITE_LIST);
        } else {
            user.get().getFavProduct().add(productId);
            userRepository.save(user.get());
        }


    }


    //HAZIR GELEN METODLARI SERVICEden başlatabilirsin Repository e gerek yok!
}
