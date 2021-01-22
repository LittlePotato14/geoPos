import com.google.gson.JsonObject;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ProgTest {
    /**
     * check if the json parser works correctly
     */
    @org.junit.jupiter.api.Test
    void stringToJson() {
        JsonObject jsonObj;
        String reqAnswer;

        try {
            reqAnswer = Prog.sendRequest(Prog.getUrl);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        jsonObj = Prog.stringToJson(reqAnswer);

        // check if it is an object
        assertTrue(jsonObj.isJsonObject());
        // check if it is a correct object for given string
        assertEquals(reqAnswer, jsonObj.toString());
    }
}