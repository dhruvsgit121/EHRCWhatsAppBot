package com.EHRC.EHRC.WHIntercativeMessageResponseWrapper;

import com.EHRC.EHRC.WhatsappMessageResponseEntities.WebHookMessage;
import com.EHRC.EHRC.WhatsappMessageResponseEntities.WebHookValue;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Map;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class WHIMValueWrapper {

    @JsonIgnore
    private String messaging_product;
    @JsonIgnore
    private Map<String, Object> metadata;
    @JsonIgnore
    private Object[] contacts;

    private WHIMMessageWrapper[] messages;

}
