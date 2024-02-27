package com.EHRC.EHRC.WhatsappMessageResponseEntities;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

//    @JsonIgnore
    private WebHookText text;

//    @JsonIgnore
//    private WHResponseInteractiveWrapper interactive;

}
