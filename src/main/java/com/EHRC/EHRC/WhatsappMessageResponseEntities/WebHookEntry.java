package com.EHRC.EHRC.WhatsappMessageResponseEntities;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class WebHookEntry {

    private String id;
    private WebHookChanges[] changes;
}
