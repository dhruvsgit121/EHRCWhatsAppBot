package com.EHRC.EHRC.WhatsAppMessagesWrapper.InteractiveMessage;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class InteractiveMessageInnerWrapper {

    private String type;
    private HeaderWrapper header;
    private BodyWrapper body;
    private BodyWrapper footer;

    private ActionWrapper action;

}
