//Step Definition File should only have the core logic
package stepImplementation;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import java.io.IOException;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinition extends Utils {

	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String place_id;
	JsonPath js;

	@Given("Add Place Payload {string}  {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException {

		//what all are attached to given - request
		// spec is the method which accepts the request spec object
		// //which comes from Utils class and since it returns requestspecification object we can simply call
		res = given().spec(requestSpecification())
				.body(data.addPlacePayLoad(name, language, address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {

		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		//To add the place

//	//Response Builder class is used to get the status code
		resspec = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build(); // build will preapre will all the parameters
		if (method.equalsIgnoreCase("POST"))
			response = res.when().post(resourceAPI.getResource());
		else if (method.equalsIgnoreCase("GET"))
			response = res.when().get(resourceAPI.getResource());




	}

	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {

		assertEquals(response.getStatusCode(), 200);
	}

	//the regexp here two strings are key value pairs defined in feature file for checking the json key value in the response
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String Expectedvalue) {
		String resp = response.asString();
		js = new JsonPath(resp);
		assertEquals(js.get(keyValue).toString(), Expectedvalue);
	}

	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_Id_created_maps_to_using(String expectedName, String resource) throws IOException {

		// requestSpec
		place_id = getJsonPath(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "GET");
		String actualName = getJsonPath(response, "name");
		assertEquals(actualName, expectedName);


	}


	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException {
		System.out.println("Delte");
		// Write code here that turns the phrase above into concrete actions

		res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}
}