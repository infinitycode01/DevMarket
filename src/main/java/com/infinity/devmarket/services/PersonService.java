package com.infinity.devmarket.services;

import com.infinity.devmarket.models.Person;
import com.infinity.devmarket.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.protocol.Web3j;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final ContractService contractService;
    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonService(ContractService contractService, PeopleRepository peopleRepository) {
        this.contractService = contractService;
        this.peopleRepository = peopleRepository;
    }

    @Transactional
    public void update(Long id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    public BigDecimal getBalance(String address) {
        Web3j web3j = contractService.connectToEthServer();
        Optional<BigDecimal> balance = contractService.getWalletBalance(web3j, address);
        return balance.orElse(null);
    }
}
