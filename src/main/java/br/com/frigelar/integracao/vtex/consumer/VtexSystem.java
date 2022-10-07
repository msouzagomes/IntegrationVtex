package br.com.frigelar.integracao.vtex.consumer;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class VtexSystem {

    @Value("${spring.vtex.baseurl}")
    private String baseUrl;

    @Value("${spring.vtex.appkey}")
    private String appkey;

    @Value("${spring.vtex.apptoken}")
    private String apptoken;

    private JSONObject json = null;
    private Request request = null;
    private OkHttpClient client = null;

    public String jsonVtexSystem(String url){
        String baseUrlVtex = baseUrl.concat(url);
        log.info("UrlVtex :" + baseUrlVtex);
        try {
            return this.jsonVtex(baseUrlVtex);
        } catch (Exception ex) {
            log.error("Erro acessar o Vtex " + ex.getMessage());
            return null;
        }
    }

    private String jsonVtex(String urlVtex) throws JSONException, IOException {

        if(this.client == null)
            this.client = new OkHttpClient().newBuilder().build();

        this.request = new Request.Builder()
                .url(urlVtex)
                .method("GET", null)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("X-VTEX-API-AppKey", this.appkey)
                .addHeader("X-VTEX-API-AppToken", this.apptoken)
                .addHeader("Cookie", "janus_sid=57bd45e0-175a-4666-b69e-e76b5a3f493c")
                .build();

        Response response = this.client.newCall(this.request).execute();

        if(this.json == null)
            json = new JSONObject();

        json.put("jvtex",response.body().string());
        response.body().close();
        return json.getString("jvtex");
    }
}
