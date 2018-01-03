package ru.sbrf.bh.accountapi.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculationServiceImpl implements CalculationService {
    @Override
    public BigDecimal increaseAmount(BigDecimal currentAmount, BigDecimal augend) {
        return currentAmount.add(augend);
    }

    @Override
    public BigDecimal substractAmount(BigDecimal currentAmount, BigDecimal subtrahend) {
        return currentAmount.subtract(subtrahend);
    }

    @Override
    public boolean isSufficientBalance(BigDecimal inputAmount, BigDecimal accountAmmount) {
        return accountAmmount.compareTo(BigDecimal.ZERO) > 0
                && (accountAmmount.compareTo(inputAmount) > 0
                || accountAmmount.compareTo(inputAmount) == 0);
    }
}
