package com.EHRC.EHRC.WHIntercativeMessageResponseWrapper;


import com.EHRC.EHRC.WhatsappMessageResponseEntities.WebHookChanges;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class WHIMResponseEntryWrapper {

    private String id;
    private WHIMChangesWrapper[] changes;
//    private WebHookChanges[] changes;

}
