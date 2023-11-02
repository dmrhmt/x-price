package com.example.xprice.service.markets;

import com.example.xprice.model.dto.ProductPriceDTO;
import com.example.xprice.model.dto.TrendyolResponseDTO;
import com.example.xprice.model.enums.MarketName;
import com.example.xprice.service.MarketService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class TrendyolServiceImpl implements MarketService {
    @Override
    public ProductPriceDTO getProductPrice(String productId) {
        var url = "https://public.trendyol.com/discovery-web-websfxproductgroups-santral/api/v1/product-groups/" + productId;
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.101 Safari/537.36");
        headers.set("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TrendyolResponseDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, TrendyolResponseDTO.class);
        var productPriceDto = ProductPriceDTO
                .builder()
                .price(BigDecimal.ZERO)
                .product("")
                .marketName(getMarketName())
                .build();
        for (var slicingAttribute : response.getBody().getResult().getSlicingAttributes()) {
            for (var attribute : slicingAttribute.getAttributes()) {
                for (var content : attribute.getContents()) {
                    var sellingPrice = content.getPrice().getSellingPrice().getValue();
                    if (sellingPrice.compareTo(productPriceDto.getPrice().intValue()) < 0 || BigDecimal.ZERO.equals(productPriceDto.getPrice()))
                        productPriceDto = ProductPriceDTO
                                .builder()
                                .price(new BigDecimal(sellingPrice))
                                .product(content.getName())
                                .marketName(getMarketName())
                                .lastUpdatedDate(new Date().toString())
                                .build();
                }
            }
        }
        return productPriceDto;
    }

    @Override
    public MarketName getMarketName() {
        return MarketName.TRENDYOL;
    }
}
