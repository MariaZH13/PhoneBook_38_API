package restassurted;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.ContactDTO;
import dto.ContactResponseDTO;
import dto.ErrorDTO;
import dto.TestHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class AddContactTest implements TestHelper {
    String endpoint = "/v1/contacts";

    @BeforeMethod
    public void precondition() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void addContactPositive(){

        ContactDTO requestDTO = ContactDTO.builder()
                .name("Harry")
                .lastName("Potter")
                .email("magician" + INT + "@gmail.com")
                .phone("12345678" + INT)
                .address("Haifa")
                .description("friend")
                .build();

        ContactResponseDTO contactResponseDTO = given()
                .header("Authorization",TOKEN)
                .body(requestDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .as(ContactResponseDTO.class);

        System.out.println(contactResponseDTO.getMessage() + " " + requestDTO.getEmail());

    }
    @Test
    public void addContactNegativeAnyFormatError(){

        ContactDTO requestDTO = ContactDTO.builder()
                .name("Harry")
                .lastName("Potter")
                .email("magician" + INT + "@gmail.com")
                .phone("12345678" + INT)
                .address("")
                .description("friend")
                .build();

        ErrorDTO contactResponseDTO = given()
                .header("Authorization",TOKEN)
                .body(requestDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(400)
                .extract()
                .as(ErrorDTO.class);

        System.out.println(contactResponseDTO.getMessage());

    }

}
