package com.EHRC.EHRC.WHIntercativeMessageResponseWrapper;


import com.EHRC.EHRC.WhatsappMessageResponseEntities.WebHookValue;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class WHIMChangesWrapper {

    private String field;
    private WHIMValueWrapper value;


}
