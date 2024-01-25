package knigirum.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseModel {

    @JsonProperty("review_id")
    private int reviewId;

    @JsonProperty("reviews")
    private List<ReviewsItem> reviews;

    @JsonProperty("total_count")
    private int totalCount;
}