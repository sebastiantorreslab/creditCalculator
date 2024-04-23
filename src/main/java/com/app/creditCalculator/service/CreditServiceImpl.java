package com.app.creditCalculator.service;

import com.app.creditCalculator.domain.Credit;
import com.app.creditCalculator.repository.CreditRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditServiceImpl implements ICreditService {

    private final CreditRepository creditRepository;

    public CreditServiceImpl(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    @Override
    public void create(Credit credit) {
        creditRepository.save(credit);

    }

    @Override
    public Optional<Credit> findById(String id) {
        return creditRepository.findById(id);
    }

    @Override
    public List<Credit> findAll() {
        return creditRepository.findAll();
    }

    @Override
    public List<Credit> findAllCreditsByCreditType(String creditType) {
        return creditRepository.findAllCreditsByCreditType(creditType);
    }

    @Override
    public Optional<Credit> update(Credit credit) {
        Optional<Credit> credit1 = creditRepository.findById(credit.getId());
        credit1.ifPresent(creditRepository::save);
        return credit1;
    }

    @Override
    public Double calculateFutureValue(Credit credit) {
        try {
            return credit.getAmount() * Math.pow((1 + credit.getInterestRate()), credit.getLoanTerm());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.00;
    }

    @Override
    public Double calculatePayment(Credit credit) {
        Double payment = (credit.getAmount() * credit.getInterestRate() * Math.pow(1 +
                         credit.getInterestRate(), credit.getLoanTerm()))
                        / (Math.pow(1+ credit.getInterestRate(),credit.getLoanTerm()) - 1) ;

        return payment;
    }


    @Override
    public void deleteById(String id) {
        if (id != null) {
            creditRepository.deleteById(id);
        }

    }


}
