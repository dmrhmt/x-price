package com.example.xprice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrendyolResponseDTO {
    private boolean isSuccess;
    private Integer statusCode;
    private Object error;
    private Result result;
    private Headers headers;

    @Data
    public static class Result{
        private ArrayList<SlicingAttribute> slicingAttributes;
    }
    @Data
    public static class Headers{
    }
    @Data
    public static class SlicingAttribute{
        private Brand brand;
        private ArrayList<Attribute> attributes;
        private String type;
        private String displayName;
        private Integer order;
        private Integer displayType;
    }
    @Data
    public static class Attribute{
        private ArrayList<Content> contents;
        private String name;
        private String beautifiedName;
    }
    @Data
    public static class Brand{
        private String beautifiedName;
        private Integer id;
        private String name;
        private boolean isVirtual;
        private String path;
    }
    @Data
    public static class Content{
        private String url;
        private Integer id;
        private String imageUrl;
        private String name;
        private Price price;
        private ArrayList<Label> labels;
    }
    @Data
    public static class Label{
        private String name;
    }
    @Data
    public static class OriginalPrice{
        private String text;
        private Integer value;
    }
    @Data
    public static class Price{
        private DiscountedPrice discountedPrice;
        private OriginalPrice originalPrice;
        private SellingPrice sellingPrice;
    }
    @Data
    public static class SellingPrice{
        private String text;
        private Integer value;
    }
    @Data
    public static class DiscountedPrice{
        private String text;
        private Integer value;
    }
}
