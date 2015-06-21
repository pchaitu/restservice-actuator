package hello;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jayway.restassured.RestAssured;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=HelloWorldApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class HelloWorldControllerTest {
	
	@Value("${local.server.port}")   // 6
    int port;
	
	@Before
    public void setUp() {
       RestAssured.port = port;
    }
	
	@Test
    public void testUnAuthorized() {
       
		when().
                get("/hello-world").
        then().
                statusCode(HttpStatus.UNAUTHORIZED.value());
     
    }
	
	@Test
    public void testAuthorized() {
		given().auth().basic("user", "password").
		when().
                get("/hello-world").
        then().
                statusCode(HttpStatus.OK.value());
     
    }

}
