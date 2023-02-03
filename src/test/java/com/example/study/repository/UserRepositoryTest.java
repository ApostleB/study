package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {

    //DI
    @Autowired
    private UserRepository userRepository;
    @Test
    public void create(){
        // String sql = insert into user (%s, %s, %d) value(account, email, age);
        User user = new User();
        user.setAccount("TEST USER 01");
        user.setPhoneNumber("010-5099-1699");
        user.setEmail("Test@test.com");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("Developer");

        User newUser = userRepository.save(user);
        System.out.println("nweUser : "+ newUser);

    }
    @Test
    public void read(){
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser ->{
            System.out.println("user : " + selectUser );
            System.out.println("email : " + selectUser.getEmail() );
        });
    }
    @Test
    public void update(){
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser ->{
            selectUser.setAccount("aaaa");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });
        System.out.println(user);

    }
    @Test
    public void delete(){
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser -> {
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(2L);
        if(deleteUser.isPresent()){
            System.out.println("데이터 존재 : " + deleteUser.get());
        }else{
            System.out.println("데이터가 삭제되어 존재하지 않음");
        }
    }
}
