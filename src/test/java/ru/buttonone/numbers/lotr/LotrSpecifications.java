package ru.buttonone.numbers.lotr;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class LotrSpecifications {

    public static final String LOTR_API_URL = "https://the-one-api.dev/v2";
    public static final String ID_THE_RETURN_OF_THE_KING_API_URL =
            "/book/5cf58080b53e011a64671584";
    public static final String ID_THE_TWO_TOWERS_API_URL =
            "/book/5cf58077b53e011a64671583/chapter";
    public static final String ID_THE_FELLOWSHIP_OF_THE_RING_API_URL =
            "/book/5cf5805fb53e011a64671582/chapter";

    public static RequestSpecification requestLotrSpecification() {
        return new RequestSpecBuilder()
                .addHeader("language", "en")
                .setBaseUri(LOTR_API_URL)
                .build();
    }

    public static ResponseSpecification responseLotrSpecification
            (String contentType, int statusCode, String nameContentLength, String valueOfContentLength) {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .expectContentType(contentType)
                .expectStatusCode(statusCode)
                .expectHeader(nameContentLength, valueOfContentLength)
                .build();
    }


}
