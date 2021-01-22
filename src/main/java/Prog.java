import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Prog {
    static String getUrl = "https://freegeoip.app/json/";

    public static void main(String[] args) {
        // GET request string
        String reqRet;

        // try to make a request
        try {
            reqRet = sendRequest(getUrl);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        // request string to json object
        JsonObject jsonObject = stringToJson(reqRet);

        // out all necessary fields
        System.out.println(getFields(jsonObject, "country_name", "region_name", "city", "latitude", "longitude"));
    }

    /**
     * provide info about given fields from json object
     * @param json json object with fields
     * @param fields requested fields to find info about
     * @return string
     * "Your info:
     * field1: field1 value
     * ...
     * fieldn: fieldn value"
     */
    public static String getFields(JsonObject json, String... fields){
        StringBuilder sb = new StringBuilder("Your info:" + System.lineSeparator());
        for(var field: fields)
            sb.append(field).append(": ").append(json.get(field).getAsString()).append(System.lineSeparator());
        return sb.toString();
    }

    /**
     * Convert string to json object
     * @param str string to convert
     * @return json object
     */
    public static JsonObject stringToJson(String str){
        return JsonParser.parseString(str).getAsJsonObject();
    }

    /**
     * send get request on given url string
     * @param urlTo string with url adress to send request
     * @return answer string
     */
    public static String sendRequest(String urlTo) throws IOException {
        // url
        URL url = new URL(urlTo);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        // params
        con.setRequestMethod("GET");
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);

        // get response
        int status = con.getResponseCode();

        // read response
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        // close connection
        con.disconnect();

        // out
        //System.out.println(content);

        return content.toString();
    }
}
