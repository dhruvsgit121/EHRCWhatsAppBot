package com.EHRC.EHRC.RestControllers;

import com.EHRC.EHRC.CustomExceptions.PhoneNumberNotValidException;
import com.EHRC.EHRC.DTU.BotMenuNames;
import com.EHRC.EHRC.Processors.WhatsAppMessageBodyProcessor;
import com.EHRC.EHRC.Repository.BotMenuRepository;
import com.EHRC.EHRC.Utilities.Utilities;
import com.EHRC.EHRC.WhatsAppMessagesWrapper.InteractiveMessage.InteractiveMessageOuterWrapper;
import com.EHRC.EHRC.WhatsappMessageResponseEntities.WebHookResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
import com.fasterxml.jackson.databind.ObjectWriter;
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
import java.net.ProtocolException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.*;


@RestController
@RequestMapping("/api")
public class WhatsAppBotController {
    @Autowired
    private Environment env;

    @Autowired
    private BotMenuRepository botMenuRepository;

//    @Autowired
    private WhatsAppMessageBodyProcessor messageBodyProcessor;

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

    @GetMapping("/test")
    public String getTestData() throws Exception {

        String jsonString = "{\"object\":\"whatsapp_business_account\",\"entry\":[{\"id\":\"244715655388071\",\"changes\":[{\"value\":{\"messaging_product\":\"whatsapp\",\"metadata\":{\"display_phone_number\":\"15551291482\",\"phone_number_id\":\"224738690728648\"},\"contacts\":[{\"profile\":{\"name\":\"DhruvGupta\"},\"wa_id\":\"919015346166\"}],\"messages\":[{\"from\":\"919015346166\",\"id\":\"wamid.HBgMOTE5MDE1MzQ2MTY2FQIAEhgWM0VCMEExNUVDQzRENDRDN0RBNThGNgA=\",\"timestamp\":\"1708980080\",\"text\":{\"body\":\"he;llo\"},\"type\":\"text\"}]},\"field\":\"messages\"}]}]}";
//        ObjectMapper objectMapper = new ObjectMapper();
        //Person person = objectMapper.readValue(jsonString, Person.class);

//        WebHookResponseBody webHook = objectMapper.readValue(jsonString, WebHookResponseBody.class);


        System.out.println(jsonString);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            WebHookResponseBody webHook = objectMapper.readValue(jsonString, WebHookResponseBody.class);

            if (webHook.getEntry() != null && webHook.getEntry().length > 0 && webHook.getEntry()[0].getChanges() != null &&
                    webHook.getEntry()[0].getChanges().length > 0 && webHook.getEntry()[0].getChanges()[0].getValue() != null
                    && webHook.getEntry()[0].getChanges()[0].getValue().getMessages() != null && webHook.getEntry()[0].getChanges()[0].getValue().getMessages().length > 0
                    && webHook.getEntry()[0].getChanges()[0].getValue().getMessages().length > 0 && webHook.getEntry()[0].getChanges()[0].getValue().getMessages()[0].getText() != null && webHook.getEntry()[0].getChanges()[0].getValue().getMessages()[0].getText().getBody() != null) {
                System.out.println("Falling in if block " + webHook.getEntry()[0].getChanges()[0].getValue().getMessages()[0].getText().getBody());
            } else {
                System.out.println("Falling in else Block!!!");
            }
        } catch (Exception e) {
            System.out.println("Exception occurred : " + e);
        };

//        System.out.println("Map data parsed is : " + webHook);
//
//        System.out.println(webHook.getEntry()[0].getId());
//        System.out.println(webHook.getObject());
//        System.out.println(webHook.getEntry()[0].getChanges());
//
//        System.out.println(webHook.getEntry()[0].getChanges()[0].getField());
//        System.out.println(webHook.getEntry()[0].getChanges()[0].getValue());


//        System.out.println(webHook.getEntry()[0].getChanges()[0].getValue().getMessages()[0]);


        return "Hello Test!!!";
    }


    @PostMapping("/webhook")
    public void getRequest(@RequestBody String json) {

        System.out.println(json);


       // {"object":"whatsapp_business_account","entry":[{"id":"244715655388071","changes":[{"value":{"messaging_product":"whatsapp","metadata":{"display_phone_number":"15551291482","phone_number_id":"224738690728648"},"contacts":[{"profile":{"name":"Dhruv Gupta"},"wa_id":"919015346166"}],"messages":[{"context":{"from":"15551291482","id":"wamid.HBgMOTE5MDE1MzQ2MTY2FQIAERgSRDE5N0M5NTlCNUVFMjgyOTgzAA=="},"from":"919015346166","id":"wamid.HBgMOTE5MDE1MzQ2MTY2FQIAEhggRDU2RDhBNDNDREUxQzZCQzcwQUFFODAwMTYyODk1NEUA","timestamp":"1709045742","type":"interactive","interactive":{"type":"list_reply","list_reply":{"id":"1","title":"Rahul"}}}]},"field":"messages"}]}]}

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            WebHookResponseBody webHook = objectMapper.readValue(json, WebHookResponseBody.class);

            if (webHook.getEntry() != null && webHook.getEntry().length > 0 && webHook.getEntry()[0].getChanges() != null &&
                    webHook.getEntry()[0].getChanges().length > 0 && webHook.getEntry()[0].getChanges()[0].getValue() != null
                    && webHook.getEntry()[0].getChanges()[0].getValue().getMessages() != null && webHook.getEntry()[0].getChanges()[0].getValue().getMessages().length > 0
                    && webHook.getEntry()[0].getChanges()[0].getValue().getMessages().length > 0 && webHook.getEntry()[0].getChanges()[0].getValue().getMessages()[0].getText() != null && webHook.getEntry()[0].getChanges()[0].getValue().getMessages()[0].getText().getBody() != null) {
                String message = webHook.getEntry()[0].getChanges()[0].getValue().getMessages()[0].getText().getBody();
                String senderNumber = webHook.getEntry()[0].getChanges()[0].getValue().getMessages()[0].getFrom();
                System.out.println("Falling in if block " + message);
                System.out.println("Whatsapp message recieved from " + senderNumber);
                sendWhatsAppMessage(message, senderNumber);
            } else {
                System.out.println("Falling in else Block!!!");
            }
        } catch (Exception e) {
            System.out.println("Exception occurred : " + e);
        }
    }

    public void sendWhatsAppMessage(String message, String senderWhatsAppNumber) throws IOException {

        List<BotMenuNames> menuNames = botMenuRepository.getBotChildMenuNames(message);

        System.out.println("****************** sendWhatsAppMessage Called *********************");
        System.out.println("Param is : " + message);
        System.out.println("Child Menu names are : " + menuNames);

        if(menuNames.size() == 0){
            //Return the default message to be sent...


        }else{
            //Return the Child Menu Options to be sent...
            String JSONBody = messageBodyProcessor.getWhatsAppInteractiveMessageWithChildMenuItemsJSON("", senderWhatsAppNumber, menuNames);
            System.out.println("JSON Data is : " + JSONBody);
            String whatsappServerResponse = hitWhatsAppServerMessageRequestWithBody(JSONBody);
            System.out.println("Response after hitting server is " + whatsappServerResponse);
        }
    }


    @GetMapping("/get")
    public String getStr() throws IOException {

        if(messageBodyProcessor == null)
            messageBodyProcessor = new WhatsAppMessageBodyProcessor();

        List<BotMenuNames> name = new ArrayList<>();

        name.add(new BotMenuNames(10, "dhruv", 20, "Rahul"));
        name.add(new BotMenuNames(30, "dhruv1", 50, "Rahul1"));
        name.add(new BotMenuNames(40, "dhruv2", 60, "Rahul2"));

        String JSONBody = messageBodyProcessor.getWhatsAppInteractiveMessageWithChildMenuItemsJSON("", "919015346166", name);

        System.out.println("JSON Data is : " + JSONBody);

        String whatsappServerResponse = hitWhatsAppServerMessageRequestWithBody(JSONBody);
        return JSONBody;
    }



    public String hitWhatsAppServerMessageRequestWithBody(String JSON) throws IOException {

        String baseURL = env.getProperty("whatsappbot.api.baseurl");
        String APIVersionNumber = env.getProperty("whatsappbot.api.version");
        String phoneNumberID = env.getProperty("whatsappbot.api.phone_number_id");
        String messageApiPath = env.getProperty("whatsappbot.api.api_path");

        String fullAPIPath = baseURL + APIVersionNumber + "/" + phoneNumberID + "/" + messageApiPath;

//        Utilities utilities = new Utilities();

        System.out.println("Request URL is " + fullAPIPath);
        System.out.println("JSON data is " + JSON);

        String bearerTokenValue = "Bearer " + env.getProperty("whatsappbot.api.bearer_token");

        System.out.println("bearer value = " + bearerTokenValue);

        URL url = new URL(fullAPIPath);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Authorization", bearerTokenValue);
        conn.setRequestProperty("Content-Type", "application/json");

        try (DataOutputStream dos = new DataOutputStream(conn.getOutputStream())) {
            dos.writeBytes(JSON);
        }

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = bf.readLine()) != null) {
                System.out.println("respose is " + line);
            }
        }
        return fullAPIPath;
    }

//    {
//        "messaging_product" : "whatsapp",
//            "recipient_type" : "individual",
//            "to" : "919015346166",
//            "type" : "interactive",
//            "interactive" : {
//        "type" : "list",
//                "header" : {
//            "text" : "Please select the option, which you want to choose and we can help you in that field.",
//                    "type" : "text"
//        },
//        "body" : {
//            "text" : "This is the body text."
//        },
//        "footer" : {
//            "text" : "This is the demo footer message."
//        },
//        "action" : {
//            "button" : "Menu Options",
//                    "sections" : [ {
//                "rows" : [ {
//                    "id" : "1",
//                            "title" : "Rahul"
//                }, {
//                    "id" : "2",
//                            "title" : "Rahul1"
//                }, {
//                    "id" : "3",
//                            "title" : "Rahul2"
//                } ]
//            } ]
//        }
//    }
//    }





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

        String body2 = "{\"messaging_product\":\"whatsapp\",\"recipient_type\":\"individual\",\"to\":\"919015346166\",\"type\":\"interactive\",\"interactive\":{\"type\":\"list\",\"header\":{\"type\":\"text\",\"text\":\"Selectthefooditemyouwouldlike.\"},\"body\":{\"text\":\"Youwillbepresentedwithalistofoptionstochoosefrom.\"},\"footer\":{\"text\":\"Allofthemarefreshlypacked.\"},\"action\":{\"button\":\"Order\",\"sections\":[{\"rows\":[{\"id\":\"1\",\"title\":\"Apple\"},{\"id\":\"2\",\"title\":\"Orange\"}]}]}}}";

//        String body2 = "{messaging_product:\"whatsapp\",recipient_type:\"individual\",to:\"919015346166\",type:\"interactive\",interactive:{type:\"list\",header:{type:\"text\",text:\"Select the food item you would like.\",},body:{text:\"You will be presented with a list of options to choose from.\",},footer:{text:\"All of them are freshly packed.\",},action:{button:\"Order\",sections:[{rows:[{id:\"1\",title:\"Apple\"},{id:\"2\",title:\"Orange\"},],},],},},}";
        System.out.println(body2);
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

