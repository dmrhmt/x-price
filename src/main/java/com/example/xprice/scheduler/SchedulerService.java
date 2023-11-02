package com.example.xprice.scheduler;

import com.example.xprice.model.ProductIdMarketProductIdMapping;
import com.example.xprice.model.dto.ProductPriceDTO;
import com.example.xprice.service.MarketService;
import com.example.xprice.service.SearchProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SchedulerService {
    @Scheduled(fixedRate = 60000)
    @CacheEvict(value = "products", allEntries = true)
    public void updateProductPrice() {}
}
