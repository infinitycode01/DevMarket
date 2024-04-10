package com.infinity.devmarket.services;

import com.infinity.devmarket.contract.PaymentManagerWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;

import java.io.IOException;
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
