package manuel.demos.inditex;

import jakarta.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;
import manuel.demos.inditex.entities.BaseProduct;
import manuel.demos.inditex.entities.BaseProductFactory;
import manuel.demos.inditex.entities.Currency;
import manuel.demos.inditex.entities.jpa.ProductDataMapper;
import manuel.demos.inditex.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;

import static io.restassured.RestAssured.given;
import static java.time.Month.DECEMBER;
import static java.time.Month.JUNE;
import static manuel.demos.inditex.InditexApplicationComponentTests.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;


/**
 * These tests cover the application logic, from the most external side the most internal one
 */
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class InditexApplicationComponentTests {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WebApplicationContext webContext;

    @Autowired
    private BaseProductFactory baseProductFactory;

    @Autowired
    private ProductDataMapper productDataMapper;

    @BeforeEach
    void setUp() throws SQLException {

        populateDB();
    }

    /**
     * This test prevents error propagation due to problems in the context load.
     * It works as a future-proof guarantee and speeds up inspections of problems related to the context
     */
    @Test
    @DisplayName("Test context is loaded")
    void contextLoad() {

        assertThat(webContext).isNotNull();
        ServletContext servletContext = webContext.getServletContext();

    }

    @Test
    void doPing() {
        given()
                .get("http://localhost:8080/ping")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName(
            "Given that a request is sent to the API, " +
                    "When any argument is not correct, " +
                    "Then an ERROR message is sent back")
    void malformedRequestSendsAnErrorBack() {

		given().log().all()
				.when()
				.queryParam("productId", "35455")
				.queryParam("brandId", "1")
				.get("http://localhost:8080/product")
				.then().log().all()
                .statusCode(400);

    }

    @Test
    @DisplayName("Given that a correct request is sent to the API," +
            "When a price for a given product is requested for 10h of the 14th day" +
            "Then the expected price is returned")
    void test1() {

        given().log().all()
                .when()
                .queryParam("productId", "35455")
                .queryParam("brandId", "1")
                .queryParam("applicationDate", "2020-06-14T10:00:00")
                .get("http://localhost:8080/product")
                .then()
                .log().all()
				.assertThat()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("productId", equalTo(35455))
                .body("brandId", equalTo(1))
                .body("startDate", equalTo("2020-06-14T00:00:00"))
                .body("endDate", equalTo("2020-12-31T23:59:59"))
                .body("price", equalTo(39.05F))
                .body("currency", equalTo("EUR"));
    }


    @Test
    @DisplayName(
            "Given that a correct request is sent to the API," +
                    "When a price for a given product is requested for 16h of the 14th day" +
                    "Then the expected price is returned")
    void test2() {

        given().log().all()
                .when()
                .queryParam("productId", "35455")
                .queryParam("brandId", "1")
                .queryParam("applicationDate", "2020-06-14T16:00:00")
                .get("http://localhost:8080/product")
                .then()
                .log().all()
				.assertThat()
                .statusCode(200)
                .body("id", equalTo(2))
                .body("productId", equalTo(35455))
                .body("brandId", equalTo(1))
                .body("startDate", equalTo("2020-06-14T15:00:00"))
                .body("endDate", equalTo("2020-06-14T18:30:59"))
                .body("price", equalTo(30.540003F))
                .body("currency", equalTo("EUR"));
    }

    @Test
    @DisplayName(
            "Given that a correct request is sent to the API," +
                    "When a price for a given product is requested for 21h of the 14th day" +
                    "Then the expected price is returned")
    void test3() {

        given().log().all()
                .when()
                .queryParam("productId", "35455")
                .queryParam("brandId", "1")
                .queryParam("applicationDate", "2020-06-14T21:00:00")
                .get("http://localhost:8080/product")
                .then()
                .log().all()
				.assertThat()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("productId", equalTo(35455))
                .body("brandId", equalTo(1))
                .body("startDate", equalTo("2020-06-14T00:00:00"))
                .body("endDate", equalTo("2020-12-31T23:59:59"))
                .body("price", equalTo(39.05F))
                .body("currency", equalTo("EUR"));
    }

    @Test
    @DisplayName(
            "Given that a correct request is sent to the API," +
                    "When a price for a given product is requested for 10 of the 15h day" +
                    "Then the expected price is returned")
    void test4() {

        given().log().all()
                .when()
                .queryParam("productId", "35455")
                .queryParam("brandId", "1")
                .queryParam("applicationDate", "2020-06-15T10:00:00")
                .get("http://localhost:8080/product")
                .then()
                .log().all()
				.assertThat()
                .statusCode(200)
                .body("id", equalTo(3))
                .body("productId", equalTo(35455))
                .body("brandId", equalTo(1))
                .body("startDate", equalTo("2020-06-15T00:00:00"))
                .body("endDate", equalTo("2020-06-15T11:00:00"))
                .body("price", equalTo(39.649998F))
                .body("currency", equalTo("EUR"));
    }

    @Test
    @DisplayName(
            "Given that a correct request is sent to the API," +
                    "When a price for a given product is requested for 21h of the 16th day" +
                    "Then the expected price is returned")
    void test5() {

        given().log().all()
                .when()
                .queryParam("productId", "35455")
                .queryParam("brandId", "1")
                .queryParam("applicationDate", "2020-06-16T21:00:00")
                .get("http://localhost:8080/product")
                .then()
                .log().all()
				.assertThat()
                .statusCode(200)
                .body("id", equalTo(4))
                .body("productId", equalTo(35455))
                .body("brandId", equalTo(1))
                .body("startDate", equalTo("2020-06-15T16:00:00"))
                .body("endDate", equalTo("2020-12-31T23:59:59"))
                .body("price", equalTo(54.53F))
                .body("currency", equalTo("EUR"));

    }

    public void populateDB() {


        BaseProduct baseProduct1 = baseProductFactory.create(
                productID,
                brandId,
                // 2020-06-14-00.00.00
                baseStartDateTime,
                // 2020-12-31-23.59.59
                baseEndDateTime,
                fee,
                priority - 1,
                35.50F,
                Currency.EUR
        );

        BaseProduct baseProduct2 = baseProductFactory.create(
                productID,
                brandId,
                // 2020-06-14-15.00.00
                baseStartDateTime.withHour(15),
                // 2020-06-14-18.30.00
                baseEndDateTime.withMonth(Month.JUNE.getValue()).withDayOfMonth(14).withHour(18).withMinute(30),
                fee + 1,
                priority,
                25.45F,
                Currency.EUR
        );

        BaseProduct baseProduct3 = baseProductFactory.create(
                productID,
                brandId,
                // 2020-06-15-00.00.00
                baseStartDateTime.withDayOfMonth(15),
                // 2020-06-15-11.00.00
                baseEndDateTime
                        .withMonth(JUNE.getValue())
                        .withDayOfMonth(15)
                        .withHour(11)
                        .withMinute(0)
                        .withSecond(0),
                fee + 2,
                priority,
                30.50F,
                Currency.EUR
        );

        BaseProduct baseProduct4 = baseProductFactory.create(
                productID,
                brandId,
                // 2020-06-15-16.00.00
                baseStartDateTime.withDayOfMonth(15).withHour(16),
                // 2020-12-31-23.59.59
                baseEndDateTime.withDayOfMonth(31),
                fee + 3,
                priority,
                38.95F,
                Currency.EUR
        );

        productRepository.save(productDataMapper.toBaseProductSQLEntity(baseProduct1));
        productRepository.save(productDataMapper.toBaseProductSQLEntity(baseProduct2));
        productRepository.save(productDataMapper.toBaseProductSQLEntity(baseProduct3));
        productRepository.save(productDataMapper.toBaseProductSQLEntity(baseProduct4));
    }


    static class TestData {

        static int brandId = 1;
        static public int productID = 35455;
        static LocalDateTime baseStartDateTime = LocalDateTime.of(2020, Month.JUNE, 14, 0, 0, 0);
        static LocalDateTime baseEndDateTime = LocalDateTime.of(2020, DECEMBER, 31, 23, 59, 59);
        static int fee = 1;
        static int priority = 1;

    }

}
