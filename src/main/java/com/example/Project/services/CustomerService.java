package com.example.Project.services;

import com.example.Project.repositories.LoginRepository;
import com.example.Project.tables.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Project.repositories.CustomerRepository;
import com.example.Project.tables.Customer;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    public CustomerRepository customerRepository;

    @Autowired
    public LoginRepository loginRepository;
    protected static SecretKeySpec secretKey;
    protected static byte[] key;
    private static final String ALGORITHM = "AES";

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }


    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }


    public Customer findCustomerByUserId(long id) {
        return customerRepository.findCustomerByUserId(id);
    }


    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public Customer registerCustomer(String username, String password, String firstName, String lastName, String email, String phoneNumber, String address, String nationalIdNumber) {
        final String secretKey = "test_email_for_encryption@gmail.com";
        Users newUser = new Users();
        newUser.setUsername(username);
        newUser.setPassword(encrypt(password, secretKey));
        newUser.setUserType("Customer");
        newUser.setUserStatus("1");
        loginRepository.save(newUser);

        Customer newCustomer = new Customer();
        newCustomer.setFirstName(firstName);
        newCustomer.setLastName(lastName);
        newCustomer.setEmail(email);
        newCustomer.setPhoneNumber(phoneNumber);
        newCustomer.setAddress(address);
        newCustomer.setNationalIdNumber(nationalIdNumber);
        newCustomer.setUser_id(newUser.getUser_id());
        return customerRepository.save(newCustomer);
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
}

