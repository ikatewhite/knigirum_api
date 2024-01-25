package knigirum.tests;

import com.github.javafaker.Faker;

public class TestData {

    public static final
    Faker faker = new Faker();

    public static final int
            BOOKS_AMOUNT = 50,
            EXISTING_RANDOM_BOOK_ID = faker.random().nextInt(1, 50),
            UNEXISTING_RANDOM_BOOK_ID = faker.random().nextInt(51, 100),
            THE_DEAD_ZONE_ID = 7,
            THE_DEAD_ZONE_PRICE = 1160,

            EXISTING_RANDOM_MIN_PRICE = faker.random().nextInt(410, 1200),
            EXISTING_RANDOM_MAX_PRICE = faker.random().nextInt(410, 1200),
            EXACT_MIN_PRICE = 700,
            EXACT_MAX_PRICE = 1000,
            MIN_PRICE_LESS_THAN_ZERO = -700,

            SERVER_ERROR_CODE = 500;

    public static final double
            WINTER_PROMOCODE_DISCOUNT = 11.00,
            SPRING_PROMOCODE_DISCOUNT = 13.00,
            SUMMER_PROMOCODE_DISCOUNT = 10.00,
            AUTUMN_PROMOCODE_DISCOUNT = 12.00,
            UNKNOWN_PROMOCODE_DISCOUNT = 0.00;

    public static final String
            EXISTING_RANDOM_AUTHOR = faker.options().option("Артур Конан Дойл", "Конан Дойл", "Артуро Перес-Реверте",
            "Перес-Реверте", "Герман Гессе", "Гессе", "Говард Лавкрафт", "Лавкрафт", "Стендаль", "Стивен Кинг",
            "Кинг"),
            UNEXISTING_RANDOM_AUTHOR = faker.options().option("Дмитрий Глуховский", "Глуховский", "Донна Тартт",
                    "Тартт", "Донна Таррт", "Таррт", "Селеста Инг", "Инг", "Stephen King", "King"),
            STEPHEN_KING = faker.options().option("Стивен Кинг", "Кинг"),
            STENDHAL = "Стендаль",

            THE_DEAD_ZONE_TITLE = "Мертвая зона",

            EXISTING_RANDOM_PROMOCODE = faker.options().option("winter", "spring", "summer", "autumn"),
            UNEXISTING_RANDOM_PROMOCODE = faker.options().option("зима", "весна", "лето", "осень"),
            WINTER_PROMOCODE = "winter",
            SPRING_PROMOCODE = "spring",
            SUMMER_PROMOCODE = "summer",
            AUTUMN_PROMOCODE = "autumn",


            RANDOM_REVIEW_AUTHOR_NAME = faker.name().username(),
            REVIEW_TEXT = "Хорошая книга",
            UPDATED_REVIEW_TEXT = "Не просто хорошая, а очень хорошая книга!",

            PRICE_IN_CYRILLIC = "Девятьсот",

            NOT_FOUND_404_MESSAGE = "Not Found",
            SERVER_ERROR_MESSAGE = "Server Error";

    public static final boolean
            RANDOM_RECOMMENDED = faker.random().nextBoolean();
}
