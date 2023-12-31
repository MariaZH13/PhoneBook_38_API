package okhttp;

import dto.ContactDTO;
import dto.ContactListDTO;
import dto.TestHelper;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetAllContacts implements TestHelper {
//    Gson gson = new Gson();
//    OkHttpClient client = new OkHttpClient();
//    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoibWFyemhAY29tIiwiaXNzIjoiUmVndWxhaXQiLCJleHAiOjE2OTA5MDU1MzEsImlhdCI6MTY5MDMwNTUzMX0.V6RrtZsKuqOku3qxVAypjX31GhKzn565_YoSYjYy_04";


    @Test
    public void getAllContactsPositive() throws IOException {

        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
                .addHeader("Authorization", TOKEN)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());

        ContactListDTO contacts = gson.fromJson(response.body().string(), ContactListDTO.class);
        for(ContactDTO contactDTO : contacts.getContacts()){
            System.out.println(contactDTO.getId());
            System.out.println(contactDTO.getEmail());
            System.out.println("----------------------");
        }
    }
}
