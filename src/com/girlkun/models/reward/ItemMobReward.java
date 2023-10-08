package com.girlkun.models.reward;

import com.girlkun.models.Template;
import com.girlkun.models.item.Item;
import com.girlkun.models.map.ItemMap;
import com.girlkun.models.map.Zone;
import com.girlkun.models.player.Player;
import com.girlkun.server.Manager;
import com.girlkun.utils.Util;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ItemMobReward {

    private Template.ItemTemplate temp;
    private int[] mapDrop;
    private int[] quantity;
    private int[] ratio;
    private int gender;

    private List<ItemOptionMobReward> option;

    public ItemMobReward(int tempId, int[] mapDrop, int[] quantity, int[] ratio, int gender) {
        this.temp = Manager.ITEM_TEMPLATES.get(tempId);
        this.mapDrop = mapDrop;
        this.quantity = quantity;
        if (this.quantity[0] < 0) {
            this.quantity[0] = -this.quantity[0];
        } else if (this.quantity[0] == 0) {
            this.quantity[0] = 1;
        }
        if (this.quantity[1] < 0) {
            this.quantity[1] = -this.quantity[1];
        } else if (this.quantity[1] == 0) {
            this.quantity[1] = 1;
        }
        if (this.quantity[0] > this.quantity[1]) {
            int tempSwap = this.quantity[0];
            this.quantity[0] = this.quantity[1];
            this.quantity[1] = tempSwap;
        }
        this.ratio = ratio;
        this.gender = gender;
        this.option = new ArrayList<>();
    }

    public ItemMap getItemMap(Zone zone, Player player, int x, int y) {
        for (int mapId : this.mapDrop) {
            if (mapId != -1 && mapId != zone.map.mapId) {
                continue;
            }
            if (this.gender != -1 && this.gender != player.gender) {
                break;
            }
            int ratioToGetItem = Util.nextInt(100);
            if (ratioToGetItem <= 10) {
                ItemMap itemMap = new ItemMap(zone, this.temp, Util.nextInt(this.quantity[0], this.quantity[1]),
                        x, y, player.id);

                if (isIdSKHTraiDat(itemMap.itemTemplate.id) || isIdSKHNamec(itemMap.itemTemplate.id)
                        || isIdSKHXayda(itemMap.itemTemplate.id)) {

                    double ratioSKH = Util.nextInt(10000);
                    if (ratioSKH <= 1) {

                        // System.out.println("\n- START -\n");
                        // System.out.println("Id: " + itemMap.itemTemplate.id + " - Name: " +
                        // itemMap.itemTemplate.name);
                        // System.out.println("Option: ");

                        for (ItemOptionMobReward opt : this.option) {
                            // System.out.println(" - Id: " + opt.getTemp().id + " - Name: " +
                            // opt.getTemp().name);

                            if (opt.getTemp().id == 0 || opt.getTemp().id == 6
                                    || opt.getTemp().id == 7
                                    || opt.getTemp().id == 14 || opt.getTemp().id == 47
                                    || opt.getTemp().id == 127 || opt.getTemp().id == 128
                                    || opt.getTemp().id == 129 || opt.getTemp().id == 130
                                    || opt.getTemp().id == 131 || opt.getTemp().id == 132
                                    || opt.getTemp().id == 133 || opt.getTemp().id == 134
                                    || opt.getTemp().id == 135
                                    || opt.getTemp().id == 136
                                    || opt.getTemp().id == 137 || opt.getTemp().id == 138
                                    || opt.getTemp().id == 139
                                    || opt.getTemp().id == 140 || opt.getTemp().id == 141
                                    || opt.getTemp().id == 142
                                    || opt.getTemp().id == 143 || opt.getTemp().id == 144
                                    || opt.getTemp().id == 30) {

                                itemMap.options.add(
                                        new Item.ItemOption(opt.getTemp(),
                                                Util.nextInt(opt.getParam()[0], opt.getParam()[1])));

                            }

                            continue;

                        }

                        // System.out.println("\n-----\n");

                        // for (ItemOption opt : itemMap.options) {

                        // System.out
                        // .println(" - Id: " + opt.optionTemplate.id + " - Name: " +
                        // opt.optionTemplate.name);

                        // }

                        // System.out.println("\n- END -\n\n");

                        return itemMap;
                    }

                } else {

                    for (ItemOptionMobReward opt : this.option) {

                        itemMap.options.add(
                                new Item.ItemOption(opt.getTemp(),
                                        Util.nextInt(opt.getParam()[0], opt.getParam()[1])));

                    }
                }

                if (itemMap.itemTemplate.id == 14 || itemMap.itemTemplate.id == 15 ||
                        itemMap.itemTemplate.id == 16
                        || itemMap.itemTemplate.id == 17 || itemMap.itemTemplate.id == 18) {
                    return null;
                }

                if (itemMap.itemTemplate.id == 555 || itemMap.itemTemplate.id == 556 || itemMap.itemTemplate.id == 561
                        || itemMap.itemTemplate.id == 562 || itemMap.itemTemplate.id == 563) {

                    double ratioSKH = Util.nextInt(1000);
                    if (ratioSKH <= 1) {

                        for (ItemOptionMobReward opt : this.option) {
                            // System.out.println(" - Id: " + opt.getTemp().id + " - Name: " +
                            // opt.getTemp().name);

                            if (opt.getTemp().id == 107) {

                                itemMap.options.add(
                                        new Item.ItemOption(opt.getTemp(),
                                                Util.nextInt(opt.getParam()[0], opt.getParam()[1])));

                            }

                            continue;

                        }

                        return itemMap;
                    }
                }

                else {
                    return itemMap;
                }

            }
        }
        return null;
    }

    private boolean isIdSKHTraiDat(int id) {
        return id == 0 || id == 6 || id == 21
                || id == 27 || id == 12;
    }

    private boolean isIdSKHXayda(int id) {
        return id == 2 || id == 8 || id == 23
                || id == 29 || id == 12;
    }

    private boolean isIdSKHNamec(int id) {
        return id == 1 || id == 7 || id == 22
                || id == 28 || id == 12;
    }

}