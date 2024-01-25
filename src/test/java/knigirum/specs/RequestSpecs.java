package knigirum.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;

import static io.restassured.http.ContentType.JSON;
import static knigirum.helpers.CustomAllureListener.withCustomTemplates;
import static knigirum.tests.TestData.*;

public class RequestSpecs {
    static RequestSpecification getBaseReqSpec() {
        return new RequestSpecBuilder()
                .log(LogDetail.ALL)
                .setContentType(JSON)
                .setBaseUri("http://188.120.241.222:8534")
                .build()
                .filter(withCustomTemplates());
    }

    public static RequestSpecification getAllBooksRequestSpec = getBaseReqSpec()
            .basePath("books");

    public static RequestSpecification getBookByItsIdRequestSpec = getBaseReqSpec()
            .basePath("book");

    public static RequestSpecification getSomeBooksWithRandomLimitRequestSpec = getBaseReqSpec()
            .basePath("books")
            .queryParam("limit", EXISTING_RANDOM_BOOK_ID);

    public static RequestSpecification getSomeBooksWithRandomOffsetRequestSpec = getBaseReqSpec()
            .basePath("books")
            .queryParam("offset", EXISTING_RANDOM_BOOK_ID);

    public static RequestSpecification getAllBooksOfParticularAuthorWithExactMinAndMaxPricesRequestSpec
            = getBaseReqSpec()
            .basePath("books")
            .queryParam("minPrice", EXACT_MIN_PRICE)
            .queryParam("maxPrice", EXACT_MAX_PRICE)
            .queryParam("string", STEPHEN_KING);

    public static RequestSpecification getTwoBooksOfParticularAuthorSortedByPriceDescWithOffsetOneRequestSpec
            = getBaseReqSpec()
            .basePath("books")
            .queryParam("limit", 2)
            .queryParam("offset", 1)
            .queryParam("sort", "price")
            .queryParam("order", "desc")
            .queryParam("string", STENDHAL);

    public static RequestSpecification getBooksOfParticularAuthorWithRandomMinAndMaxPricesRequestSpec
            = getBaseReqSpec()
            .basePath("books")
            .queryParam("minPrice", EXISTING_RANDOM_MIN_PRICE)
            .queryParam("maxPrice", EXISTING_RANDOM_MAX_PRICE)
            .queryParam("string", STEPHEN_KING);

    public static RequestSpecification getBooksOfRandomAuthorWithRandomMinAndMaxPricesRequestSpec = getBaseReqSpec()
            .basePath("books")
            .queryParam("minPrice", EXISTING_RANDOM_MIN_PRICE)
            .queryParam("maxPrice", EXISTING_RANDOM_MAX_PRICE)
            .queryParam("string", EXISTING_RANDOM_AUTHOR);

    public static RequestSpecification getBooksOfUnexistingAuthorWithRandomMinAndMaxPricesRequestSpec
            = getBaseReqSpec()
            .basePath("books")
            .queryParam("minPrice", EXISTING_RANDOM_MIN_PRICE)
            .queryParam("maxPrice", EXISTING_RANDOM_MAX_PRICE)
            .queryParam("string", UNEXISTING_RANDOM_AUTHOR);

    public static RequestSpecification getBooksWithMaxPriceZeroRequestSpec = getBaseReqSpec()
            .basePath("books")
            .queryParam("maxPrice", 0);

    public static RequestSpecification getDiscountByPromocodeRequestSpec = getBaseReqSpec()
            .basePath("promocodes");

    public static RequestSpecification getReviewsByBookIdRequestSpec = getBaseReqSpec()
            .basePath("reviews");

    public static RequestSpecification getReviewRequestSpec = getBaseReqSpec()
            .basePath("review");

    public static RequestSpecification getAllDooksRequestSpec = getBaseReqSpec()
            .basePath("dooks");

    public static RequestSpecification getDookByItsIdRequestSpec = getBaseReqSpec()
            .basePath("dook");

    public static RequestSpecification getBooksWithMinPriceLessThanZeroRequestSpec
            = getBaseReqSpec()
            .basePath("books")
            .queryParam("minPrice", MIN_PRICE_LESS_THAN_ZERO);

    public static RequestSpecification getBooksWithMaxPriceInCyrillicRequestSpec
            = getBaseReqSpec()
            .basePath("books")
            .queryParam("maxPrice", PRICE_IN_CYRILLIC);
}