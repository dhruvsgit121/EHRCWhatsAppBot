package com.EHRC.EHRC.RestControllers;

import com.EHRC.EHRC.CustomExceptions.PhoneNumberNotValidException;
import com.EHRC.EHRC.CustomResponseEntity.PhoneNumberNotValidErrorResponse;
import com.EHRC.EHRC.Utilities.Utilities;
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


@RestController
@RequestMapping("/api")
public class WhatsAppBotController {
    @Autowired
    private Environment env;


    @GetMapping("/webhook")
    public ResponseEntity<String> webhookVerify(@RequestParam("hub.mode") String mode,
                                                @RequestParam("hub.challenge") String challenge,
                                                @RequestParam("hub.verify_token") String token) {
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
    public void getRequest(@RequestBody String json){
        System.out.println(json);
    }


//
//    @GetMapping("/webhook")
//    public void getWebHookCallBack() {
//        System.out.println();
//
//    }
//
//
//
//    @PostMapping("/webhook")
//    public void processWebHookCallBack() {
//        System.out.println();
//
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

        String body2 = "{\"messaging_product\":\"whatsapp\",\"recipient_type\":\"individual\"," +
                "\"to\":" + "\"" + "91" +
                whatsAppNumber + "\"" +
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
                "\"parameters\":[{\"type\":\"payload\",\"payload\":\"B\"}]}]}}";


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


//    @ExceptionHandler
//    public ResponseEntity<PhoneNumberNotValidErrorResponse> handlePhoneNumberNotValidException(PhoneNumberNotValidException exception) {
//
//        PhoneNumberNotValidErrorResponse errorResponse = new PhoneNumberNotValidErrorResponse();
//        errorResponse.setMessage(exception.getMessage());
//        errorResponse.setTimeStamp(System.currentTimeMillis());
//        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
//
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//    }


//    @ExceptionHandler
//    public ResponseEntity<PhoneNumberNotValidErrorResponse> handleWebserviceResponseException(IOException exception) {
//
//        System.out.println("ResponseEntity<PhoneNumberNotValidErrorResponse> handleWebserviceResponseException called");
//        System.out.println("exceptions is : " + exception);
//        PhoneNumberNotValidErrorResponse errorResponse = new PhoneNumberNotValidErrorResponse();
//        errorResponse.setMessage(exception.getMessage());
//        errorResponse.setTimeStamp(System.currentTimeMillis());
//        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
//
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//    }
}
