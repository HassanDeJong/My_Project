package com.example.Project.services;

import com.example.Project.tables.Customer;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.Project.dtos.loginDTO;
import com.example.Project.repositories.LoginRepository;
import com.example.Project.tables.Users;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class LoginServices
{
    @Autowired
    public LoginRepository login_repository;
    protected static SecretKeySpec secretKey;
    protected static byte[] key;
    private static final String ALGORITHM = "AES";


    public Users registerNewAdmin(String username, String password) {
        final String secretKey = "test_email_for_encryption@gmail.com";
        Users newUser = new Users();
        newUser.setUsername(username);
        newUser.setPassword(encrypt(password, secretKey));
        newUser.setUserType("Admin");
        newUser.setUserStatus("1");
        return login_repository.save(newUser);
    }

    public void prepareSecreteKey(String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes(StandardCharsets.UTF_8);
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String encrypt(String strToEncrypt, String secret) {
        try {
            prepareSecreteKey(secret);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public Users saveUsers(loginDTO lgn){
        final String secretKey = "test_email_for_encryption@gmail.com";
        Users l = new Users();
        l.setPassword(encrypt(lgn.getPassword(),secretKey));
        l.setUsername(lgn.getUsername()); l.setUserStatus("1");
        l.setUserType(lgn.getUserType());
        Optional<Users> check = login_repository.check_existing_user(lgn.getUsername());
        if(check.isPresent()){
            throw new ResponseStatusException(HttpStatus.FOUND,"User exist");
        }else{
            return login_repository.save(l);
        }
    }

    @Transactional
    public Optional<Users> updateLoginData(int user_id,loginDTO dto){
        return login_repository.findById(user_id).map(l->{
            l.setPassword(dto.getPassword());
            l.setUserType(dto.getUserType());
            l.setUsername(dto.getUsername());
            l.setUserStatus(dto.getUserStatus());
            return l;
        });
    }
    @Transactional
    public Optional<Users> resetPassword(int user_id,loginDTO dto){
        return login_repository.findById(user_id).map(l->{
            final String secretKey = "test_email_for_encryption@gmail.com";
            l.setPassword(encrypt(dto.getPassword(),secretKey));
            l.setUserType(dto.getUserType());
            l.setUsername(dto.getUsername());
            l.setUserStatus(dto.getUserStatus());
            return l;
        });
    }


    public Users login_authentication(String username, String password){
        final String secretKey = "test_email_for_encryption@gmail.com";
        String encrypted_password = encrypt(password,secretKey);
//        String status = "1";
        Optional<Users> l = login_repository.login_authentication(username,encrypted_password);
        if(l.isPresent()){
            return l.get();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User was not found");
        }
    }

    public Users getLoginDataByUserId(int user_id){
        Optional<Users> l = login_repository.findById(user_id);
        return l.orElseGet(Users::new);
    }

    public List<Users> getAllUsers(){
        List<Users> l = login_repository.findAll();
        if(l.size() > 0){
            return l;
        }else{
            return new ArrayList<>();
        }
    }
}


