package com.girlkun.models.npc;

import com.girlkun.models.player.Player;

/**
 *
 *@Stole 
 *
 */
public interface IAtionNpc {
    
    void openBaseMenu(Player player);

    void confirmMenu(Player player, int select);


}
