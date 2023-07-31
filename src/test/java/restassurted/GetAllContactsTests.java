package restassurted;

import com.jayway.restassured.RestAssured;
import dto.ContactDTO;
import dto.ContactListDTO;
import dto.TestHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class GetAllContactsTests implements TestHelper {

    String endpoint = "/v1/contacts";

    @BeforeMethod
    public void precondition() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void getAllContactsPositive() {

        ContactListDTO listDTO = given()
                .header("Authorization", TOKEN)
                .when()
                .get(endpoint)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .as(ContactListDTO.class);

        for (ContactDTO contactDTO : listDTO.getContacts()) {
            System.out.println(contactDTO.getId());
            System.out.println(contactDTO.getEmail());
            System.out.println("----------------------");
        }
    }
}