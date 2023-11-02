package com.example.xprice.model.dto;

import com.example.xprice.model.enums.MarketName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPriceDTO implements Serializable {
    private BigDecimal price;
    private String product;
    private MarketName marketName;
    private String lastUpdatedDate;
}
