package com.EHRC.EHRC.WhatsAppMessagesWrapper.InteractiveMessage;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ActionWrapper {

    private String button;
    private SectionWrapper[] sections;

}
