package com.EHRC.EHRC.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@IdClass(BotChildPrimaryKey.class)
@Table(name = "bot_child")
public class BotChildItems {

    @Id
    private int parent_id;

    @Id
    private int child_id;

}
