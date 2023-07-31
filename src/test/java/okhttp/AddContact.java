package okhttp;

import dto.ContactDTO;
import dto.ContactResponseDTO;
import dto.ErrorDTO;
import dto.TestHelper;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddContact implements TestHelper {


    @Test
    public void AddContactPositive() throws  IOException {

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
        if(response.isSuccessful()){
            ContactResponseDTO responseDTO = gson.fromJson(response.body().string(), ContactResponseDTO.class);
            String message = responseDTO.getMessage();
            System.out.println(message);
            System.out.println("Response code is " + response.code());
            System.out.println(contact.getId());
            System.out.println(contact.getEmail());
            Assert.assertTrue(response.isSuccessful());
            String id = message.substring(message.lastIndexOf(" ") + 1);
            System.out.println(id);

        } else{
            System.out.println("Response code is: "+ response.code());

            try {
                ErrorDTO errorDTO = gson.fromJson(response.body().string(), ErrorDTO.class);
                System.out.println(errorDTO.getMessage() + " " + errorDTO.getError() + " " + errorDTO.getStatus());

            } catch (Exception e) {
                System.out.println(e.getMessage());

            }

        }

    }
}
