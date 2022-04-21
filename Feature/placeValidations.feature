Feature: Validating Place API's
@AddPlace
	Scenario Outline: Verify if Place is being Succesfully added using AddPlaceAPI
		Given Add Place Payload "<name>"  "<language>" "<address>"
		When user calls "AddPlaceAPI" with "POST" http request
		Then the API call got success with status code 200
		And "status" in response body is "OK"
		And "scope" in response body is "APP"
		And verify place_Id created maps to "<name>" using "GetPlaceAPI"

		Examples:
			| name    | language | address            |
			| Frontline house | English-US  | Copehagen |
			| Penthouse | Spanish  | Sea cross center  |
@DeletePlace
	Scenario: Verify if Delete Place functionality is working

		Given DeletePlace Payload
		When user calls "DeletePlaceAPI" with "POST" http request
		Then the API call got success with status code 200
		And "status" in response body is "OK"



	#place_id variable is static as for all test cases value won't change
	#if not static it will set as null after one test case execution is completed
	
	
	

	
	
	