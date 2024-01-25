package knigirum.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductsItem {

    @JsonProperty("author")
    private String author;

    @JsonProperty("author_bio")
    private String authorBio;

    @JsonProperty("description")
    private String description;

    @JsonProperty("id")
    private int id;

    @JsonProperty("price")
    private int price;

    @JsonProperty("reviews_count")
    private int reviewsCount;

    @JsonProperty("title")
    private String title;
}