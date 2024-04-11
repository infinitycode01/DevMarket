package com.infinity.devmarket.controllers;

import com.infinity.devmarket.contract.PaymentManagerWrapper;
import com.infinity.devmarket.models.Product;
import com.infinity.devmarket.security.PersonDetails;
import com.infinity.devmarket.services.ContractService;
import com.infinity.devmarket.services.PaymentService;
import com.infinity.devmarket.services.ProductService;
import com.infinity.devmarket.util.ProductValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Value("${owner.address}")
    private String ownerAddress;
    private final ContractService contractService;
    private final ProductService productService;
    private final PaymentService paymentService;
    private final ProductValidator productValidator;

    @Autowired
    public ProductController(ContractService contractService, ProductService productService, PaymentService paymentService, ProductValidator productValidator) {
        this.contractService = contractService;
        this.productService = productService;
        this.paymentService = paymentService;
        this.productValidator = productValidator;
    }

    @GetMapping
    public String showProducts(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        model.addAttribute("role", personDetails.getRole());
        model.addAttribute("products", productService.findAll());
        return "product/index";
    }

    @GetMapping("/{id}")
    public String viewProductById(@PathVariable("id") Long id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        model.addAttribute("role", personDetails.getRole());
        model.addAttribute("product", productService.findById(id));
        return "product/show";
    }

    @GetMapping("/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String newProduct(@ModelAttribute("product") Product product) {
        return "product/new";
    }

    @PostMapping("/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String create(@ModelAttribute("product") @Valid Product product,
                         BindingResult bindingResult) {
        productValidator.validate(product, bindingResult);

        if (bindingResult.hasErrors())
            return "/product/new";

        productService.save(product);

        return "redirect:/product";
    }

    @GetMapping("/{id}/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "product/edit";
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String update(@ModelAttribute("product") Product product,
                         @PathVariable("id") Long id) {
        productService.update(id, product);
        return "redirect:/product";
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return "redirect:/product";
    }


    @GetMapping("/{id}/buy")
    public String buyPage(@PathVariable("id") Long id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        model.addAttribute("person", personDetails.getPerson());
        model.addAttribute("product", productService.findById(id));
        return "product/buy";
    }

    @PostMapping("/{id}/buy")
    public String buyProduct(@PathVariable("id") Long id,
                             @RequestParam("privateKey") String privateKey) {
        try {
            Web3j web3j = contractService.connectToEthServer();
            PaymentManagerWrapper ownerContract = contractService.deployContract(web3j);
            Credentials userCredential = Credentials.create(privateKey);

            paymentService.pay(contractService.loadContract(web3j, ownerContract, userCredential),
                    productService.findById(id).getPrice());
            paymentService.withdraw(ownerContract, ownerAddress);
        } catch (Exception e) {
            return "redirect:/product/payment_error";
        }
        return "redirect:/product";
    }

    @GetMapping("/payment_error")
    public String errorPage() {
        return "product/payment_error";
    }
}