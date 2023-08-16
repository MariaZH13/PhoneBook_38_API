package dto;

import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public interface TestHelper {
    public final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    String BASE_URL = "https://contactapp-telran-backend.herokuapp.com";
    String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiYWRlbDI0OUBnbWFpbC5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTY5MTUxMzA1MSwiaWF0IjoxNjkwOTEzMDUxfQ.BxN_UrzYotvehj2ZCaNyWVZfN8Qv4-QyIINYAvTNc8w";
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    int INT = (int)(System.currentTimeMillis()/1000)%3600;

}