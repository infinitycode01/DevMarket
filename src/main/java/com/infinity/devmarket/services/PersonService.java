package com.infinity.devmarket.services;

import com.infinity.devmarket.models.Person;
import com.infinity.devmarket.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;

import java.io.IOException;
import java.math.BigDecimal;

@Service
public class PersonService {
    @Value("${server.url}")
    private String serverUrl;
    private final PeopleRepository peopleRepository;
    private final PaymentService paymentService;

    @Autowired
    public PersonService(PeopleRepository peopleRepository, PaymentService paymentService) {
        this.peopleRepository = peopleRepository;
        this.paymentService = paymentService;
    }

    public BigDecimal getBalance(String address) throws IOException {
        Web3j web3j = paymentService.connectToServer(serverUrl);
        return paymentService.getWalletBalance(web3j, address);
    }
}
