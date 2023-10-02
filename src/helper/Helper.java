/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helper;

import com.girlkun.jdbc.daos.PlayerDAO;
import com.girlkun.server.Client;
import com.girlkun.utils.Logger;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Helper {
    private static Helper instance = null;

    // Static method
    // Static method to create instance of Singleton class
    public static synchronized Helper getInstance() {
        if (instance == null) {
            instance = new Helper();
        }
        return instance;
    }

    public void autoSave() {
        Logger.warning("\n\n ---------- Auto save start ---------- \n\n");
  
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Client.gI().getPlayers().forEach(player -> {
                    PlayerDAO.updatePlayer(player);
                });
            }
        }, 0, 10000);
    }
}
