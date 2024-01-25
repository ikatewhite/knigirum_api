package knigirum.tests;

import knigirum.API.ReviewsAPI;
import knigirum.models.ReviewResponseModel;
import knigirum.models.ReviewsItem;

import static knigirum.tests.TestData.*;

public class TestBase {
    ReviewsItem addNewReview = ReviewsItem.builder()
            .bookId(EXISTING_RANDOM_BOOK_ID)
            .authorName(RANDOM_REVIEW_AUTHOR_NAME)
            .text(REVIEW_TEXT)
            .recommended(RANDOM_RECOMMENDED)
            .build();

    ReviewsItem addNewReviewToUnexistingBook = ReviewsItem.builder()
            .bookId(UNEXISTING_RANDOM_BOOK_ID)
            .authorName(RANDOM_REVIEW_AUTHOR_NAME)
            .text(REVIEW_TEXT)
            .recommended(RANDOM_RECOMMENDED)
            .build();

    ReviewResponseModel newReview = ReviewsAPI.reviewAdded(addNewReview);

    ReviewsItem updateExistingReview = ReviewsItem.builder()
            .text(UPDATED_REVIEW_TEXT)
            .build();
}
