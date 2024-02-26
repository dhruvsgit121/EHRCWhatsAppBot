package com.EHRC.EHRC.DTU;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BotMenuNames {
    private int parentID;
    private String BotMenuName;
    private int childId;
    private String childMenuName;
}
