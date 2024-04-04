package com.infinity.devmarket.controllers;

import com.infinity.devmarket.models.Product;
import com.infinity.devmarket.security.PersonDetails;
import com.infinity.devmarket.services.ProductService;
import com.infinity.devmarket.util.ProductValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final ProductValidator productValidator;

    @Autowired
    public ProductController(ProductService productService, ProductValidator productValidator) {
        this.productService = productService;
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
    public String update(@ModelAttribute("product") Product product, @PathVariable("id") Long id) {
        productService.update(id, product);
        return "redirect:/product";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return "redirect:/product";
    }




}
