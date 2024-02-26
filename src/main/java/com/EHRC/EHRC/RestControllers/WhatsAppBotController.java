package com.EHRC.EHRC.RestControllers;

import com.EHRC.EHRC.CustomExceptions.PhoneNumberNotValidException;
import com.EHRC.EHRC.DTU.BotMenuNames;
import com.EHRC.EHRC.Repository.BotMenuRepository;
import com.EHRC.EHRC.Utilities.Utilities;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
//import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

//import javax.json.;

//import javax.*;


@RestController
@RequestMapping("/api")
public class WhatsAppBotController {
    @Autowired
    private Environment env;


    @Autowired
    private BotMenuRepository botMenuRepository;


    @PostMapping("/getChildMenuItems")
    public List<BotMenuNames> getMenuChildNames(@RequestParam String menuName) {
        System.out.println("List<BotMenuNames> getMenuChildNames called : " + menuName);
        return botMenuRepository.getBotChildMenuNames(menuName);
    }


    @GetMapping("/webhook")
    public ResponseEntity<String> webhookVerify(@RequestParam("hub.mode") String mode, @RequestParam("hub.challenge") String challenge, @RequestParam("hub.verify_token") String token) {
        System.out.println(mode);
        System.out.println(challenge);
        System.out.println(token);
        if (mode.equals("subscribe") && token.equals("Hello")) {
            return new ResponseEntity<>(challenge, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Verification token or mode mismatch", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/webhook")
    public void getRequest(@RequestBody String json) {
        System.out.println(json);



        ObjectMapper mapper = new ObjectMapper();
        // create instance of the File class
//        File fileObj = new File("C:\\Users\\rastogi ji\\OneDrive\\Desktop\\Sample.json");
        // use try-catch block to convert JSON data into Map
        try {

            Map<String, Object> userData = mapper.readValue(json, new TypeReference<Map<String, Object>>() {
            });

            // read JSON data from file using fileObj and map it using ObjectMapper and TypeReference classes
//            Map<String, Object> userData = mapper.readValue(
//                    fileObj, new TypeReference<Map<String, Object>>() {
//                    });
//            // print all key-value pairs
            System.out.println("Value parsed is " + userData);
            System.out.println("Name : " + userData.get("Name"));
            System.out.println("Mobile : " + userData.get("Mobile"));
            System.out.println("Designation : " + userData.get("Designation"));
            System.out.println("Pet : " + userData.get("Pet"));
            System.out.println("Address : " + userData.get("Address"));
        } catch (Exception e) {
            // show error message
            e.printStackTrace();
        }


        // Convert JSON to Map
//            Map<String, Object> map = convertJsonToMap(jsonString);
//
//            // Print the resulting Map
//            for (Map.Entry<String, Object> entry : map.entrySet()) {
//                System.out.println(entry.getKey() + ": " + entry.getValue());
//            }
    }




//        public static Map<String, Object> convertJsonToMap(String jsonString) {
//
//            JSONObject jsonObject = new JSONObject(jsonString);
//            Map<String, Object> map = new HashMap<>();
//
//            for (Iterator it = jsonObject.keys(); it.hasNext(); ) {
//                String key = it.next();
//                Object value = jsonObject.get(key);
//                map.put(key, value);
//            }
//
//            return map;
//        }











//    /*
//
//
////    import org.json.JSONObject;
////import java.util.HashMap;
////import java.util.Map;
//
//    public class JsonToMapConverter {
//        public static void main(String[] args) {
//            // Assume we have a JSON string:
//            String jsonString = "{\"name\":\"John\",\"age\":30,\"city\":\"New York\"}";
//
//            // Convert JSON to Map
//            Map<String, Object> map = convertJsonToMap(jsonString);
//
//            // Print the resulting Map
//            for (Map.Entry<String, Object> entry : map.entrySet()) {
//                System.out.println(entry.getKey() + ": " + entry.getValue());
//            }
//        }
//
//        public static Map<String, Object> convertJsonToMap(String jsonString) {
//            JSONObject jsonObject = new JSONObject(jsonString);
//            Map<String, Object> map = new HashMap<>();
//
//            for (String key : jsonObject.keySet()) {
//                Object value = jsonObject.get(key);
//                map.put(key, value);
//            }
//
//            return map;
//        }
//    }*/
//
















    @GetMapping("/sendTestMessage/{whatsAppNumber}")
    public String getHelloWorld(@PathVariable String whatsAppNumber) throws IOException {

        String baseURL = env.getProperty("whatsappbot.api.baseurl");
        String APIVersionNumber = env.getProperty("whatsappbot.api.version");
        String phoneNumberID = env.getProperty("whatsappbot.api.phone_number_id");
        String messageApiPath = env.getProperty("whatsappbot.api.api_path");

        String fullAPIPath = baseURL + APIVersionNumber + "/" + phoneNumberID + "/" + messageApiPath;

        Utilities utilities = new Utilities();

        if (!utilities.isValid(whatsAppNumber)) {
            throw new PhoneNumberNotValidException("Phone Number that you entered is not valid. Please enter a valid phone number.");
        }

        System.out.println("Whatsapp number is " + fullAPIPath);

        String body2 = "{messaging_product:\"whatsapp\",recipient_type:\"individual\",to:\"919015346166\",type:\"interactive\",interactive:{type:\"list\",header:{type:\"text\",text:\"Select the food item you would like.\",},body:{text:\"You will be presented with a list of options to choose from.\",},footer:{text:\"All of them are freshly packed.\",},action:{button:\"Order\",sections:[{title:\"Section1-Fruit\",rows:[{id:\"1\",title:\"Apple\",description:\"Dozen\",},{id:\"2\",title:\"Orange\",description:\"Dozen\",},],},{title:\"Section2-Vegetables\",rows:[{id:\"3\",title:\"Spinach\",description:\"1kg\",},{id:\"2\",title:\"Broccoli\",description:\"1kg\",},],},],},},}";

//        String body2 = "{\"from\":\"224738690728648\",\"to\":\"919015346166\"," +
//                "\"channel\":\"whatsapp\",\"message_type\":\"custom\"," +
//                "\"custom\":{\"type\":\"interactive\",\"interactive\":" +
//                "{\"type\":\"list\",\"header\":{\"type\":\"text\"," +
//                "\"text\":\"Selectwhichpillyouwouldlike\"},\"body\":" +
//                "{\"text\":\"Youwillbepresentedwithalistofoptions\"},\"footer\":" +
//                "{\"text\":\"Therearenowrongchoices\"},\"action\":{\"button\":\"Select\",\"sections\":" +
//                "[{\"title\":\"SectionA-pills\",\"rows\":" +
//                "[{\"id\":\"row1\",\"title\":\"Red\",\"description\":\"Taketheredpill\"}," +
//                "{\"id\":\"row2\",\"title\":\"Blue\",\"description\":\"Takethebluepill\"}," +
//                "{\"id\":\"row3\",\"title\":\"Green\",\"description\":\"Takethegreenpill\"}]}," +
//                "{\"title\":\"SectionB-nopills\",\"rows\":" +
//                "[{\"id\":\"row4\",\"title\":\"Nothing\",\"description\":\"Donottakeapill\"}]}]}}}}";


//        String body2 = "{\"messaging_product\":\"whatsapp\",\"recipient_type\":\"individual\"," +
//                "\"to\":" + "\"" + "91" +
//                whatsAppNumber + "\"" +
//                ",\"type\":\"template\"," +
//                "\"template\":{\"name\":\"dhruv\",\"language\":{\"code\":\"en\"}," +
//                "\"components\":[{\"type\":\"header\",\"parameters\":[{\"type\":\"image\"," +
//                "\"image\":{\"link\":\"https://images.spoonacular.com/file/wximages/423186-636x393.png\"}}]}," +
//                "{\"type\":\"body\",\"parameters\":[{\"type\":\"text\",\"text\":\"My name is rahul Jagger!!!!  \"}," +
//                "{\"type\":\"currency\",\"currency\":" +
//                "{\"fallback_value\":\"VALUE\",\"code\":\"USD\",\"amount_1000\":3500}}]}," +
//                "{\"type\":\"button\",\"sub_type\":\"quick_reply\",\"index\":\"0\",\"parameters\":" +
//                "[{\"type\":\"payload\",\"payload\":\"A\"}]}," +
//                "{\"type\":\"button\",\"sub_type\":\"quick_reply\",\"index\":\"1\",\"parameters\":" +
//                "[{\"type\":\"payload\",\"payload\":\"A\"}]}," +
//                "{\"type\":\"button\",\"sub_type\":\"quick_reply\",\"index\":\"2\"," +
//                "\"parameters\":[{\"type\":\"payload\",\"payload\":\"aGlzIHRoaXMgaXMgY29vZHNhc2phZHdpcXdlMGZoIGFTIEZISUQgV1FEV0RT\"}]}]}}";


        System.out.println("Body is : " + body2);

        String bearerTokenValue = "Bearer " + env.getProperty("whatsappbot.api.bearer_token");

        System.out.println("bearer value = " + bearerTokenValue);

        URL url = new URL(fullAPIPath);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Authorization", bearerTokenValue);
        conn.setRequestProperty("Content-Type", "application/json");

        try (DataOutputStream dos = new DataOutputStream(conn.getOutputStream())) {
            dos.writeBytes(body2);
        }

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = bf.readLine()) != null) {
                System.out.println("respose is " + line);
            }
        }
        return fullAPIPath;
    }
}



/*
public class SendCustomTemplateMessage {

    //customfoodmessagetemplate

    String body = "\"{\"messaging_product\":\"whatsapp\"," +
            "\"recipient_type\":\"individual\"," +
            "\"to\":\"919015346166\"" +
            ",\"type\":\"template\"," +
            "\"template\":{\"name\":\"customfoodmessagetemplate\"," +
            "\"language\":{\"code\":\"en\"}," +
            "\"components\":[{\"type\":\"header\"," +
            "\"parameters\":[{\"type\":\"image\",\"image\":" +
            "{\"link\":\"https://images.spoonacular.com/file/wximages/423186-636x393.png\"}}]}," +
            "{\"type\":\"body\",\"parameters\":" +
            "[{\"type\":\"text\",\"text\":\"Hello!!! Welcome to the world of burgers.\"}," +
            "{\"type\":\"currency\",\"currency\":" +
            "{\"fallback_value\":\"$3.5\",\"code\":\"USD\",\"amount_1000\":\"3500\"}}," +
            "{\"type\":\"date_time\",\"date_time\":{\"fallback_value\":\"July 20,2024\"}}]}," +


            "{\"type\":\"button\",\"sub_type\":\"quick_reply\",\"index\":\"0\"," +
            "\"parameters\":[{\"type\":\"payload\",\"payload\":\"A\"}]}," +
            "{\"type\":\"button\",\"sub_type\":\"quick_reply\",\"index\":\"1\"," +
            "\"parameters\":[{\"type\":\"payload\",\"payload\":\"B\"}]}]}}\"";

}
*/

//
//{
//        messaging_product: "whatsapp",
//        recipient_type: "individual",
//        to: "919015346166",
//        type: "interactive",
//        interactive: {
//        type: "list",
//        header: {
//        type: "text",
//        text: "Select the food item you would like",
//        },
//        body: {
//        text: "You will be presented with a list of options to choose from",
//        },
//        footer: {
//        text: "All of them are freshly packed",
//        },
//        action: {
//        button: "Order",
//        sections: [
//        {
//        title: "Section 1 - Fruit",
//        rows: [
//        {
//        id: "1",
//        title: "Apple",
//        description: "Dozen",
//        },
//        {
//        id: "2",
//        title: "Orange",
//        description: "Dozen",
//        },
//        ],
//        },
//        {
//        title: "Section 2 - Vegetables",
//        rows: [
//        {
//        id: "3",
//        title: "Spinach",
//        description: "1kg ",
//        },
//        {
//        id: "2",
//        title: "Broccoli",
//        description: "1kg",
//        },
//        ],
//        },
//        ],
//        },
//        },
//        }

