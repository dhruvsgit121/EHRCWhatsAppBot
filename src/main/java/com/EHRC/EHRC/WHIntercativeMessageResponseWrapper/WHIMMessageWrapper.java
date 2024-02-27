package com.EHRC.EHRC.WHIntercativeMessageResponseWrapper;


import com.EHRC.EHRC.WhatsappMessageResponseEntities.WHResponseInteractiveWrapper;
import com.EHRC.EHRC.WhatsappMessageResponseEntities.WebHookText;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class WHIMMessageWrapper {

    @JsonIgnore
    private Object context;

    private String from;
    private String id;
    private String timestamp;
    private String type;

    //    @JsonIgnore
//    private WebHookText text;

//    @JsonIgnore
    private WHResponseInteractiveWrapper interactive;



}
