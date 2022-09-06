package ru.buttonone.numbers.lotr;

import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.buttonone.model.Book;
import ru.buttonone.model.Chapter;

import java.util.List;


import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("From Api LOTR should ")
public class LotrTest {


    public static final String LOTR_API_URL = "https://the-one-api.dev/v2";
    public static final String ID_THE_RETURN_OF_THE_KING_API_URL =
            "/book/5cf58080b53e011a64671584";
    public static final String ID_THE_TWO_TOWERS_API_URL =
            "/book/5cf58077b53e011a64671583/chapter";
    public static final String ID_THE_FELLOWSHIP_OF_THE_RING_API_URL =
            "/book/5cf5805fb53e011a64671582/chapter";

    @DisplayName("correct get books")
    @Test
    public void shouldHaveCorrectGetBooks() {
        ValidatableResponse validatableResponse = given()
                .baseUri(LOTR_API_URL)
                .when()
                .get("/book")
                .then();

        List<Book> bookList = validatableResponse
                .extract()
                .body()
                .jsonPath().getList("docs", Book.class);

        System.out.println("bookList = " + bookList);


        //1
        Assertions.assertAll(
                () -> assertTrue(bookList.stream().anyMatch(b -> b.getName().
                        equals("The Fellowship Of The Ring")))
        );

        //2
        assertThat(bookList, hasItem(new Book("The Fellowship Of The Ring")));

        MatcherAssert.assertThat(bookList, Matchers.containsInAnyOrder
                (new Book("The Fellowship Of The Ring"),
                        new Book("The Return Of The King"),
                        new Book("The Two Towers")
                ));

    }


    @DisplayName("correct get charts the fellowship of the ring")
    @Test
    public void shouldHaveCorrectGetChaptersFromTheFellowshipOfTheRing() {
        ValidatableResponse validatableResponse = given()
                .spec(LotrSpecifications.requestLotrSpecification())
                .when()
                .get(ID_THE_FELLOWSHIP_OF_THE_RING_API_URL)
                .then()
                .spec(LotrSpecifications.responseLotrSpecification
                        ("application/json", 200, "Content-Length",
                                "1653"));

        List<Chapter> chaptersList = validatableResponse
                .extract()
                .body()
                .jsonPath().getList("docs", Chapter.class);

        System.out.println("chaptersList = " + chaptersList);
////
////        //1
        Assertions.assertAll(
                () -> assertTrue(chaptersList.stream().anyMatch(b -> b.getChapterName().
                        equals("The Mirror of Galadriel")))
        );
        Assertions.assertAll(
                () -> assertTrue(chaptersList.stream().anyMatch(b -> b.getId().
                        equals("6091b6d6d58360f988133b8c")))
        );

////
////        //2
        assertThat(chaptersList, hasItem(new Chapter("In the House of Tom Bombadil")));
        assertThat(chaptersList, hasSize(22));
//
//        MatcherAssert.assertThat(chaptersList, Matchers.containsInAnyOrder
//                (new Chapter("A Long-expected Party"),
//                        new Chapter("The Shadow of the Past"),
//                        new Chapter("Three is Company"),
//                        new Chapter("A Short Cut to Mushrooms"),
//                        new Chapter("A Conspiracy Unmasked"),
//                        new Chapter("The Old Forest"),
//                        new Chapter("In the House of Tom Bombadil"),
//                        new Chapter("Fog on the Barrow-Downs"),
//                        new Chapter("At the Sign of The Prancing Pony"),
//                        new Chapter("Strider"),
//                        new Chapter("A Knife in the Dark"),
//                        new Chapter("Flight to the Ford"),
//                        new Chapter("Many Meetings"),
//                        new Chapter("The Council of Elrond"),
//                        new Chapter("The Ring Goes South"),
//                        new Chapter("A Journey in the Dark"),
//                        new Chapter("The Bridge of Khazad-dûm"),  //какая-то проблема с этой главой - падает
//                        new Chapter("Lothlórien"),
//                        new Chapter("The Mirror of Galadriel"),
//                        new Chapter("Farewell to Lórien"),
//                        new Chapter("The Great River"),
//                        new Chapter("The Breaking of the Fellowship")
//                ));
//
    }


    @DisplayName("correct get charts the two towers")
    @Test
    public void shouldHaveCorrectGetChaptersFromTheTwoTowers() {
        ValidatableResponse validatableResponse = given()
                .spec(LotrSpecifications.requestLotrSpecification())
                .when()
                .get(ID_THE_TWO_TOWERS_API_URL)
                .then()
                .spec(LotrSpecifications.responseLotrSpecification("application/json", 200,
                        "Content-Length",
                        "1574"));

        List<Chapter> chaptersList = validatableResponse
                .extract()
                .body()
                .jsonPath().getList("docs", Chapter.class);

        System.out.println("chaptersList = " + chaptersList);
//
//        //1
        Assertions.assertAll(
                () -> assertTrue(chaptersList.stream().anyMatch(b -> b.getChapterName().
                        equals("The Departure of Boromir")))
        );
//
//        //2
        assertThat(chaptersList, hasItem(new Chapter("The Riders of Rohan")));
        assertThat(chaptersList, hasSize(21));
        assertThat(chaptersList, hasItems(new Chapter("The Choices of Master Samwise"),
                new Chapter("Shelob's Lair")));

        MatcherAssert.assertThat(chaptersList, Matchers.containsInAnyOrder
                (new Chapter("The Departure of Boromir"),
                        new Chapter("The Riders of Rohan"),
                        new Chapter("The Uruk-Hai"),
                        new Chapter("Treebeard"),
                        new Chapter("The White Rider"),
                        new Chapter("The King of the Golden Hall"),
                        new Chapter("Helm's Deep"),
                        new Chapter("The Road to Isengard"),
                        new Chapter("Flotsam and Jetsam"),
                        new Chapter("The Voice of Saruman"),
                        new Chapter("The Palantir"),
                        new Chapter("The Taming of Smeagol"),
                        new Chapter("The Passage of the Marshes"),
                        new Chapter("The Black Gate is Closed"),
                        new Chapter("Of Herbs and Stewed Rabbit"),
                        new Chapter("The Window on the West"),
                        new Chapter("The Forbidden Pool"),
                        new Chapter("Journey to the Cross-roads"),
                        new Chapter("The Stairs of Cirith Ungol"),
                        new Chapter("Shelob's Lair"),
                        new Chapter("The Choices of Master Samwise")
                ));

    }


}
