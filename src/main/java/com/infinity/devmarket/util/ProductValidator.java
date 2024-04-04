package com.infinity.devmarket.util;

import com.infinity.devmarket.models.Product;
import com.infinity.devmarket.services.ProductService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProductValidator implements Validator {
    private final ProductService productService;

    public ProductValidator(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return Product.class.equals(clazz);
    }

    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        Product product = (Product) target;

        if (productService.loadUserByName(product.getName()).isEmpty())
            return;

        errors.rejectValue("name", "", "Product by that name already exists");
    }
}
