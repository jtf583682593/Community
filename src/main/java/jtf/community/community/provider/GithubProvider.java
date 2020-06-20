package jtf.community.community.provider;

import com.alibaba.fastjson.JSON;
import jtf.community.community.DTO.AccessTokenDTO;
import jtf.community.community.DTO.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body=RequestBody.create(mediaType,JSON.toJSONString(accessTokenDTO));
        Request request=new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try(Response response=client.newCall(request).execute()) {
            String string=response.body().string();
            String str=string.split("&")[0].split("=")[1];
            return str;
        }catch (Exception e){
        }
        return  null;
    }

    public GithubUser gitUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String str=response.body().string();
            GithubUser githubUser=JSON.parseObject(str,GithubUser.class);
            return  githubUser;
        } catch (IOException e) {
        }
        return  null;
    }
}
