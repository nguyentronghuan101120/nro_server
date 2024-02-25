
package com.arriety.MaQuaTang;

import com.girlkun.data.DataGame;
import com.girlkun.models.item.Item.ItemOption;
import com.girlkun.utils.Logger;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

public class MaQuaTang {
    String code;
    int countLeft;
    public HashMap<Integer, Integer> detail = new HashMap<>();
    public ArrayList<Integer> listIdPlayer = new ArrayList<>();
    public ArrayList<ItemOption> option = new ArrayList<>();
    Timestamp datecreate;
    Timestamp dateexpired;

    public boolean isUsedGiftCode(int idPlayer) {
        try {
            return listIdPlayer.contains(idPlayer);
        } catch (Exception e) {

            Logger.logException(DataGame.class, e);
            return false;
        }
    }

    public void addPlayerUsed(int idPlayer) {
        try {
            listIdPlayer.add(idPlayer);
        } catch (Exception e) {
            Logger.logException(DataGame.class, e);
        }
    }

    public boolean timeCode() {
        try {
            return this.datecreate.getTime() > this.dateexpired.getTime() ? true : false;
        } catch (Exception e) {
            Logger.logException(DataGame.class, e);
            return false;
        }
    }
}
