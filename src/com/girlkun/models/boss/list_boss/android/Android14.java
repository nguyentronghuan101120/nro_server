package com.girlkun.models.boss.list_boss.android;

import com.girlkun.consts.ConstPlayer;
import com.girlkun.models.boss.Boss;
import com.girlkun.models.boss.BossID;
import com.girlkun.models.boss.BossStatus;
import com.girlkun.models.boss.BossesData;
import com.girlkun.models.map.ItemMap;
import com.girlkun.models.player.Player;
import com.girlkun.services.PlayerService;
import com.girlkun.services.Service;
import com.girlkun.services.TaskService;
import com.girlkun.utils.Util;

public class Android14 extends Boss {

    public boolean callApk13;

    public Android14() throws Exception {
        super(BossID.ANDROID_14, BossesData.ANDROID_14);
    }

    @Override
    public void reward(Player plKill) {
        try {
            int[] itemRan = new int[] { 1142, 382, 383, 384, 1142 };
            int itemId = itemRan[2];
            if (Util.isTrue(15, 100)) {
                ItemMap it = new ItemMap(this.zone, itemId, 17, this.location.x,
                        this.zone.map.yPhysicInTop(this.location.x,
                                this.location.y - 24),
                        plKill.id);
                Service.gI().dropItemMap(this.zone, it);
            }
            TaskService.gI().checkDoneTaskKillBoss(plKill, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void resetBase() {
        super.resetBase();
        this.callApk13 = false;
    }

    @Override
    public void active() {
        if (this.typePk == ConstPlayer.NON_PK && !this.callApk13) {
            this.changeToTypePK();
        }
        this.attack();
    }

    @Override
    public int injured(Player plAtt, int damage, boolean piercing, boolean isMobAttack) {
        if (!this.callApk13 && damage >= this.nPoint.hp) {
            this.callApk13();
            return 0;
        }
        return super.injured(plAtt, damage, piercing, isMobAttack);
    }

    public void callApk13() {
        try {
            if (this.bossAppearTogether == null || this.bossAppearTogether[this.currentLevel] == null) {
                return;
            }
            for (Boss boss : this.bossAppearTogether[this.currentLevel]) {
                if (boss.id == BossID.ANDROID_13) {
                    boss.changeStatus(BossStatus.RESPAWN);
                } else if (boss.id == BossID.ANDROID_15) {
                    boss.changeToTypeNonPK();
                    ((Android15) boss).callApk13 = true;
                    ((Android15) boss).recoverHP();
                }
            }
            this.changeToTypeNonPK();
            this.recoverHP();
            this.callApk13 = true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void recoverHP() {
        PlayerService.gI().hoiPhuc(this, this.nPoint.hpMax, 0);
    }

    @Override
    public void doneChatS() {
        if (this.bossAppearTogether == null || this.bossAppearTogether[this.currentLevel] == null) {
            return;
        }
        for (Boss boss : this.bossAppearTogether[this.currentLevel]) {
            if (boss.id == BossID.ANDROID_15) {
                boss.changeToTypePK();
                break;
            }
        }
    }

}
