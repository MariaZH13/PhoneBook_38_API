package okhttp;

import dto.ContactResponseDTO;
import dto.TestHelper;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteAllContacts implements TestHelper {
    String endpoint = "/v1/contacts/clear";

    @Test
    public void deleteContactByIdPositive() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + endpoint)
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
