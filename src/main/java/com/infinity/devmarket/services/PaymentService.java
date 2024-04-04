package com.infinity.devmarket.services;

import com.infinity.devmarket.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

@Service
public class PaymentService {
    @Value("${owner.address}")
    private String ownerAddress;
    private final EncodeService encodeService;

    @Autowired
    public PaymentService(EncodeService encodeService) {
        this.encodeService = encodeService;
    }

    public Web3j connectToServer(String serverUrl) {
        return Web3j.build(new HttpService(serverUrl));
    }

    public void pay(Web3j web3j, BigDecimal price) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        String encodedPrivateKey = personDetails.getPrivateKey();

        Credentials credentials = Credentials.create(encodeService.decode(encodedPrivateKey));

        Transfer.sendFunds(web3j, credentials, ownerAddress, price, Convert.Unit.ETHER).send();
    }

    public BigDecimal getWalletBalance(Web3j web3j, String address) throws IOException {
        EthGetBalance ethGetBalance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
        BigInteger weiBalance = ethGetBalance.getBalance();
        return Convert.fromWei(new BigDecimal(weiBalance), Convert.Unit.ETHER);
    }

}
