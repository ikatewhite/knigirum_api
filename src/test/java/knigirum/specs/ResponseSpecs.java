package knigirum.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class ResponseSpecs {
    public static ResponseSpecification getAllBooksResponseSpec200 = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .expectBody("products", notNullValue())
            .expectBody(matchesJsonSchemaInClasspath("schemas/all_books_list.json"))
            .build();

    public static ResponseSpecification getBookByItsIdResponseSpec200 = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .expectBody("author", notNullValue())
            .expectBody("author_bio", notNullValue())
            .expectBody("description", notNullValue())
            .expectBody("id", notNullValue())
            .expectBody("price", notNullValue())
            .expectBody("reviews_count", notNullValue())
            .expectBody("title", notNullValue())
            .build();

    public static ResponseSpecification getSomeBooksResponseSpec200 = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .expectBody("products", notNullValue())
            .build();

    public static ResponseSpecification getDiscountByPromocodeResponseSpec200 = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .expectBody("promo_discount", notNullValue())
            .build();

    public static ResponseSpecification getReviewsByBookIdResponseSpec200 = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .expectBody("reviews", notNullValue())
            .expectBody("total_count", notNullValue())
            .build();

    public static ResponseSpecification getReviewByReviewIdResponseSpec200 = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .expectBody("author_name", notNullValue())
            .expectBody("recommended", notNullValue())
            .expectBody("review_id", notNullValue())
            .expectBody("text", notNullValue())
            .build();

    public static ResponseSpecification addReviewResponseSpec201 = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(201)
            .expectBody("review_id", notNullValue())
            .expectBody("total_count", notNullValue())
            .build();

    public static ResponseSpecification emptyBodyResponseSpec204 = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(204)
            .build();

    public static ResponseSpecification getDeletedReviewByReviewIdResponseSpec200 = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .expectBody("author_name", equalTo(""))
            .expectBody("recommended", equalTo(false))
            .expectBody("review_id", equalTo(0))
            .expectBody("text", equalTo(""))
            .build();

    public static ResponseSpecification badRequestResponseSpec404 = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(404)
            .expectBody(notNullValue())
            .build();

    public static ResponseSpecification serverErrorResponseSpec500 = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(500)
            .expectBody(notNullValue())
            .build();
}