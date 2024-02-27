package com.EHRC.EHRC.Processors;


import com.EHRC.EHRC.DTU.BotMenuNames;
import com.EHRC.EHRC.WhatsAppMessagesWrapper.InteractiveMessage.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.*;

import java.util.List;

@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
@ToString
public class WhatsAppMessageBodyProcessor {
    public String getWhatsAppInteractiveMessageWithChildMenuItemsJSON(String message, String recipitentNumber, List<BotMenuNames> childMenuNames) throws JsonProcessingException {

        InteractiveMessageOuterWrapper wrapper = getWhatsAppInteractiveMessageOuterWrapper(message, recipitentNumber, childMenuNames);//new InteractiveMessageOuterWrapper();

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String JSON = ow.writeValueAsString(wrapper);

        System.out.println("JSON String is = " + JSON);
        return JSON;
    }


    public InteractiveMessageOuterWrapper getWhatsAppInteractiveMessageOuterWrapper(String message, String recipitentNumber, List<BotMenuNames> childMenuNames) {

        InteractiveMessageOuterWrapper wrapper = new InteractiveMessageOuterWrapper();

        wrapper.setMessaging_product("whatsapp");
        wrapper.setType("interactive");
        wrapper.setRecipient_type("individual");
        wrapper.setTo(recipitentNumber);

        InteractiveMessageInnerWrapper innerWrapper = getWhatsAppInteractiveMessageInnerWrapper(message, recipitentNumber, childMenuNames);


        wrapper.setInteractive(innerWrapper);
        return wrapper;
    }


    public InteractiveMessageInnerWrapper getWhatsAppInteractiveMessageInnerWrapper(String message, String recipitentNumber, List<BotMenuNames> childMenuNames) {
        InteractiveMessageInnerWrapper innerWrapper = new InteractiveMessageInnerWrapper();
        innerWrapper.setType("list");
        innerWrapper.setHeader(getWhatsAppInteractiveMessageHeaderWrapper());
        innerWrapper.setBody(getWhatsAppInteractiveMessageBodyWrapper());
        innerWrapper.setFooter(getWhatsAppInteractiveMessageFooterWrapper());
        innerWrapper.setAction(getWhatsAppInteractiveMessageActionWrapper(childMenuNames));
        return innerWrapper;
    }

    public SectionWrapper[] getWhatsAppInteractiveMessageSectionWrapper(List<BotMenuNames> childMenuNames) {
        SectionWrapper[] sectionWrapper = new SectionWrapper[1];
        SectionWrapper section = new SectionWrapper();
        section.setRows(getMenuOptionsWrapper(childMenuNames));
        sectionWrapper[0] = section;
        return sectionWrapper;
    }

    public HeaderWrapper getWhatsAppInteractiveMessageHeaderWrapper() {
        HeaderWrapper headerWrapper = new HeaderWrapper();
        headerWrapper.setText("Please select the option!!!");
        headerWrapper.setType("text");
        return headerWrapper;
    }

    public BodyWrapper getWhatsAppInteractiveMessageFooterWrapper() {
        BodyWrapper bodyWrapper = new BodyWrapper();
        bodyWrapper.setText("This is the demo footer message.");
        return bodyWrapper;
    }

    public BodyWrapper getWhatsAppInteractiveMessageBodyWrapper() {
        BodyWrapper bodyWrapper = new BodyWrapper();
        bodyWrapper.setText("This is the body text.");
        return bodyWrapper;
    }


    public ActionWrapper getWhatsAppInteractiveMessageActionWrapper(List<BotMenuNames> childMenuNames) {
        ActionWrapper actionWrapper = new ActionWrapper();
        actionWrapper.setButton("Menu Options");
        actionWrapper.setSections(getWhatsAppInteractiveMessageSectionWrapper(childMenuNames));
        return actionWrapper;
    }


    public MenuOptionsWrapper[] getMenuOptionsWrapper(List<BotMenuNames> childMenuNames) {
        MenuOptionsWrapper[] menuWrapper = new MenuOptionsWrapper[childMenuNames.size()];
        for (int i = 0; i < childMenuNames.size(); i++) {
            menuWrapper[i] = new MenuOptionsWrapper((i + 1) + "", childMenuNames.get(i).getChildMenuName());
        }
        return menuWrapper;
    }
}

//    Child Menu names are : [BotMenuNames(parentID=1, BotMenuName=start, childId=2, childMenuName=a), BotMenuNames(parentID=1, BotMenuName=start, childId=3, childMenuName=b)]
//
//        Exception occurred : java.lang.NullPointerException: Cannot invoke
//        "com.EHRC.EHRC.Processors.WhatsAppMessageBodyProcessor." +
//        "getWhatsAppInteractiveMessageWithChildMenuItemsJSON(String, String, java.util.List)"
//        because "this.messageBodyProcessor" is null
