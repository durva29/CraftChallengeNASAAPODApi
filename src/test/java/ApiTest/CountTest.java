package ApiTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasKey;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class CountTest extends BaseSetUp {
	
  @BeforeTest
  public void beforeTest() {
    System.out.println("Validation of count related test cases.");
  }

  // valid count with the API KEY
  @Test
  public void validCountWithAPIKEYTest() {
    Response response = given().param("api_key", API_KEY).param("count", 2).when().get(APOD_URL);
    ValidatableResponse validatableResponse = response.then();
    validatableResponse.assertThat().statusCode(HttpStatus.SC_OK);
    validatableResponse.assertThat().contentType(ContentType.JSON);
    validatableResponse.body("size()", greaterThan(0));
  }

  // count value as zero with API Key
  @Test
  public void countAsZeroWithAPIKEYTest() {
    Response response = given().param("api_key", API_KEY).param("count", 0).when().get(APOD_URL);
    ValidatableResponse validatableResponse = response.then();
    validatableResponse.assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    validatableResponse.assertThat().contentType(ContentType.JSON);
    validatableResponse.body(
    		"$", hasKey("msg"),
    		"msg", containsString(COUNT_IN_RANGE_ERROR),
    		"$", hasKey("service_version")
    	);
  }

  //count value as negative with API Key
  @Test
  public void countAsNegativeWithAPIKEYTest() {
    Response response = given().param("api_key", API_KEY).param("count", -10).when().get(APOD_URL);
    ValidatableResponse validatableResponse = response.then();
    validatableResponse.assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    validatableResponse.assertThat().contentType(ContentType.JSON);
    validatableResponse.body(
    		"$", hasKey("msg"),
    		"msg", containsString(COUNT_IN_RANGE_ERROR),
    		"$", hasKey("service_version")
    	);
  }

  //count value greater than 100 with API Key
  @Test
  public void countGreaterThan100WithAPIKEYTest() {
    Response response = given().param("api_key", API_KEY).param("count", 101).when().get(APOD_URL);
    ValidatableResponse validatableResponse = response.then();
    validatableResponse.assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    validatableResponse.assertThat().contentType(ContentType.JSON);
    validatableResponse.body(
    		"$", hasKey("msg"),
    		"msg", containsString(COUNT_IN_RANGE_ERROR),
    		"$", hasKey("service_version")
    	);
  }

  //invalid combination of parameters - date & start_date with count for the APOD Image with API KEY
  @Test
  public void invalidParamCombWithAPIKEYTest() {
    Response response = given().param("api_key", API_KEY).param("end_date", INVALID_DATEFORMAT_OF_THE_APOD_IMAGE).param("start_date", DATE_OF_THE_APOD_IMAGE).param("count", 2).when().get(APOD_URL);
    ValidatableResponse validatableResponse = response.then();
    validatableResponse.assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    validatableResponse.assertThat().contentType(ContentType.JSON);
    validatableResponse.body(
    		"$", hasKey("msg"),
    		"msg", containsString(INVALID_COMBINATION_PARAM_ERROR),
    		"$", hasKey("service_version")
    	);
  }
  
  @AfterTest
  public void afterTest() {
    System.out.println("Ending Test Cases in CountTest Class.");
  }

}
