package com.example.xprice.controller;

import com.example.xprice.model.dto.ProductPriceDTO;
import com.example.xprice.service.SearchProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SearchProductsController {
    private final SearchProductsService searchProductsService;
    @GetMapping("/product-prices")
    @Cacheable(value = "products")
    public List<ProductPriceDTO> searchProducts(@RequestParam String productId) {
        return searchProductsService.search(productId);
    }

}
