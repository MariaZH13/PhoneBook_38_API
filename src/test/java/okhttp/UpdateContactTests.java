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

public class UpdateContactTests implements TestHelper {

    String endpoint = "/v1/contacts";
    String id;

    @BeforeMethod
    public void precondition() throws IOException {
        ContactDTO contact = ContactDTO.builder()
                .name("Jack")
                .lastName("Jackson")
                .email("jackie" + INT + "@mail.com")
                .phone("12345678" + INT)
                .address("Ashdod")
                .description("friend")
                .build();
        RequestBody requestBody = RequestBody.create(gson.toJson(contact), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + endpoint)
                .addHeader("Authorization", TOKEN)
                .post(requestBody)
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ContactResponseDTO responseDTO = gson.fromJson(response.body().string(), ContactResponseDTO.class);
        Assert.assertTrue(response.isSuccessful());
        String message = responseDTO.getMessage();
        System.out.println(message + " " + contact.getAddress());
        id = message.substring(message.lastIndexOf(" ") + 1);
    }

    @Test
    public void updateContactPositive() throws IOException {
         ContactDTO contact = ContactDTO.builder()
                .id(id)
                 .name("Jack")
                .lastName("Jackson")
                .address("Bat Yam")
                .build();
        RequestBody requestBody = RequestBody.create(gson.toJson(contact), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + endpoint)
                .addHeader("Authorization", TOKEN)
                .put(requestBody)
                .build();

        Response response = client.newCall(request).execute();

        ContactResponseDTO responseDTO = gson.fromJson(response.body().string(),ContactResponseDTO.class);
        Assert.assertTrue(response.isSuccessful());
        System.out.println(responseDTO.getMessage() + " " + contact.getAddress());





    }
}