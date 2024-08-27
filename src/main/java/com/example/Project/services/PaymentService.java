package com.example.Project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Project.repositories.PaymentRepository;
import com.example.Project.tables.Payments;

import java.util.*;

@Service
public class PaymentService {

    @Autowired
    public PaymentRepository paymentRepository;


    public static String generateNumber(String pattern) {
        List<Character> characters = new ArrayList<>();
        for (char c : pattern.toCharArray()) {
            if (Character.isDigit(c)) {
                characters.add(c);
            }
        }

        long seed = System.nanoTime();
        Collections.shuffle(characters, new Random(seed));

        StringBuilder generatedNumber = new StringBuilder(pattern);
        int index = 0;
        for (char c : generatedNumber.toString().toCharArray()) {
            if (Character.isDigit(c)) {
                generatedNumber.setCharAt(index, characters.get(0));
                characters.remove(0);
            }
            index++;
        }
        return generatedNumber.toString();
    }

    public Payments savePayment(Payments payment) {

        if (payment.getHouseBuying() != null && payment.getHouseBooking() != null) {
            throw new IllegalArgumentException("Payment cannot be associated with both house buying and house booking.");
        }

        if (payment.getHouseBuying() == null && payment.getHouseBooking() == null) {
            throw new IllegalArgumentException("Payment must be associated with either house buying or house booking.");
        }

        if(payment.getHouseBooking() != null){
            payment.setHouseBuying(null);
        }
        if(payment.getHouseBuying() != null){
            payment.setHouseBooking(null);
        }
        String pattern = "#9874563210";
        String generatedNumber = generateNumber(pattern);
        payment.setControllNumber(generatedNumber);
        return paymentRepository.save(payment);
    }

    public List<Payments> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Optional<Payments> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }


    public Payments updatePayment(Long id, Payments payment) {
        if (paymentRepository.existsById(id)) {
            payment.setPayment_id(id);
            return paymentRepository.save(payment);
        }
        return null;
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    public List<Payments> getAllPaymentsHouseBuying() {
        return paymentRepository.getAllPaymentsHouseBuying();
    }

    public List<Payments> getAllPaymentsHouseRenting() {
        return paymentRepository.getAllPaymentsHouseRenting();
    }
}

