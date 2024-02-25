
package com.arriety.MaQuaTang;

import com.girlkun.data.DataGame;
import com.girlkun.database.GirlkunDB;
import com.girlkun.models.item.Item.ItemOption;
import com.girlkun.models.player.Player;
import com.girlkun.services.NpcService;
import com.girlkun.services.Service;
import com.girlkun.utils.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class MaQuaTangManager {
    public String name;
    public final ArrayList<MaQuaTang> listGiftCode = new ArrayList<>();

    private static MaQuaTangManager instance;

    public static MaQuaTangManager gI() {
        if (instance == null) {
            instance = new MaQuaTangManager();
        }
        return instance;
    }

    public void init() {
        try (Connection con = GirlkunDB.getConnection();) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM giftcode");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MaQuaTang giftcode = new MaQuaTang();
                ArrayList<Integer> tempListIdPlayer = new ArrayList<>();
                String tempDBListPlayers = null;
                giftcode.code = rs.getString("code"); // + Util.nextInt(0, 0);
                giftcode.countLeft = rs.getInt("count_left");
                giftcode.datecreate = rs.getTimestamp("datecreate");
                giftcode.dateexpired = rs.getTimestamp("expired");
                String dbListIdPlayer = rs.getString("listIdPlayers");
                JSONArray jar = (JSONArray) JSONValue.parse(rs.getString("detail"));
                if (jar != null) {
                    for (int i = 0; i < jar.size(); ++i) {
                        JSONObject jsonObj = (JSONObject) jar.get(i);
                        giftcode.detail.put(Integer.parseInt(jsonObj.get("id").toString()),
                                Integer.parseInt(jsonObj.get("quantity").toString()));
                        jsonObj.clear();
                    }
                }

                JSONArray option = (JSONArray) JSONValue.parse(rs.getString("itemoption"));
                if (option != null) {
                    for (int u = 0; u < option.size(); u++) {
                        JSONObject jsonobject = (JSONObject) option.get(u);
                        giftcode.option.add(new ItemOption(Integer.parseInt(jsonobject.get("id").toString()),
                                Integer.parseInt(jsonobject.get("param").toString())));
                        jsonobject.clear();
                    }
                }
                if (!dbListIdPlayer.isEmpty()) {
                    tempDBListPlayers = dbListIdPlayer = removeCharAt(dbListIdPlayer, 0);
                    tempDBListPlayers = dbListIdPlayer = removeCharAt(dbListIdPlayer, dbListIdPlayer.length() - 1);
                    String[] resultTempDBListPlayer = tempDBListPlayers.split(",");
                    for (String item : resultTempDBListPlayer) {
                        if (!item.isEmpty()) {
                            tempListIdPlayer.add(Integer.parseInt(item));
                        }
                    }
                    giftcode.listIdPlayer = tempListIdPlayer;
                }
                listGiftCode.add(giftcode);
            }
            con.close();
        } catch (Exception erorlog) {
            Logger.logException(DataGame.class, erorlog);
        }
    }

    public void updateGiftCodeListIdPlayer(ArrayList<Integer> listIdPlayers, String code) {
        try {
            String sql = "UPDATE giftcode SET listIdPlayers = ? WHERE code = ?";
            ArrayList<Integer> deDupStringList = new ArrayList<>(new HashSet<>(listIdPlayers));
            GirlkunDB.executeUpdate(sql, JSONValue.toJSONString(deDupStringList), code);
        } catch (Exception e) {
            Logger.logException(DataGame.class, e);
        }
    }

    public void sizeList(Player pl) {
        try {
            Service.gI().sendThongBao(pl, "" + MaQuaTang.class);
        } catch (Exception e) {
            Logger.logException(DataGame.class, e);
        }
    }

    public MaQuaTang checkUseGiftCode(int idPlayer, String code) {
        try {
            for (MaQuaTang giftCode : listGiftCode) {
                if (giftCode.code.equals(code) && giftCode.countLeft > 0 && !giftCode.isUsedGiftCode(idPlayer)) {
                    giftCode.countLeft -= 1;
                    giftCode.addPlayerUsed(idPlayer);
                    updateGiftCodeListIdPlayer(giftCode.listIdPlayer, code);
                    return giftCode;
                }
            }
            return null;
        } catch (Exception e) {
            Logger.logException(DataGame.class, e);
            return null;
        }
    }

    public void checkInfomationGiftCode(Player p) {
        try {
            StringBuilder sb = new StringBuilder();
            for (MaQuaTang giftCode : listGiftCode) {
                sb.append("Code: ").append(giftCode.code).append(", Số lượng: ").append(giftCode.countLeft).append("\b")
                        .append(", Ngày tạo: ")
                        .append(giftCode.datecreate).append("Ngày hết hạn").append(giftCode.dateexpired);
            }

            NpcService.gI().createTutorial(p, 5073, sb.toString());
        } catch (Exception e) {
            Logger.logException(DataGame.class, e);
        }
    }

    public static String removeCharAt(String s, int pos) {
        return s.substring(0, pos) + s.substring(pos + 1);
    }
}
