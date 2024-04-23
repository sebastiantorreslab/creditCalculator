package com.app.creditCalculator.repository;

import com.app.creditCalculator.domain.Credit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditRepository extends MongoRepository<Credit, String> {

    List<Credit> findAllCreditsByCreditType(String creditType);






}
