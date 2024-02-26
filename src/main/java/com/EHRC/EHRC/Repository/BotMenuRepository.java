package com.EHRC.EHRC.Repository;


import com.EHRC.EHRC.DTU.BotMenuNames;
import com.EHRC.EHRC.Entity.BotItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BotMenuRepository extends JpaRepository<BotItemsEntity, Integer> {

    @Query("select new com.EHRC.EHRC.DTU.BotMenuNames(c.id, c.name, j.child_id) from BotItemsEntity c, BotChildItems j where c.name = ?1 and c.id = j.parent_id")

    //@Query("select new com.EHRC.EHRC.DTU.BotMenuNames(c.id, c.name, j.name) from BotItemsEntity c inner join BotChildItems m inner join BotItemsEntity j where c.name = ?1 and c.id = m.parentid and m.childid = j.id")
    public List<BotMenuNames> getBotChildMenuNames(String parentName);

}
