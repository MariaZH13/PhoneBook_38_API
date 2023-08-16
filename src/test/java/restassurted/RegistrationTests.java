package restassurted;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import dto.TestHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class RegistrationTests implements TestHelper {
    String endpoint = "/v1/user/registration/usernamepassword";

    @BeforeMethod
    public void precondition() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void registrationPositive(){
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("adel" + INT + "@gmail.com")
                .password("Zxcvb$" + INT)
                .build();

        String token = given()
                .body(requestDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .path("token");

        System.out.println(token);
        System.out.println(requestDTO.getUsername() + " " + requestDTO.getPassword());
    }

    @Test
    public void registrationNegativeWrongEmail(){
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("adel" + INT + "gmail.com")
                .password("Zxcvb$" + INT)
                .build();

        ErrorDTO errorDTO = given()
                .body(requestDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(400)
                .extract()
                .as(ErrorDTO.class);

        System.out.println(errorDTO);
    } @Test
    public void registrationNegativeDublicateUser(){
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("adel2697@gmail.com")
                .password("Zxcvb$" + INT)
                .build();

        ErrorDTO errorDTO = given()
                .body(requestDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(409)
                .extract()
                .as(ErrorDTO.class);

        System.out.println(errorDTO);
    }



}
