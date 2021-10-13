package ApiTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.hasKey;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class ThumbsTest extends BaseSetUp {
	
	  @BeforeTest
	  public void beforeTest() {
	    System.out.println("Validation of thumbs related test cases.");
	  }

	  // thumbs value as true with date and API KEY
	  @Test
	  public void thumbsTrueAPODImageDateWithAPIKEYTest() {
	    Response response = given().param("api_key", API_KEY).param("date", THUMBS_DATE_APOD_VIDEO).param("thumbs", true).when().get(APOD_URL);
	    ValidatableResponse validatableResponse = response.then();
	    validatableResponse.assertThat().statusCode(HttpStatus.SC_OK);
	    validatableResponse.assertThat().contentType(ContentType.JSON);
	    validatableResponse.body(
	    		"$", hasKey("date"),
	    		"date", Matchers.equalTo(THUMBS_DATE_APOD_VIDEO),
	    		"$", hasKey("copyright"),
	    		"copyright", Matchers.equalTo("Vikas Chander Music: Tea Time via PremiumBeat"),
	    		"$", hasKey("explanation"),
	    		"$", hasKey("media_type"),
	    		"media_type", Matchers.equalTo("video"),
	    		"$", hasKey("thumbnail_url"),
	    		"thumbnail_url", Matchers.equalTo("https://img.youtube.com/vi/tLC6Sy8f06s/0.jpg"),
	    		"$", hasKey("service_version"),
	    		"$", hasKey("title"),
	    		"title", Matchers.equalTo("Night of the Perseids"),
	    		"$", hasKey("url"),
	    		"url", Matchers.equalTo("https://www.youtube.com/embed/tLC6Sy8f06s?rel=0")
	    );
	  }
	  
	  
	  // thumbs value as false with date and API KEY
	  @Test
	  public void thumbsFalseAPODImageDateWithAPIKEYTest() {
	    Response response = given().param("api_key", API_KEY).param("date", THUMBS_DATE_APOD_VIDEO).param("thumbs", false	).when().get(APOD_URL);
	    ValidatableResponse validatableResponse = response.then();
	    validatableResponse.assertThat().statusCode(HttpStatus.SC_OK);
	    validatableResponse.assertThat().contentType(ContentType.JSON);
	    validatableResponse.body(
	    		"$", hasKey("date"),
	    		"date", Matchers.equalTo(THUMBS_DATE_APOD_VIDEO),
	    		"$", hasKey("copyright"),
	    		"$", hasKey("explanation"),
	    		"$", hasKey("media_type"),
	    		"media_type", Matchers.equalTo("video"),
	    		"$", not(hasKey("thumbnail_url")),
	    		"$", hasKey("service_version"),
	    		"$", hasKey("title"),
	    		"$", hasKey("url")
	    );
	  }
	  
	  
	  @AfterTest
	  public void afterTest() {
	    System.out.println("Ending Test Cases in ThumbsTest Class.");
	  }
}
