package stepImplementation;


import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    //Hooks used to run before and after of a particular tag defined method
    @Before("@DeletePlace")
    public void beforeScenario() throws IOException
    {		//execute this code only when place id is null
        //write a code that will give you place id

        StepDefinition m =new StepDefinition();
        if(StepDefinition.place_id==null)
        {

            m.add_place_payload("Terrace" , "English" , "Belgium");//call the method to add the palce
            m.user_calls_with_http_request("AddPlaceAPI", "POST"); // calling the when statment with appplaceapi as resource and post as method
            m.verify_place_Id_created_maps_to_using("Terrace", "GetPlaceAPI");
        }



    }
}
