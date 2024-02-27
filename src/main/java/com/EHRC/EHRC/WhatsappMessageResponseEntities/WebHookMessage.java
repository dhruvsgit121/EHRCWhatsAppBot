package com.EHRC.EHRC.WhatsappMessageResponseEntities;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class WebHookMessage {

    private String from;
    private String id;
    private String timestamp;
    private String type;
    private WebHookText text;

    private WHResponseInteractiveWrapper interactive;


}
