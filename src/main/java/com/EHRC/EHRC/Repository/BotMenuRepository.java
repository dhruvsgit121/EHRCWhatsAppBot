package com.EHRC.EHRC.Repository;


import com.EHRC.EHRC.DTU.BotMenuNames;
import com.EHRC.EHRC.Entity.BotItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BotMenuRepository extends JpaRepository<BotItemsEntity, Integer> {

    @Query("select new com.EHRC.EHRC.DTU.BotMenuNames(c.id, c.name, j.child_id, d.name) from BotItemsEntity c, BotChildItems j, BotItemsEntity d where c.name = ?1 and c.id = j.parent_id and j.child_id = d.id")
    public List<BotMenuNames> getBotChildMenuNames(String parentName);

}
