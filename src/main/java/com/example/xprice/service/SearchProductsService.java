package com.example.xprice.service;

import com.example.xprice.model.dto.ProductPriceDTO;
import com.example.xprice.model.enums.MarketName;
import com.example.xprice.model.ProductIdMarketProductIdMapping;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchProductsService {
    private final List<MarketService> marketServiceList;

    public List<ProductPriceDTO> search(String productId) {
        List<ProductPriceDTO> productPriceList = new ArrayList<>();
        try {
            marketServiceList.forEach(marketService -> {
                    var marketProducts = ProductIdMarketProductIdMapping.productInfoMap.get(marketService.getMarketName());
                    var marketProductId = marketProducts.get(productId);
                    productPriceList.add(marketService.getProductPrice(marketProductId));
            });
        } catch (Exception e) {
            log.error("There is an error while getting product price list! {}", e);
        }
        productPriceList.sort(Comparator.comparing(ProductPriceDTO::getPrice));
        return productPriceList;
    }
}
