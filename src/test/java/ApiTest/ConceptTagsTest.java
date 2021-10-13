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

public class ConceptTagsTest extends BaseSetUp {
	
  @BeforeTest
  public void beforeTest() {
    System.out.println("Validation of concept_tags related test cases.");
  }

  // concept_tags as true with the API KEY
  @Test
  public void conceptTagsTrueWithAPIKEYTest() {
    Response response = given().param("api_key", API_KEY).param("date", THUMBS_DATE_APOD_VIDEO).param("concept_tags", true).when().get(APOD_URL);
    ValidatableResponse validatableResponse = response.then();
    validatableResponse.assertThat().statusCode(HttpStatus.SC_OK);
    validatableResponse.assertThat().contentType(ContentType.JSON);
    validatableResponse.body(
    		"$", hasKey("concepts"),
    		"concepts", Matchers.equalTo(CONCEPTTAGS_TURNEDOFF_MSG),
    		"$", hasKey("date"),
    		"date", Matchers.equalTo(THUMBS_DATE_APOD_VIDEO),
    		"$", hasKey("copyright"),
    		"copyright", Matchers.equalTo("Vikas Chander Music: Tea Time via PremiumBeat"),
    		"$", hasKey("explanation"),
    		"$", hasKey("media_type"),
    		"media_type", Matchers.equalTo("video"),
    		"$", hasKey("service_version"),
    		"$", hasKey("title"),
    		"title", Matchers.equalTo("Night of the Perseids"),
    		"$", hasKey("url"),
    		"url", Matchers.equalTo("https://www.youtube.com/embed/tLC6Sy8f06s?rel=0")
    );
  }
  
  
  
 // Without conecpt_tags with the API KEY
 @Test
 public void withoutConceptTagsWithAPIKEYTest() {
   Response response = given().param("api_key", API_KEY).param("date", THUMBS_DATE_APOD_VIDEO).when().get(APOD_URL);
   ValidatableResponse validatableResponse = response.then();
   validatableResponse.assertThat().statusCode(HttpStatus.SC_OK);
   validatableResponse.assertThat().contentType(ContentType.JSON);
   validatableResponse.body(
   		"$", not(hasKey("concepts")),
   		"$", hasKey("date"),
   		"date", Matchers.equalTo(THUMBS_DATE_APOD_VIDEO),
   		"$", hasKey("copyright"),
   		"copyright", Matchers.equalTo("Vikas Chander Music: Tea Time via PremiumBeat"),
   		"$", hasKey("explanation"),
   		"$", hasKey("media_type"),
   		"media_type", Matchers.equalTo("video"),
   		"$", hasKey("service_version"),
   		"$", hasKey("title"),
   		"title", Matchers.equalTo("Night of the Perseids"),
   		"$", hasKey("url"),
   		"url", Matchers.equalTo("https://www.youtube.com/embed/tLC6Sy8f06s?rel=0")
   );
 }
  
  @AfterTest
  public void afterTest() {
    System.out.println("Ending Test Cases in ConceptTagsTest Class.");
  }
  
}