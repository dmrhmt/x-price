package com.example.xprice.service.markets;

import com.example.xprice.model.dto.ProductPriceDTO;
import com.example.xprice.model.enums.MarketName;
import com.example.xprice.service.MarketService;
import org.jsoup.Jsoup;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class AkakceServiceImpl implements MarketService {
    @Override
    public ProductPriceDTO getProductPrice(String productId) {
        // TODO: this should be in a table
        var url = "https://api.akakce.com/p/?" + productId;
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.101 Safari/537.36");
        headers.set("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        headers.set("authority", "api.akakce.com");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        var priceNode = Jsoup.parse(response.getBody()).getElementsByTag("body").get(0).childNode(15).childNode(1).childNode(3).childNode(1);
        var productName = priceNode.childNode(0).childNode(0).attr("title");
        var productPrice = priceNode.childNode(0).childNode(0).childNode(1).childNode(1).childNode(0).toString();
        if (StringUtils.hasText(productPrice))
            productPrice = productPrice.replace(" TL", "");
        productName = new String(productName.getBytes(), StandardCharsets.UTF_8);
        return ProductPriceDTO
                .builder()
                .price(new BigDecimal(productPrice))
                .product(productName)
                .marketName(getMarketName())
                .lastUpdatedDate(new Date().toString())
                .build();
    }

    @Override
    public MarketName getMarketName() {
        return MarketName.AKAKCE;
    }
}
