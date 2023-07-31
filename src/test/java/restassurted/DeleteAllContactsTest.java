package restassurted;

import com.jayway.restassured.RestAssured;
import dto.ContactResponseDTO;
import dto.ErrorDTO;
import dto.TestHelper;
import okhttp3.Request;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class DeleteAllContactsTest implements TestHelper {

    String endpoint = "/v1/contacts/clear";

    @BeforeMethod
    public void precondition() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void deleteAllContactsPositive(){
        ContactResponseDTO contactResponseDTO = given()
                .header("Authorization", TOKEN)
                .when()
                .delete(endpoint)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .as(ContactResponseDTO.class);

        System.out.println(contactResponseDTO.getMessage());

    }

}
