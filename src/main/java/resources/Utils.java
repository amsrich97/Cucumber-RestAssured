package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {

    //this method can be used for add place, delte place
    RequestSpecification req;
    public RequestSpecification requestSpecification() throws IOException {


        if(req==null) {
            //file output stream to create files during run time
            PrintStream stream = new PrintStream(new FileOutputStream("logging.txt"));

            //Request Builder class is used to send the request to post/put
            RequestSpecification req = new RequestSpecBuilder()
                    .setBaseUri(getGlobalValue("baseURI"))
                    .addQueryParam("key", "qaclick123")
                    // by adding logging filter to this main method, it attaches to the object so that for every class its noticeable
                    .addFilter(RequestLoggingFilter.logRequestTo(stream))//filter attached to req Object
                    .addFilter(ResponseLoggingFilter.logResponseTo(stream))//logging response as well
                    .setContentType(ContentType.JSON)
                    .build();
            return req;
        }
        return req;

    }


    public static String getGlobalValue(String key) throws IOException {
        Properties prop = new Properties();//captures all files with .properties
        FileInputStream fis = new FileInputStream("src/main/java/resources/global.properties");
        prop.load(fis);
        return prop.getProperty(key);

    }

    public String getJsonPath(Response response , String key)
    {
    String resp = response.asString();
    JsonPath js = new JsonPath(resp);
    return js.get(key).toString();
    }
}
