package okhttp;

import dto.ContactDTO;
import dto.ContactResponseDTO;
import dto.TestHelper;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteContactByIDTests implements TestHelper {

    String endpoint = "/v1/contacts/";

    String id;


    @BeforeMethod
    public void precondition() throws IOException {
           ContactDTO contact = ContactDTO.builder()
                .name("Mary")
                .lastName("May")
                .email("mary" + INT + "@mail.com")
                .phone("12345678" + INT)
                .address("Rehovot")
                .description("collegue")
                .build();
           RequestBody requestBody = RequestBody.create(gson.toJson(contact),JSON);

           Request request = new Request.Builder()
                .url(BASE_URL + "/v1/contacts")
                .addHeader("Authorization", TOKEN)
                .post(requestBody)
                .build();

           Response response = client.newCall(request).execute();
           ContactResponseDTO responseDTO = gson.fromJson(response.body().string(), ContactResponseDTO.class);
           Assert.assertTrue(response.isSuccessful());
           String message = responseDTO.getMessage();
           System.out.println(message);
           id = message.substring(message.lastIndexOf(" ") + 1);
    }

    @Test
    public void deleteContactByIdPositive() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + endpoint + id)
                .addHeader("Authorization", TOKEN)
                .delete()
                .build();

        Response response = client.newCall(request).execute();

        ContactResponseDTO responseDTO = gson.fromJson(response.body().string(),ContactResponseDTO.class);
        Assert.assertTrue(response.isSuccessful());
        String message = responseDTO.getMessage();
        System.out.println(message);
    }

}
