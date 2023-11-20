package hello.apitest;

import org.apache.tomcat.util.json.JSONParser;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

@Controller
public class ApiController {

    @GetMapping("/api")
    public String api() throws IOException {
        String key = "202310746OIX95182";
        String result = "";

        URL url = null;
        try {
            url = new URL("https://wise.uos.ac.kr/uosdoc/api.ApiApiMainBd.oapi?apiKey="+key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        BufferedReader bf;

        bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

        result = bf.readLine();

        JSONObject json=XML.toJSONObject(result);
        JSONParser jsonParser = new JSONParser(result);
        JSONObject jsonObject = (JSONObject)jsonParser.parse();
        JSONObject movieInfoResult = (JSONObject)jsonObject.get("movieInfoResult");
        JSONObject movieInfo = (JSONObject)movieInfoResult.get("movieInfo");
        System.out.println(result);

        return "hello";
    }

}
