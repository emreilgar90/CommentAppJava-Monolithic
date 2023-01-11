package com.bilgeadam.commentAppJava2023.repository;

import com.bilgeadam.commentAppJava2023.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
//findBy= bul , metot otomatik oluşturulamıyorsa @query yazıyoruz.
    //contiting = içerir mi ? var mı ?

    //1.Kullanıcıları isme göre sıralı getir.
    List<User> findAllByOrderByName();
    //User findByNameAndSurName(String name,String surname);

    /**************************************************************************************************************/

    //2.Dışarıdan girilen değer hangi kullanıcıların isimlerinde mevcuttur.
    Optional<List<User>> findAllOptionalByNameContainingIgnoreCase(String value);


    /**************************************************************************************************************/

    //3.Emaillerinin içinde belirlediğimiz değer geçen kullanıcılar.
    Optional<List<User>> findAllOptionalByEmailContainingIgnoreCase(String value);


    /**************************************************************************************************************/

    //4.Emailleri belirlediğimiz değere göre biten kullanıcılar. Ending With= Bununla bitiyorsa...
    Optional<List<User>> findAllOptionalByEmailEndingWithIgnoreCase(String value);


    /**************************************************************************************************************/

    //5.Email ve Passworde göre kullanıcı kontrolü LOGIN,3 farklı şekilde de yazılabilir.
    Optional<User> findOptionalByEmailAndPassword(String email, String password);


    @Query(value = "select * from tbl_user as u where u.email=?1,and u.password=?2", nativeQuery = true)
    Optional<User> findOptionalByEmailAndPassword2(String email, String password);


    @Query(value = "select * from tbl_user as u where u.email=:email,and u.password=:password", nativeQuery = true)
    Optional<User> findOptionalByEmailAndPassword3(@Param("email") String email, @Param("password") String password);

    /**************************************************************************************************************/


    //6.Passwordunun uzunluğu belirlediğimiz sayıdan buyuk olanlar(QUERY=sorgu)
    @Query("select u from User as u where (u.password)> ?1 ")
    List<User> controlPassword(int length);

    @Query("select u from User as u where (u.password)> :x ")
    List<User> controlPassword2(@Param("x") int length);


}
