package com.EHRC.EHRC.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;


@RestController
@RequestMapping("/api")
public class WhatsAppBotController {
    @Autowired
    private Environment env;

    @GetMapping("/sendTestMessage/{whatsAppNumber}")
    public String getHelloWorld(@PathVariable String whatsAppNumber) throws IOException {

        String baseURL = env.getProperty("whatsappbot.api.baseurl");
        String APIVersionNumber = env.getProperty("whatsappbot.api.version");
        String phoneNumberID = env.getProperty("whatsappbot.api.phone_number_id");
        String messageApiPath = env.getProperty("whatsappbot.api.api_path");

        String fullAPIPath = baseURL + APIVersionNumber + "/" + phoneNumberID + "/" + messageApiPath;

        String body2 = "{\"messaging_product\":\"whatsapp\",\"recipient_type\":\"individual\"," +
                "\"to\":" + "\""  + "91" +
                whatsAppNumber + "\""  +
                ",\"type\":\"template\"," +
                "\"template\":{\"name\":\"dhruv\",\"language\":{\"code\":\"en\"}," +
                "\"components\":[{\"type\":\"header\",\"parameters\":[{\"type\":\"image\"," +
                "\"image\":{\"link\":\"https://images.spoonacular.com/file/wximages/423186-636x393.png\"}}]}," +
                "{\"type\":\"body\",\"parameters\":[{\"type\":\"text\",\"text\":\"Helloworld\"}," +
                "{\"type\":\"currency\",\"currency\":" +
                "{\"fallback_value\":\"VALUE\",\"code\":\"USD\",\"amount_1000\":3500}}]}," +
                "{\"type\":\"button\",\"sub_type\":\"quick_reply\",\"index\":\"0\",\"parameters\":" +
                "[{\"type\":\"payload\",\"payload\":\"A\"}]}," +
                "{\"type\":\"button\",\"sub_type\":\"quick_reply\",\"index\":\"1\"," +
                "\"parameters\":[{\"type\":\"payload\",\"payload\":\"B\"}]}]}}" ;

       // String body2 = "{\"messaging_product\":\"whatsapp\",\"recipient_type\":\"individual\",\"to\":\"919015346166\",\"type\":\"template\",\"template\":{\"name\":\"dhruv\",\"language\":{\"code\":\"en\"},\"components\":[{\"type\":\"header\",\"parameters\":[{\"type\":\"image\",\"image\":{\"link\":\"https://images.spoonacular.com/file/wximages/423186-636x393.png\"}}]},{\"type\":\"body\",\"parameters\":[{\"type\":\"text\",\"text\":\"Helloworld\"},{\"type\":\"currency\",\"currency\":{\"fallback_value\":\"VALUE\",\"code\":\"USD\",\"amount_1000\":3500}}]},{\"type\":\"button\",\"sub_type\":\"quick_reply\",\"index\":\"0\",\"parameters\":[{\"type\":\"payload\",\"payload\":\"A\"}]},{\"type\":\"button\",\"sub_type\":\"quick_reply\",\"index\":\"1\",\"parameters\":[{\"type\":\"payload\",\"payload\":\"B\"}]}]}}" ;


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
                System.out.println(line);
            }
        }
        return fullAPIPath;
    }

}

//String pageTitle = env.getProperty("default.page.title");


//https://graph.facebook.com/v18.0/224738690728648/messages
//    private String baseURL = "https://graph.facebook.com/";
//    private String APIVersionNumber = "v18.0";
//    private String phoneNumberID = "224738690728648";
//    private String sendMessageAPIPath = "messages";
//
//    public void sendMessage() throws Exception{
//        String fullAPIPath = baseURL + APIVersionNumber + "/" + phoneNumberID + "/" + sendMessageAPIPath ;
//        System.out.println("sendMessage called : " + fullAPIPath);
//
//        String body = "{\"messaging_product\": \"whatsapp\", \"to\": \"919015346166\", \"type\": \"template\", \"template\": { \"name\": \"hello_world\", \"language\": { \"code\": \"en_US\" } }}";
//        String body2 = "{\"messaging_product\":\"whatsapp\",\"recipient_type\":\"individual\",\"to\":\"919015346166\",\"type\":\"template\",\"template\":{\"name\":\"dhruv\",\"language\":{\"code\":\"en\"},\"components\":[{\"type\":\"header\",\"parameters\":[{\"type\":\"image\",\"image\":{\"link\":\"https://images.spoonacular.com/file/wximages/423186-636x393.png\"}}]},{\"type\":\"body\",\"parameters\":[{\"type\":\"text\",\"text\":\"Helloworld\"},{\"type\":\"currency\",\"currency\":{\"fallback_value\":\"VALUE\",\"code\":\"USD\",\"amount_1000\":3500}}]},{\"type\":\"button\",\"sub_type\":\"quick_reply\",\"index\":\"0\",\"parameters\":[{\"type\":\"payload\",\"payload\":\"A\"}]},{\"type\":\"button\",\"sub_type\":\"quick_reply\",\"index\":\"1\",\"parameters\":[{\"type\":\"payload\",\"payload\":\"B\"}]}]}}" ;
//
//        //"{\"messaging_product\":\"whatsapp\",\"recipient_type\":\"individual\",\"to\":\"919015346166\",\"type\":\"template\",\"template\":{\"name\":\"dhruv\",\"language\":{\"code\":\"en\"},\"components\":[{\"type\":\"header\",\"parameters\":[{\"type\":\"image\",\"image\":{\"link\":\"https://images.spoonacular.com/file/wximages/423186-636x393.png\"}}]},{\"type\":\"body\",\"parameters\":[{\"type\":\"text\",\"text\":\"Hello Welcometotheworldofburgers.\"},{\"type\":\"currency\",\"currency\":{\"fallback_value\":\"$3.5\",\"code\":\"USD\",\"amount_1000\":3500}},{\"type\":\"date_time\",\"date_time\":{\"fallback_value\":\"July 20,2024\"}}]},{\"type\":\"button\",\"sub_type\":\"quick_reply\",\"index\":\"0\",\"parameters\":[{\"type\":\"payload\",\"payload\":\"A\"}]},{\"type\":\"button\",\"sub_type\":\"quick_reply\",\"index\":\"1\",\"parameters\":[{\"type\":\"payload\",\"payload\":\"B\"}]}]}}";
//
//
//        URL url = new URL(fullAPIPath);
//        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
//        conn.setRequestMethod("POST");
//        conn.setDoOutput(true);
//        conn.setRequestProperty("Authorization", "Bearer EAAP6A94pp78BO12m5xZAkYaHxGyBYfpYuJbxsYryFV5m2QeVlq9RzTkqjri2GMCNxyt3sbhT2PWCrVZBvZAZBjdlj0Kg9QFBBUigyjVl5Lss6vVvuD0nPHZA4ZCiAWUUNW5RqOadOM0e3gjc15vSCHjafG5aGbJNStHuLzcemXxx9uPm1fNCBNWuUNrdcl9KdDBifObaQtGZCTqw8ry6IMZD");
//        conn.setRequestProperty("Content-Type", "application/json");
//
//        try (DataOutputStream dos = new DataOutputStream(conn.getOutputStream())) {
//            dos.writeBytes(body2);
//        }
//
//        try (BufferedReader bf = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
//            String line;
//            while ((line = bf.readLine()) != null) {
//                System.out.println(line);
//            }
//        }
//
//
//    }
