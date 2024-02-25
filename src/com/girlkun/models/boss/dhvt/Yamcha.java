package com.girlkun.models.boss.dhvt;

import com.girlkun.models.boss.BossID;
import com.girlkun.models.boss.BossesData;
import com.girlkun.models.player.Player;

public class Yamcha extends BossDHVT {

    public Yamcha(Player player) throws Exception {
        super(BossID.YAMCHA, BossesData.YAMCHA);
        this.playerAtt = player;
    }
}