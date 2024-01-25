package knigirum.API;

import knigirum.models.ReviewResponseModel;
import knigirum.models.ReviewsItem;

import static io.restassured.RestAssured.given;
import static knigirum.specs.RequestSpecs.getReviewRequestSpec;
import static knigirum.specs.ResponseSpecs.addReviewResponseSpec201;

public class ReviewsAPI {
    public static ReviewResponseModel reviewAdded(ReviewsItem addNewReviewRequest) {
        return given(getReviewRequestSpec)
                .body(addNewReviewRequest)
                .post()
                .then()
                .spec(addReviewResponseSpec201)
                .extract().as(ReviewResponseModel.class);
    }
}
