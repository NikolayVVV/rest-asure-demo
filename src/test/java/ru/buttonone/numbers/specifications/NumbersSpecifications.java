package ru.buttonone.numbers.specifications;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.Scanner;

public class NumbersSpecifications {

    public static final String NUMBERS_URL = "http://numbersapi.com/";

    public static RequestSpecification defaultRequestSpecification() {
        return new RequestSpecBuilder()
                .addHeader("Accept-Language", "ru")
                .setBaseUri(NUMBERS_URL)
                .build();
    }

    public static ResponseSpecification defaultResponseSpecification(int statusCode) {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .expectStatusCode(statusCode)
                .build();
    }


}
