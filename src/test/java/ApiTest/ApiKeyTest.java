package ApiTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class ApiKeyTest extends BaseSetUp {

	@BeforeTest
	public void beforeTest() {
		System.out.println("Validation of API KEY related test cases.");
	}

	//Valid API KEY
	@Test 
	public void validAPIKeyTest() {
		Response response = given().param("api_key", API_KEY).when().get(APOD_URL);
		ValidatableResponse validatableResponse = response.then();
		validatableResponse.assertThat().statusCode(HttpStatus.SC_OK);
		validatableResponse.assertThat().contentType(ContentType.JSON);
		validatableResponse.body(
				"$", hasKey("date"),
				"date", Matchers.equalTo(TODAYS_DATE_OF_THE_APOD_IMAGE),
				"$", hasKey("copyright"),
				"$", hasKey("explanation"),
				"$", hasKey("media_type"),
				"$", hasKey("service_version"),
				"$", hasKey("title"),
				"$", hasKey("url")
				);
	}


	// invalid API KEY
	@Test
	public void invalidAPIKeyTest() {
		Response response = given().param("api_key", "invalidAPIKey").when().get(APOD_URL);
		ValidatableResponse validatableResponse = response.then();
		validatableResponse.assertThat().statusCode(HttpStatus.SC_FORBIDDEN).body("error.code", Matchers.equalTo(API_KEY_INVALID_ERROR));

	}

	// API KEY MISSING
	@Test
	public void missingAPIKeyTest() {
		Response response = given().when().get(APOD_URL);
		ValidatableResponse validatableResponse = response.then();
		validatableResponse.assertThat().statusCode(HttpStatus.SC_FORBIDDEN).body("error.code", Matchers.equalTo(API_KEY_MISSING_ERROR));
	}

	@AfterTest
	public void afterTest() {
		System.out.println("Ending Test Cases in ApiKeyTest Class.");
	}

}
