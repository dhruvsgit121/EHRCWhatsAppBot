package com.EHRC.EHRC.WebHookResponseWrapper.WHTypeWrapper;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class WHResponseTypeWrapper {

    private String object;
    private WHEntryWrapper[] entry;

}
