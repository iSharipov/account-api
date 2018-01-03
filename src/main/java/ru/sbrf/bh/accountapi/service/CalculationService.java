package ru.sbrf.bh.accountapi.service;

import java.math.BigDecimal;

public interface CalculationService {
    BigDecimal increaseAmount(BigDecimal currentAmount, BigDecimal augend);

    BigDecimal substractAmount(BigDecimal currentAmount, BigDecimal subtrahend);

    boolean isSufficientBalance(BigDecimal inputAmount, BigDecimal accountAmmount);
}
