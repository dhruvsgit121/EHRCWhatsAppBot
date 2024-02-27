package com.EHRC.EHRC.WhatsAppMessagesWrapper.InteractiveMessage;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class InteractiveMessageOuterWrapper {

    private String messaging_product;
    private String recipient_type;
    private String to;
    private String type;
    private InteractiveMessageInnerWrapper interactive;

}
