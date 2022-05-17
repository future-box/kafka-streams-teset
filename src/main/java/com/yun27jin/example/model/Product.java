package com.yun27jin.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Product {

    private long productId;
    private String dispNm;
    private String brandNm;
}
