package com.EHRC.EHRC.WhatsappMessageResponseEntities;

import lombok.*;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class WebHookChanges {
    private String field;
//    private WebHookValue value;
    private WebHookValue value;
}
