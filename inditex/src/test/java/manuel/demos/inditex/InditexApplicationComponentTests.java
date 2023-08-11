package manuel.demos.inditex;

import io.restassured.RestAssured;
import jakarta.servlet.ServletContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

import static io.restassured.RestAssured.DEFAULT_PORT;
import static io.restassured.RestAssured.given;
import static io.restassured.http.Method.GET;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;


/**
 * These tests cover the application logic, from the most external side the most internal one
 */
@SpringBootTest
@TestPropertySource(locations = "classpath:application-component.yaml")
@AutoConfigureMockMvc
class InditexApplicationComponentTests {

	@Autowired
	private WebApplicationContext webContext;

	@BeforeAll
	public static void setUp() {
		RestAssured.port = DEFAULT_PORT;
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

		assertThat(servletContext).isNotNull().isInstanceOf(MockServletContext.class);
//		assertThat(webApplicationContext.getBean("productos");

	}

	@Test
	@DisplayName(
			"Given that a request is sent to the API, " +
			"When any argument is not correct, " +
			"Then an ERROR message is sent back")
	void malformedRequestSendsAnErrorBack(){

		given().request(GET, "/users/eugenp", Map.of(
				"applicationDate",	"1",
				"productID", 			"1"
				//,"StringId", 				"1"  the API is forced to fail
				))
				.then().statusCode(404);

	}

	@Test
	@DisplayName(
			"Given that a request is sent to the API, " +
			"When every argument is correct, " +
			"Then the expected product is sent back")
	void wellFormedRequestSuccess(){

		given().request(GET, "/item", Map.of(
				"applicationDate", 	"1",
				"productID", 		"1",
				"StringId", 		"1"))
				.then().statusCode(200)
				.assertThat()
				.body("item.productId", equalTo(35455))
				.body("item.brandId",equalTo(1))
				.body("item.startDate", equalTo("10:00:00 14-08-2023"))
				.body("item.endDate", equalTo("10:00:00 14-08-2023"))
				.body("item.curr", equalTo(16))
				.body("item.price", equalTo(12));

	}

}
