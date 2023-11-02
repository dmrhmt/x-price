package com.example.xprice.service;

import com.example.xprice.model.dto.ProductPriceDTO;
import com.example.xprice.model.enums.MarketName;

public interface MarketService {
    ProductPriceDTO getProductPrice(String productId);
    MarketName getMarketName();
}
