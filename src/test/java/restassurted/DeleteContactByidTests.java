package restassurted;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.ContactDTO;
import dto.ContactResponseDTO;
import dto.ErrorDTO;
import dto.TestHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.jayway.restassured.RestAssured.given;

public class DeleteContactByidTests implements TestHelper {

    String endpoint = "/v1/contacts/";
    String id;

    @BeforeMethod
    public void precondition() throws IOException {
        RestAssured.baseURI = BASE_URL;
        ContactDTO requestDTO = ContactDTO.builder()
                .name("Hermione")
                .lastName("Granger")
                .email("hermiG" + INT + "@gmail.com")
                .phone("1234555" + INT)
                .address("Tel Aviv")
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

        String message = contactResponseDTO.getMessage();
        System.out.println(message);
        id = message.substring(message.lastIndexOf(" ") + 1);
    }

    @Test
    public void deleteContactByidPositive(){

        ContactResponseDTO contactResponseDTO = given()
                .header("Authorization",TOKEN)
                .contentType(ContentType.JSON)
                .when()
                .delete(endpoint + id)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .as(ContactResponseDTO.class);

        System.out.println(contactResponseDTO.getMessage());

    }

    @Test
    public void deleteContactByidNegativeAnyFormatError(){

        ErrorDTO errorDTO = given()
                .header("Authorization",TOKEN)
                .contentType(ContentType.JSON)
                .when()
                .delete(endpoint + "d868ac44-fb9b-447f-bea2")
                .then()
                .assertThat().statusCode(400)
                .extract()
                .as(ErrorDTO.class);

        System.out.println(errorDTO.getMessage());

    }
}
