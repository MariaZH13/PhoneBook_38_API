package okhttp;


import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import dto.TestHelper;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationTests implements TestHelper {

//Gson gson = new Gson();
//OkHttpClient client = new OkHttpClient();

@Test
public void registrationPositive() throws IOException {

    AuthRequestDTO requestDTO = AuthRequestDTO.builder()
            .username("kiwi" + INT + "@mail.com")
            .password("Zxcvb0" + INT + "$")
            .build();
    RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO),JSON);

    Request request = new Request.Builder()
            .url(BASE_URL + "/v1/user/registration/usernamepassword")
            .post(requestBody)
            .build();

    Response response = client.newCall(request).execute();

    if(response.isSuccessful()){

       AuthResponseDTO responseDTO = gson.fromJson(response.body().string(), AuthResponseDTO.class);
        System.out.println(responseDTO.getToken());
        System.out.println("Response code is: " + response.code());
        System.out.println(requestDTO.getUsername());
        Assert.assertTrue(response.isSuccessful());

    }else {
        System.out.println("Response code is: " + response.code());
        ErrorDTO errorDTO = gson.fromJson(response.body().string(), ErrorDTO.class);
        System.out.println(errorDTO.getStatus() + " " + errorDTO.getMessage() + " " + errorDTO.getError());
        Assert.assertTrue(!response.isSuccessful());
    }
}





}
