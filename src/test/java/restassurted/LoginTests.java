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

public class LoginTests implements TestHelper {

    String endpoint = "/v1/user/login/usernamepassword";

    @BeforeMethod
    public void precondition() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void loginPositive() {
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("marzh@com")
                .password("Qwe1234$")
                .build();

        AuthResponseDTO responseDTO = given()
                .body(requestDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .as(AuthResponseDTO.class);

        System.out.println(responseDTO);
    }

    @Test
    public void loginNegativeWrongEmail() {
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("marzhcom")
                .password("Qwe1234$")
                .build();

        ErrorDTO errorDTO = given()
                .body(requestDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(401)
                .extract()
                .as(ErrorDTO.class);

        System.out.println(errorDTO);
    }

}
