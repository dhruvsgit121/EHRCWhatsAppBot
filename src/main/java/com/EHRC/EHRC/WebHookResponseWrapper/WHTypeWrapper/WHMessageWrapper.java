package com.EHRC.EHRC.WebHookResponseWrapper.WHTypeWrapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class WHMessageWrapper {

    private String from;
    @JsonIgnore
    private Object id;

    @JsonIgnore
    private Object timestamp;

    @JsonIgnore
    private Object text;

    private String type;

    @JsonIgnore
    private Object context;

    @JsonIgnore
    private Object interactive;

}
