package com.infinity.devmarket.services;

import com.infinity.devmarket.contract.PaymentManagerWrapper;
import org.springframework.stereotype.Service;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;

@Service
public class PaymentService {
    public void pay(PaymentManagerWrapper contract, BigDecimal priceEth) throws Exception {
        BigInteger priceWei = Convert.toWei(priceEth, Convert.Unit.ETHER).toBigInteger();
        contract.pay(priceWei).send();
    }

    public void withdraw(PaymentManagerWrapper ownerContract, String address) throws Exception {
        ownerContract.withdraw(address).send();
    }
}
