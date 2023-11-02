package com.example.xprice.model;

import com.example.xprice.model.enums.MarketName;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public class ProductIdMarketProductIdMapping {
    // TODO: this should be in a table
    public static final Map<MarketName, Map<String, String>> productInfoMap = new HashMap<>() {{
        put(MarketName.AKAKCE, new HashMap() {{
            put("1", "882468581"); //productId - marketProductId
        }});
        put(MarketName.TRENDYOL, new HashMap() {{
            put("1", "58204786"); //productId - marketProductId
        }});
    }};
}
