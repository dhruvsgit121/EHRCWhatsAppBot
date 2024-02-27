package com.EHRC.EHRC.WhatsappMessageResponseEntities;


import lombok.*;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class WebHookValue {
    private String messaging_product;
    private Map<String, Object> metadata;
    private Object[] contacts;

    private WebHookMessage[] messages;
}
