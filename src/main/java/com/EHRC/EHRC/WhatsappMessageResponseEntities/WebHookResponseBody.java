package com.EHRC.EHRC.WhatsappMessageResponseEntities;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class WebHookResponseBody {
    private String object;
    private WebHookEntry[] entry;
}
