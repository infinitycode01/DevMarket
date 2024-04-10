package com.infinity.devmarket.services;

import com.infinity.devmarket.contract.PaymentManagerWrapper;
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
public class ContractService {
    @Value("${owner.credentials}")
    private String ownerCredentials;
    @Value("${eth.server.url}")
    private String ethServerUrl;

    public Web3j connectToEthServer() {
        return Web3j.build(new HttpService(ethServerUrl));
    }

    public PaymentManagerWrapper deployContract(Web3j web3j) throws Exception {
        Credentials credentials = Credentials.create(ownerCredentials);
        return PaymentManagerWrapper.deploy(web3j, credentials, new DefaultGasProvider()).send();
    }

    public PaymentManagerWrapper loadContract(Web3j web3j, PaymentManagerWrapper ownerContract, Credentials credentials) {
        return PaymentManagerWrapper.load(getContractAddress(ownerContract), web3j, credentials, new DefaultGasProvider());
    }

    public String getContractAddress(PaymentManagerWrapper paymentManagerWrapper) {
        return paymentManagerWrapper.getContractAddress();
    }

    public BigDecimal getWalletBalance(Web3j web3j, String address) throws IOException {
        EthGetBalance ethGetBalance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
        BigInteger weiBalance = ethGetBalance.getBalance();
        return Convert.fromWei(new BigDecimal(weiBalance), Convert.Unit.ETHER);
    }
}
