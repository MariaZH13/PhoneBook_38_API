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

public class UpdateContactTests implements TestHelper {
    String endpoint = "/v1/contacts";

    ContactDTO contactDTO;
    String id;

    @BeforeMethod
    public void precondition() {
        RestAssured.baseURI = BASE_URL;
           contactDTO = ContactDTO.builder()
                .name("Ron")
                .lastName("Weasley")
                .email("ron" + INT + "@gmail.com")
                .phone("1238890" + INT)
                .address("Tel Aviv")
                .description("friend")
                .build();

        ContactResponseDTO contactResponseDTO = given()
                .header("Authorization",TOKEN)
                .body(contactDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .as(ContactResponseDTO.class);

        String message = contactResponseDTO.getMessage();
        System.out.println(message + " " +  contactDTO.getName());
        id = message.substring(message.lastIndexOf(" ") + 1);
    }

    @Test
    public void updateContactPositive(){
         contactDTO.setId(id);
         contactDTO.setName("Ronny");

       ContactResponseDTO contactResponseDTO = given()
                .header("Authorization",TOKEN)
                .body(contactDTO)
                .contentType(ContentType.JSON)
                .when()
                .put(endpoint)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .as(ContactResponseDTO.class);

        System.out.println(contactResponseDTO.getMessage() + " " + contactDTO.getName());

    }
    @Test
    public void updateContactNegative(){
        ContactDTO requestDTO = ContactDTO.builder()
                .id("123456789Q")
                .name("Ron")
                .lastName("Weasley")
                .address("Rishon")
                .build();

       ErrorDTO errorDTO = given()
               .header("Authorization",TOKEN)
                .body(requestDTO)
                .contentType(ContentType.JSON)
                .when()
                .put(endpoint)
                .then()
                .assertThat().statusCode(400)
                .extract()
                .as(ErrorDTO.class);

        System.out.println(errorDTO.getMessage());
    }
}

