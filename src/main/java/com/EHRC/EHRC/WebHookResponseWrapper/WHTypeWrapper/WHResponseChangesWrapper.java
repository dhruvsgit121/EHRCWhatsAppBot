package com.EHRC.EHRC.WebHookResponseWrapper.WHTypeWrapper;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class WHResponseChangesWrapper {

    @JsonIgnore
    private Object field;

    private WHResponseValueWrapper value;


}
