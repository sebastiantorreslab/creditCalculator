package com.app.creditCalculator.service;

import com.app.creditCalculator.domain.Credit;
import com.app.creditCalculator.repository.CreditRepository;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public interface ICreditService {

    void create(Credit credit);
    Optional<Credit> findById(String  id);
    List<Credit> findAll();
    List<Credit> findAllCreditsByCreditType(String creditType);
    Optional<Credit> update(Credit credit);
    Double calculateFutureValue(Credit credit);
    public Double calculatePayment(Credit credit);
    void deleteById(String id);


}
