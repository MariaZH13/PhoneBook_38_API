package okhttp;

import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import dto.TestHelper;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTests implements TestHelper {

    @Test
    public void loginPositive() throws IOException {
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("marzh@com")
                .password("Qwe1234$")
                .build();
        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO),JSON);

        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/user/login/usernamepassword")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        if(response.isSuccessful()){

        AuthResponseDTO responseDTO = gson.fromJson(response.body().string(),AuthResponseDTO.class);
//        System.out.println(responseDTO.getToken());
        System.out.println("Response code is: " + response.code());
        Assert.assertTrue(response.isSuccessful());

        } else{
            System.out.println("Response code is: " + response.code());
            ErrorDTO errorDTO = gson.fromJson(response.body().string(), ErrorDTO.class);
            System.out.println(errorDTO.getMessage() + " " + errorDTO.getError() + " " + errorDTO.getStatus());

        }
    }

//    eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoibWFyemhAY29tIiwiaXNzIjoiUmVndWxhaXQiLCJleHAiOjE2OTA5MDU1MzEsImlhdCI6MTY5MDMwNTUzMX0.V6RrtZsKuqOku3qxVAypjX31GhKzn565_YoSYjYy_04
}
