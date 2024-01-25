package knigirum.tests;

import knigirum.models.PromoDiscountModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static knigirum.specs.RequestSpecs.getDiscountByPromocodeRequestSpec;
import static knigirum.specs.ResponseSpecs.getDiscountByPromocodeResponseSpec200;
import static knigirum.tests.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("promocodes")
public class PromocodesTests {
    @Test
    @Tag("positive")
    @DisplayName("Get discount by one of the existing promocodes chosen randomly")
    void positiveGetDiscountByRandomExistingPromocodeTest() {
        PromoDiscountModel getDiscount = step("Make request", () ->
                given(getDiscountByPromocodeRequestSpec)
                        .get(EXISTING_RANDOM_PROMOCODE)
                        .then()
                        .spec(getDiscountByPromocodeResponseSpec200)
                        .extract().as(PromoDiscountModel.class));
        step("Check response 200", () -> {
            if (Objects.equals(EXISTING_RANDOM_PROMOCODE, WINTER_PROMOCODE)) {
                assertThat(getDiscount.getPromoDiscount()).isEqualTo(WINTER_PROMOCODE_DISCOUNT);
            }
            if (Objects.equals(EXISTING_RANDOM_PROMOCODE, SPRING_PROMOCODE)) {
                assertThat(getDiscount.getPromoDiscount()).isEqualTo(SPRING_PROMOCODE_DISCOUNT);
            }
            if (Objects.equals(EXISTING_RANDOM_PROMOCODE, SUMMER_PROMOCODE)) {
                assertThat(getDiscount.getPromoDiscount()).isEqualTo(SUMMER_PROMOCODE_DISCOUNT);
            }
            if (Objects.equals(EXISTING_RANDOM_PROMOCODE, AUTUMN_PROMOCODE)) {
                assertThat(getDiscount.getPromoDiscount()).isEqualTo(AUTUMN_PROMOCODE_DISCOUNT);
            }
        });
    }

    @Test
    @Tag("negative")
    @DisplayName("Get discount by one of the unexisting promocodes chosen randomly")
    void negativeGetDiscountByRandomUnexistingPromocodeTest() {
        PromoDiscountModel getDiscount = step("Make request", () ->
                given(getDiscountByPromocodeRequestSpec)
                        .get(UNEXISTING_RANDOM_PROMOCODE)
                        .then()
                        .spec(getDiscountByPromocodeResponseSpec200)
                        .extract().as(PromoDiscountModel.class));
        step("Check response 200", () -> {
            assertThat(getDiscount.getPromoDiscount()).isEqualTo(UNKNOWN_PROMOCODE_DISCOUNT);
        });
    }
}
