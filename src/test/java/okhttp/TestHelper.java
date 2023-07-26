package okhttp;

import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public interface TestHelper {
    public final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    String baseUrl = "https://contactapp-telran-backend.herokuapp.com";

    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoia2l3aTEyQG1haWwuY29tIiwiaXNzIjoiUmVndWxhaXQiLCJleHAiOjE2OTA5NzA4MTMsImlhdCI6MTY5MDM3MDgxM30.QWIN66semSckxtMMreZLV-lhAtExubXoCrPZVtuG01g";

//    Gson gson = new Gson();
//    OkHttpClient client = new OkHttpClient();

    int i = (int)(System.currentTimeMillis()/1000)%3600;

}