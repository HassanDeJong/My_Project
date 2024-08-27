package com.example.Project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Project.services.PaymentService;
import com.example.Project.tables.Payments;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin
public class PaymentController {

    @Autowired
    public PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<Payments> createPayment(@RequestBody Payments payment) {
        Payments createdPayment = paymentService.savePayment(payment);
        return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Payments>> getAllPayments() {
        List<Payments> payments = paymentService.getAllPayments();
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payments> getPaymentById(@PathVariable Long id) {
        Optional<Payments> payment = paymentService.getPaymentById(id);
        return payment.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payments> updatePayment(@PathVariable Long id, @RequestBody Payments payment) {
        Payments updatedPayment = paymentService.updatePayment(id, payment);
        return updatedPayment != null
                ? new ResponseEntity<>(updatedPayment, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/all-payments-booking")
    public ResponseEntity<List<Payments>> getAllPaymentsHouseRenting() {
        List<Payments> payments = paymentService.getAllPaymentsHouseRenting();
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    @GetMapping("/all-payments-buying")
    public ResponseEntity<List<Payments>> getAllPaymentsHouseBuying() {
        List<Payments> payments = paymentService.getAllPaymentsHouseBuying();
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }
}

