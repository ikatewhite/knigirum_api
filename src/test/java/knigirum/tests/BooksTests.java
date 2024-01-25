package knigirum.tests;

import knigirum.models.AllBooksResponseModel;
import knigirum.models.ErrorMessageModel;
import knigirum.models.ProductsItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static knigirum.specs.RequestSpecs.*;
import static knigirum.specs.ResponseSpecs.*;
import static knigirum.tests.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("books")
public class BooksTests {
    @Test
    @Tag("positive")
    @DisplayName("Get list of all books in KnigiRum database")
    void positiveGetAllBooksTest() {
        AllBooksResponseModel getAllBooks = step("Make request", () ->
                given(getAllBooksRequestSpec)
                        .get()
                        .then()
                        .spec(getAllBooksResponseSpec200)
                        .extract().as(AllBooksResponseModel.class));
        step("Check response 200", () -> {
            assertThat(getAllBooks.getProducts()).hasSize(BOOKS_AMOUNT);
            assertThat(getAllBooks.getProducts().get(0).getId()).isEqualTo(1);
            assertThat(getAllBooks.getProducts().get(49).getId()).isEqualTo(BOOKS_AMOUNT);
        });
    }

    @Test
    @Tag("positive")
    @DisplayName("Get random book by its Id")
    void positiveGetRandomBookByItsIdTest() {
        ProductsItem getBookId = step("Make request", () ->
                given(getBookByItsIdRequestSpec)
                        .get(String.valueOf(EXISTING_RANDOM_BOOK_ID))
                        .then()
                        .spec(getBookByItsIdResponseSpec200)
                        .extract().as(ProductsItem.class));
        step("Check response 200", () -> {
            assertThat(getBookId.getId()).isEqualTo(EXISTING_RANDOM_BOOK_ID);
        });
    }

    @Test
    @Tag("positive")
    @DisplayName("Get particular book by its Id")
    void positiveGetParticularBookByItsIdTest() {
        ProductsItem getBook = step("Make request", () ->
                given(getBookByItsIdRequestSpec)
                        .get(String.valueOf(THE_DEAD_ZONE_ID))
                        .then()
                        .spec(getBookByItsIdResponseSpec200)
                        .extract().as(ProductsItem.class));
        step("Check response 200", () -> {
            assertThat(getBook.getAuthor()).contains(STEPHEN_KING);
            assertThat(getBook.getId()).isEqualTo(THE_DEAD_ZONE_ID);
            assertThat(getBook.getPrice()).isEqualTo(THE_DEAD_ZONE_PRICE);
            assertThat(getBook.getTitle()).isEqualTo(THE_DEAD_ZONE_TITLE);
        });
    }

    @Test
    @Tag("positive")
    @DisplayName("Get some books with random limit")
    void positiveGetSomeBooksWithRandomLimitTest() {
        AllBooksResponseModel getSomeBooks = step("Make request", () ->
                given(getSomeBooksWithRandomLimitRequestSpec)
                        .get()
                        .then()
                        .spec(getSomeBooksResponseSpec200)
                        .extract().as(AllBooksResponseModel.class));
        step("Check response 200", () -> {
            assertThat(getSomeBooks.getProducts()).hasSize(EXISTING_RANDOM_BOOK_ID);
            assertThat(getSomeBooks.getProducts().get(0).getId()).isEqualTo(1);
            assertThat(getSomeBooks.getProducts().get(EXISTING_RANDOM_BOOK_ID - 1).getId())
                    .isEqualTo(EXISTING_RANDOM_BOOK_ID);
        });
    }

    @Test
    @Tag("positive")
    @DisplayName("Get some books with random offset")
    void positiveGetSomeBooksWithRandomOffsetTest() {
        AllBooksResponseModel getSomeBooks = step("Make request", () ->
                given(getSomeBooksWithRandomOffsetRequestSpec)
                        .get()
                        .then()
                        .spec(getSomeBooksResponseSpec200)
                        .extract().as(AllBooksResponseModel.class));
        step("Check response 200", () -> {
            assertThat(getSomeBooks.getProducts()).hasSize(BOOKS_AMOUNT - EXISTING_RANDOM_BOOK_ID);
        });
    }

    @Test
    @Tag("positive")
    @DisplayName("Get all books of particular author with exact minimum price and exact maximum price")
    void positiveGetAllBooksOfParticularAuthorWithExactMinAndMaxPricesTest() {
        AllBooksResponseModel getSomeBooks = step("Make request", () ->
                given(getAllBooksOfParticularAuthorWithExactMinAndMaxPricesRequestSpec)
                        .get()
                        .then()
                        .spec(getSomeBooksResponseSpec200)
                        .extract().as(AllBooksResponseModel.class));
        step("Check response 200", () -> {
            assertThat(getSomeBooks.getProducts().get(0).getAuthor()).contains(STEPHEN_KING);
            assertThat(getSomeBooks.getProducts().get(getSomeBooks.getProducts().size() - 1).getAuthor())
                    .contains(STEPHEN_KING);
            assertThat(getSomeBooks.getProducts().get(0).getPrice())
                    .isGreaterThanOrEqualTo(EXACT_MIN_PRICE);
            assertThat(getSomeBooks.getProducts().get(0).getPrice()).isLessThanOrEqualTo(EXACT_MAX_PRICE);
            assertThat(getSomeBooks.getProducts().get(getSomeBooks.getProducts().size() - 1).getPrice())
                    .isGreaterThanOrEqualTo(EXACT_MIN_PRICE);
            assertThat(getSomeBooks.getProducts().get(getSomeBooks.getProducts().size() - 1).getPrice())
                    .isLessThanOrEqualTo(EXACT_MAX_PRICE);
        });
    }

    @Test
    @Tag("positive")
    @DisplayName("Get two books of particular author sorted by price descending with offset one")
    void positiveGetTwoBooksOfParticularAuthorSortedByPriceDescWithOffsetOneTest() {
        AllBooksResponseModel getSomeBooks = step("Make request", () ->
                given(getTwoBooksOfParticularAuthorSortedByPriceDescWithOffsetOneRequestSpec)
                        .get()
                        .then()
                        .spec(getSomeBooksResponseSpec200)
                        .extract().as(AllBooksResponseModel.class));
        step("Check response 200", () -> {
            assertThat(getSomeBooks.getProducts().get(0).getAuthor()).isEqualTo(STENDHAL);
            assertThat(getSomeBooks.getProducts().get(1).getAuthor()).isEqualTo(STENDHAL);
        });
    }

    @Test
    @Tag("positive")
    @DisplayName("Get books of particular author with random minimum price and random maximum price")
    void positiveGetBooksOfParticularAuthorWithMinAndMaxRandomPricesTest() {
        given(getBooksOfParticularAuthorWithRandomMinAndMaxPricesRequestSpec)
                .get()
                .then()
                .spec(getSomeBooksResponseSpec200);
    }

    @Test
    @Tag("positive")
    @DisplayName("Get books of random author with random minimum price and random maximum price")
    void positiveGetBooksOfRandomAuthorWithMinAndMaxRandomPricesTest() {
        given(getBooksOfRandomAuthorWithRandomMinAndMaxPricesRequestSpec)
                .get()
                .then()
                .spec(getSomeBooksResponseSpec200);
    }

    @Test
    @Tag("negative")
    @DisplayName("Get books of unexisting in the catalogue author with random minimum price and random maximum price")
    void negativeGetBooksOfUnexistingAuthorWithMinAndMaxRandomPricesTest() {
        AllBooksResponseModel noBooksFound = step("Make request", () ->
                given(getBooksOfUnexistingAuthorWithRandomMinAndMaxPricesRequestSpec)
                        .get()
                        .then()
                        .spec(getSomeBooksResponseSpec200)
                        .extract().as(AllBooksResponseModel.class));
        step("Check response 200", () -> assertThat(noBooksFound.getProducts()).isEmpty());
    }

    @Test
    @Tag("negative")
    @DisplayName("Get books with maximum price zero")
    void negativeGetBooksWithMaxPriceZeroTest() {
        AllBooksResponseModel noBooksFound = step("Make request", () ->
                given(getBooksWithMaxPriceZeroRequestSpec)
                        .get()
                        .then()
                        .spec(getSomeBooksResponseSpec200)
                        .extract().as(AllBooksResponseModel.class));
        step("Check response 200", () -> assertThat(noBooksFound.getProducts()).isEmpty());
    }

    @Test
    @Tag("negative")
    void negativeGetRandomBookByUnexistingIdTest() {
        given(getBookByItsIdRequestSpec)
                .get(String.valueOf(UNEXISTING_RANDOM_BOOK_ID))
                .then()
                .spec(emptyBodyResponseSpec204);
    }

    @Test
    @Tag("negative")
    @DisplayName("Get list of all 'dooks' instead of books")
    void negativeGetAllDooksTest() {
        ErrorMessageModel errorMessage = step("Make request", () ->
                given(getAllDooksRequestSpec)
                        .get()
                        .then()
                        .spec(badRequestResponseSpec404)
                        .extract().as(ErrorMessageModel.class));
        step("Check response 404", () -> {
            assertThat(errorMessage.getMessage()).isEqualTo(NOT_FOUND_404_MESSAGE);
        });
    }

    @Test
    @Tag("negative")
    @DisplayName("Get random 'dook' instead of book by its Id")
    void negativeGetRandomDookByItsIdTest() {
        ErrorMessageModel errorMessage = step("Make request", () ->
                given(getDookByItsIdRequestSpec)
                        .get(String.valueOf(EXISTING_RANDOM_BOOK_ID))
                        .then()
                        .spec(badRequestResponseSpec404)
                        .extract().as(ErrorMessageModel.class));
        step("Check response 404", () -> {
            assertThat(errorMessage.getMessage()).isEqualTo(NOT_FOUND_404_MESSAGE);
        });
    }

    @Test
    @Tag("negative")
    @DisplayName("Get books with minimum price less than zero")
    void negativeGetBooksWithMinPriceLessThanZeroTest() {
        ErrorMessageModel errorMessage = step("Make request", () ->
                given(getBooksWithMinPriceLessThanZeroRequestSpec)
                        .get()
                        .then()
                        .spec(serverErrorResponseSpec500)
                        .extract().as(ErrorMessageModel.class));
        step("Check response 500", () -> {
            assertThat(errorMessage.getCode()).isEqualTo(SERVER_ERROR_CODE);
            assertThat(errorMessage.getMessage()).isEqualTo(SERVER_ERROR_MESSAGE);
        });
    }

    @Test
    @Tag("negative")
    @DisplayName("Get books with maximum price in Cyrillic")
    void negativeGetBooksWithMaxPriceInCyrillicTest() {
        ErrorMessageModel errorMessage = step("Make request", () ->
                given(getBooksWithMaxPriceInCyrillicRequestSpec)
                        .get()
                        .then()
                        .spec(serverErrorResponseSpec500)
                        .extract().as(ErrorMessageModel.class));
        step("Check response 500", () -> {
            assertThat(errorMessage.getCode()).isEqualTo(SERVER_ERROR_CODE);
            assertThat(errorMessage.getMessage()).isEqualTo(SERVER_ERROR_MESSAGE);
        });
    }
}