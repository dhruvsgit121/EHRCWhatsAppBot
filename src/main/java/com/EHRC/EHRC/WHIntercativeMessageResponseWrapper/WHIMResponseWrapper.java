package com.EHRC.EHRC.WHIntercativeMessageResponseWrapper;


import com.EHRC.EHRC.WhatsappMessageResponseEntities.WebHookEntry;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class WHIMResponseWrapper {

    private String object;
    private WHIMResponseEntryWrapper[] entry;
}
