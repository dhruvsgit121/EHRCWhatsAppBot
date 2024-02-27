package com.EHRC.EHRC.WebHookResponseWrapper.WHTypeWrapper;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class WHEntryWrapper {
    private String id;
    private WHResponseChangesWrapper[] changes;
}
