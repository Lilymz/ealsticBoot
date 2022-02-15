package com.zjmx.elastic.document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;

@Document(indexName = "product",createIndex = true)
@Getter
@Setter
@ToString
public class Product {
    @Id
    private Long id;
    @Field(name = "shortName",type = FieldType.Text,analyzer = "ik_max_word")
    private String shortName;
    @Field(name = "price",type = FieldType.Double)
    private BigDecimal price;
    @Field(name = "pic",type = FieldType.Keyword)
    private String pic;
}
