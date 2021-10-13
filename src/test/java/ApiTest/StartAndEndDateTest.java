package ApiTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasKey;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class StartAndEndDateTest extends BaseSetUp {
	
  @BeforeTest
  public void beforeTest() {
    System.out.println("Validation of the start date and end date related test cases.");
  }

  // valid start date for the APOD Image with API KEY
  @Test
  public void validAPODImageDateWithAPIKEYTest() {
    Response response = given().param("api_key", API_KEY).param("start_date", DATE_OF_THE_APOD_IMAGE).when().get(APOD_URL);
    ValidatableResponse validatableResponse = response.then();
    validatableResponse.body(
    		"date[0]", equalTo(DATE_OF_THE_APOD_IMAGE),
    		"date", hasItems(DATE_OF_THE_APOD_IMAGE, TODAYS_DATE_OF_THE_APOD_IMAGE)
    	);
  }
  

  // valid start_date and end_date for the APOD Image with API KEY
  @Test
  public void validStartEndDateCombWithAPIKEYTest() {
    Response response = given().param("api_key", API_KEY).param("start_date", DATE_OF_THE_APOD_IMAGE).param("end_date", TODAYS_DATE_OF_THE_APOD_IMAGE).when().get(APOD_URL);
    ValidatableResponse validatableResponse = response.then();
    validatableResponse.assertThat().statusCode(HttpStatus.SC_OK);
    validatableResponse.assertThat().contentType(ContentType.JSON);
    validatableResponse.body(
    		"date[0]", Matchers.equalTo(DATE_OF_THE_APOD_IMAGE),
    		"date", hasItems(DATE_OF_THE_APOD_IMAGE, TODAYS_DATE_OF_THE_APOD_IMAGE)
    	);
  }

  // end_date greater than today's date for the APOD Image with API KEY
  @Test
  public void endDateGreaterThanTodaysDate() {
    Response response = given().param("api_key", API_KEY).param("start_date", "2021-10-06").param("end_date", FUTURE_DATE_OF_THE_APOD_IMAGE).when().get(APOD_URL);
    ValidatableResponse validatableResponse = response.then();
    validatableResponse.assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    validatableResponse.assertThat().contentType(ContentType.JSON);
    validatableResponse.body(
    		"$", hasKey("msg"),
    		"msg", containsString(DATERANGE_ERROR),
    		"$", hasKey("service_version")
    	);
  }

  //invalid combination of parameters date & start_date for the APOD Image with API KEY
  @Test
  public void invalidParamCombWithAPIKEYTest() {
    Response response = given().param("api_key", API_KEY).param("date", TODAYS_DATE_OF_THE_APOD_IMAGE).param("start_date", DATE_OF_THE_APOD_IMAGE).when().get(APOD_URL);
    ValidatableResponse validatableResponse = response.then();
    validatableResponse.assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    validatableResponse.assertThat().contentType(ContentType.JSON);
    validatableResponse.body(
    		"$", hasKey("msg"),
    		"msg", containsString(INVALID_COMBINATION_PARAM_ERROR),
    		"$", hasKey("service_version")
    	);
  }

  // invalid combination of parameters - only end_date with the APOD Image with API KEY
  @Test
  public void onlyEndDateWithAPIKEYTest() {
    Response response = given().param("api_key", API_KEY).param("end_date", TODAYS_DATE_OF_THE_APOD_IMAGE).when().get(APOD_URL);
    ValidatableResponse validatableResponse = response.then();
    validatableResponse.assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    validatableResponse.assertThat().contentType(ContentType.JSON);
    validatableResponse.body(
    		"$", hasKey("msg"),
    		"msg",containsString(INVALID_COMBINATION_PARAM_ERROR),
            "$", hasKey("service_version")
         );
  }

  // invalid end date format
  @Test
  public void invalidEndDateWithAPIKEYTest() {
    Response response = given().param("api_key", API_KEY).param("end_date", INVALID_DATEFORMAT_OF_THE_APOD_IMAGE).param("start_date", DATE_OF_THE_APOD_IMAGE).when().get(APOD_URL);
    ValidatableResponse validatableResponse = response.then();
    validatableResponse.assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    validatableResponse.assertThat().contentType(ContentType.JSON);
    validatableResponse.body(
    		"$", hasKey("msg"),
    		"msg", containsString(INVALID_DATEFORMAT_ERROR),
    		"$", hasKey("service_version")
    	);
  }

  @AfterTest
  public void afterTest() {
    System.out.println("Ending Test Cases in StartAndEndDateTest Class.");
  }
}
