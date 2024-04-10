package com.infinity.devmarket.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;

import java.io.IOException;
import java.math.BigDecimal;

@Service
public class PersonService {
    private final ContractService contractService;

    @Autowired
    public PersonService(ContractService contractService) {
        this.contractService = contractService;
    }

    public BigDecimal getBalance(String address) throws IOException {
        Web3j web3j = contractService.connectToEthServer();
        return contractService.getWalletBalance(web3j, address);
    }
}
