package knigirum.tests;

import knigirum.API.ReviewsAPI;
import knigirum.models.ErrorMessageModel;
import knigirum.models.ReviewResponseModel;
import knigirum.models.ReviewsItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static knigirum.specs.RequestSpecs.*;
import static knigirum.specs.ResponseSpecs.*;
import static knigirum.tests.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("reviews")
public class ReviewsTests extends TestBase {

    @Test
    @Tag("positive")
    @DisplayName("Get reviews by existing book Id")
    void positiveGetReviewsByExistingBookIdTest() {
        given(getReviewsByBookIdRequestSpec)
                .get(String.valueOf(EXISTING_RANDOM_BOOK_ID))
                .then()
                .spec(getReviewsByBookIdResponseSpec200);
    }

    @Test
    @Tag("negative")
    @DisplayName("Get reviews by unexisting book Id")
    void negativeGetReviewsByUnexistingBookIdTest() {
        ReviewResponseModel noReviewsFound = step("Make request", () ->
                given(getReviewsByBookIdRequestSpec)
                        .get(String.valueOf(UNEXISTING_RANDOM_BOOK_ID))
                        .then()
                        .spec(getReviewsByBookIdResponseSpec200)
                        .extract().as(ReviewResponseModel.class));
        step("Check response 200", () -> {
            assertThat(noReviewsFound.getReviews()).isEmpty();
            assertThat(noReviewsFound.getTotalCount()).isEqualTo(0);
        });
    }

    @Test
    @Tag("positive")
    @DisplayName("Get review by review id")
    void positiveGetReviewByReviewIdTest() {
        ReviewsItem getReview = step("Make request", () ->
                given(getReviewRequestSpec)
                        .get(String.valueOf(newReview.getReviewId()))
                        .then()
                        .spec(getReviewByReviewIdResponseSpec200)
                        .extract().as(ReviewsItem.class));
        step("Check response 200", () -> {
            assertThat(getReview.getAuthorName()).isEqualTo(addNewReview.getAuthorName());
            assertThat(getReview.isRecommended()).isEqualTo(addNewReview.isRecommended());
            assertThat(getReview.getReviewId()).isEqualTo(newReview.getReviewId());
            assertThat(getReview.getText()).isEqualTo(addNewReview.getText());
        });
    }

    @Test
    @Tag("positive")
    @DisplayName("Add review to the existing book")
    void positiveAddReviewTest() {
        ReviewsAPI.reviewAdded(addNewReview);
    }

    @Test
    @Tag("positive")
    @DisplayName("Update review text")
    void positivePartiallyUpdateReviewTest() {
        step("Create and update review", () -> {
            given(getReviewRequestSpec)
                    .body(updateExistingReview)
                    .patch(String.valueOf(newReview.getReviewId()))
                    .then()
                    .spec(emptyBodyResponseSpec204);
        });
        step("Check that the review is updated", () -> {
            ReviewsItem updatedReview = given(getReviewRequestSpec)
                    .get(String.valueOf(newReview.getReviewId()))
                    .then()
                    .spec(getReviewByReviewIdResponseSpec200)
                    .extract().as(ReviewsItem.class);
            assertThat(updatedReview.getAuthorName()).isEqualTo(addNewReview.getAuthorName());
            assertThat(updatedReview.isRecommended()).isEqualTo(addNewReview.isRecommended());
            assertThat(updatedReview.getReviewId()).isEqualTo(newReview.getReviewId());
            assertThat(updatedReview.getText()).isEqualTo(updateExistingReview.getText());
        });
    }

    @Test
    @Tag("positive")
    @DisplayName("Delete review")
    void positiveDeleteReviewTest() {
        step("Create and delete review", () -> {
            given(getReviewRequestSpec)
                    .delete(String.valueOf(newReview.getReviewId()))
                    .then()
                    .spec(emptyBodyResponseSpec204);
        });
        step("Check that the review is deleted", () -> {
            given(getReviewRequestSpec)
                    .get(String.valueOf(newReview.getReviewId()))
                    .then()
                    .spec(getDeletedReviewByReviewIdResponseSpec200);
        });
    }

    @Test
    @Tag("negative")
    @DisplayName("Add review to the unexisting book")
    void negativeAddReviewToUnexistingBookTest() {
        ErrorMessageModel errorMessage = step("Make request", () ->
                given(getReviewRequestSpec)
                        .body(addNewReviewToUnexistingBook)
                        .post()
                        .then()
                        .spec(serverErrorResponseSpec500)
                        .extract().as(ErrorMessageModel.class));
        step("Check response 500", () -> {
            assertThat(errorMessage.getCode()).isEqualTo(SERVER_ERROR_CODE);
            assertThat(errorMessage.getMessage()).isEqualTo(SERVER_ERROR_MESSAGE);
        });
    }
}