package ApiTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.hasKey;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class DateTest extends BaseSetUp {

  @BeforeTest
  public void beforeTest() {
    System.out.println("Validation of date related test cases.");
  }

  // valid date for the APOD Image with API KEY
  @Test
  public void validAPODImageDateWithAPIKEYTest() {
    Response response = given().param("api_key", API_KEY).param("date", DATE_OF_THE_APOD_IMAGE).when().get(APOD_URL);
    ValidatableResponse validatableResponse = response.then();
    validatableResponse.assertThat().statusCode(HttpStatus.SC_OK);
    validatableResponse.assertThat().contentType(ContentType.JSON);
    validatableResponse.body(
    		"date", Matchers.equalTo(DATE_OF_THE_APOD_IMAGE),
    		"$", hasKey("copyright"),
    		"$", hasKey("date"),
    		"$", hasKey("explanation"),
    		"$", hasKey("hdurl"),
    		"$", hasKey("media_type"),
    		"$", hasKey("service_version"),
    		"$", hasKey("title"),
    		"$", hasKey("url")
    	);
  }

  // valid date for the APOD Image without API KEY
  @Test
  public void validAPODImageDateWithoutAPIKEYTest() {
    Response response = given().param("date", DATE_OF_THE_APOD_IMAGE).when().get(APOD_URL);
    ValidatableResponse validatableResponse = response.then();
    validatableResponse.assertThat().statusCode(HttpStatus.SC_FORBIDDEN);
    validatableResponse.body("error.code", Matchers.equalTo(API_KEY_MISSING_ERROR));
  }

  // Invalid date format for the APOD Image with API KEY
  @Test
  public void invalidAPODImageDateWithAPIKEYTest() {
    Response response = given().param("api_key", API_KEY).param("date", INVALID_DATEFORMAT_OF_THE_APOD_IMAGE).when().get(APOD_URL);
    ValidatableResponse validatableResponse = response.then();
    validatableResponse.assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    validatableResponse.assertThat().contentType(ContentType.JSON);
    validatableResponse.body(
    		"$", hasKey("msg"),
    		"msg", containsString(INVALID_DATEFORMAT_ERROR),
    		"$", hasKey("service_version")
    	);
  }

  // date as future date for the APOD Image with API KEY
  @Test
  public void futureAPODImageDateWithAPIKEYTest() {
    Response response = given().param("api_key", API_KEY).param("date", FUTURE_DATE_OF_THE_APOD_IMAGE).when().get(APOD_URL);
    ValidatableResponse validatableResponse = response.then();
    validatableResponse.assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    validatableResponse.assertThat().contentType(ContentType.JSON);
    validatableResponse.body(
    		"$", hasKey("msg"),
    		"msg", containsString(DATERANGE_ERROR),
    		"$", hasKey("service_version")
    	);
  }
  
  @AfterTest
  public void afterTest() {
    System.out.println("Ending Test Cases in DatesUnitTest Class.");
  }

}
