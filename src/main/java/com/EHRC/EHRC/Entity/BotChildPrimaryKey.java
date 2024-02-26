package com.EHRC.EHRC.Entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BotChildPrimaryKey implements Serializable {

    @Id
    private int parent_id;

    @Id
    private int child_id;

}
