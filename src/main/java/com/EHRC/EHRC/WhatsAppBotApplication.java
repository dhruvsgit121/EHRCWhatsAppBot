package com.EHRC.EHRC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WhatsAppBotApplication {

	public static void main(String[] args) {

		String data = "\"{\"messaging_product\":\"whatsapp\"," +
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

		SpringApplication.run(WhatsAppBotApplication.class, args);
	}

}
