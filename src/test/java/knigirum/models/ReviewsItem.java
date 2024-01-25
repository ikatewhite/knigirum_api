package knigirum.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewsItem {

    @JsonProperty("book_id")
    private int bookId;

    @JsonProperty("author_name")
    private String authorName;

    @JsonProperty("review_id")
    private int reviewId;

    @JsonProperty("text")
    private String text;

    @JsonProperty("recommended")
    private boolean recommended;
}