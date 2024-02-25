package com.girlkun.models.player;

import com.girlkun.services.MapService;
import com.girlkun.models.map.Map;
import com.girlkun.models.map.Zone;
import com.girlkun.server.Manager;
import com.girlkun.services.Service;
import com.girlkun.utils.Util;

public class Referee1 extends Player {

    private long lastTimeChat;


    public void initReferee1() {
        init();
    }

    @Override
    public short getHead() {
        return 678;
    }

    @Override
    public short getBody() {
        return 679;
    }

    @Override
    public short getLeg() {
        return 680;
    }

    public void joinMap(Zone z, Player player) {
        MapService.gI().goToMap(player, z);
        z.load_Me_To_Another(player);
    }

    @Override
    public void update() {
        if (Util.canDoWithTime(lastTimeChat, 5000)) {
           
            Service.getInstance().chat(this, "|3|Xin Chào Các Cư Dân Đã Đến Với Ngọc Rồng online");
         Service.getInstance().chat(this, "|4|Xin Chào Các Cư Dân Đã Đến Với Ngọc Rồng online");
          Service.getInstance().chat(this, "|6|Xin Chào Các Cư Dân Đã Đến Với Ngọc Rồng online"); 
            Service.getInstance().chat(this, "|5|Xin Chào Các Cư Dân Đã Đến Với Ngọc Rồng online");
       Service.getInstance().chat(this, "|8|Xin Chào Các Cư Dân Đã Đến Với Ngọc Rồng online"); 
       Service.getInstance().chat(this, "|9|Xin Chào Các Cư Dân Đã Đến Với Ngọc Rồng online");
            lastTimeChat = System.currentTimeMillis();
            
        }
    }

    private void init() {
        int id = -1000000;
        for (Map m : Manager.MAPS) {
            if (m.mapId == 99) {
                for (Zone z : m.zones) {
                    Referee1 pl = new Referee1();
                    pl.name = "nàng tiên cá";
                    pl.gender = 0;
                    pl.id = id++;
                    pl.nPoint.hpMax = 69;
                    pl.nPoint.hpg = 69;
                    pl.nPoint.hp = 69;
                    pl.nPoint.setFullHpMp();
                    pl.location.x = 714;
                    pl.location.y = 432;
                    joinMap(z, pl);
                    z.setReferee(pl);
                }
            } else if (m.mapId == 99) {                      
                    for (Zone z : m.zones) {
                    Referee1 pl = new Referee1();
                    pl.name = "nàng tiên cá";
                    pl.gender = 0;
                    pl.id = id++;
                    pl.nPoint.hpMax = 69;
                    pl.nPoint.hpg = 69;
                    pl.nPoint.hp = 69;
                    pl.nPoint.setFullHpMp();
                    pl.location.x = 761;
                    pl.location.y = 432;
                    joinMap(z, pl);
                    z.setReferee(pl);
                 }
              } else if (m.mapId == 99) {                      
                    for (Zone z : m.zones) {
                    Referee1 pl = new Referee1();
                    pl.name = "nàng tiên cá";
                    pl.gender = 0;
                    pl.id = id++;
                    pl.nPoint.hpMax = 69;
                    pl.nPoint.hpg = 69;
                    pl.nPoint.hp = 69;
                    pl.nPoint.setFullHpMp();
                    pl.location.x = 752;
                    pl.location.y = 408;
                    joinMap(z, pl);
                    z.setReferee(pl);
                 }
            }
        }
    }
}

