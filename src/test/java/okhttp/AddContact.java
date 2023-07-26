package okhttp;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dto.ContactDTO;
import dto.ContactResponseDTO;
import dto.ErrorDTO;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddContact implements TestHelper{

    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();

    @Test
    public void AddContactPositive() throws  IOException {

        ContactDTO contact = ContactDTO.builder()
                .name("Mary")
                .lastName("May")
                .email("mary" + i + "@mail.com")
                .phone("1234" + i)
                .address("Rehovot")
                .description("collegue")
                .build();
        RequestBody requestBody = RequestBody.create(gson.toJson(contact),JSON);

        Request request = new Request.Builder()
                .url(baseUrl + "/v1/contacts")
                .addHeader("Authorization",token)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        if(response.isSuccessful()){
            ContactResponseDTO responseDTO = gson.fromJson(response.body().string(), ContactResponseDTO.class);
            System.out.println(responseDTO.getMessage());
            System.out.println("Response code is " + response.code());
            System.out.println(contact.getId());
            System.out.println(contact.getEmail());
            Assert.assertTrue(response.isSuccessful());

        } else{
            System.out.println("Response code is: "+ response.code());
            ErrorDTO errorDTO = gson.

            try {
                ErrorDTO errorDTO = gson.fromJson(response.body().string(), ErrorDTO.class);
//                System.out.println(errorDTO.getMessage() + " " + errorDTO.getError() + " " + errorDTO.getStatus());

            } catch (Exception e) {
                System.out.println(e.getMessage());

            }

        }

    }
}
