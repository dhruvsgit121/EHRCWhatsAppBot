package com.EHRC.EHRC.WhatsappMessageResponseEntities;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

public class WHResponseInteractiveWrapper {

    private String type;
    private WHListReplyWrapper list_reply;

}
