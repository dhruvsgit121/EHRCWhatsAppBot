package com.EHRC.EHRC.WebHookResponseWrapper.WHTypeWrapper;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class WHResponseValueWrapper {

    @JsonIgnore
    private Object messaging_product;
    @JsonIgnore
    private Object metadata;
    @JsonIgnore
    private Object contacts;

    private WHMessageWrapper[] messages;
}
