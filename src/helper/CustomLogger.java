package helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.girlkun.models.player.Pet;
import com.girlkun.models.player.Player;
import com.girlkun.utils.Logger;

public class CustomLogger {

    // Create a DateTimeFormatter to format the time in the desired format
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static void showUserLoginInfo(Player player) {
        loggerTitle("Account infomation");

        LocalDateTime now = LocalDateTime.now();
        // Print the current time in the desired format
        Logger.log(Logger.GREEN, "Log time: " + formatter.format(now) + "\n");

        Logger.log(Logger.GREEN, "Name: " + player.name + "\n");
        Logger.log(Logger.GREEN, "HP: " + player.nPoint.hp + "\n");
        Logger.log(Logger.GREEN, "KI: " + player.nPoint.mp + "\n");
        Logger.log(Logger.GREEN, "ATK: " + player.nPoint.dame + "\n");
        Logger.log(Logger.GREEN, "Def: " + player.nPoint.def + "\n");
        Logger.log(Logger.GREEN, "Crit: " + player.nPoint.crit + "\n");
        Logger.log(Logger.GREEN, "Task name: " + player.playerTask.taskMain.name + ", ID: "
                + player.playerTask.taskMain.id + "\n");

        loggerTitle("End");
    }

    public static void showUserLogoutInfo(Player player) {
        loggerTitle("User logout infomation");
        LocalDateTime now = LocalDateTime.now();

        Logger.log(Logger.GREEN, "User '" + player.name + "' logout since: " + formatter.format(now) + "\n");
        loggerTitle("End");

    }


    public static void showPetInfo(Player player) {
        loggerTitle("Pet information");

        LocalDateTime now = LocalDateTime.now();
        // Print the current time in the desired format
        Logger.log(Logger.GREEN, "Log time: " + formatter.format(now) + "\n");

        Logger.log(Logger.GREEN, "Name: " + player.name + "\n");
        Logger.log(Logger.GREEN, "Power: " + player.nPoint.power + "\n");
        Logger.log(Logger.GREEN, "Exp: " + player.nPoint.tiemNang + "\n");
        Logger.log(Logger.GREEN, "HP: " + player.nPoint.hp + "\n");
        Logger.log(Logger.GREEN, "KI: " + player.nPoint.mp + "\n");
        Logger.log(Logger.GREEN, "ATK: " + player.nPoint.dame + "\n");
        Logger.log(Logger.GREEN, "Def: " + player.nPoint.def + "\n");
        Logger.log(Logger.GREEN, "Crit: " + player.nPoint.crit + "\n");
        Logger.log(Logger.GREEN, "Task name: " + player.playerTask.taskMain.name + ", ID: "
                + player.playerTask.taskMain.id + "\n");

        loggerTitle("End");
    }



    private static void loggerTitle(String title) {
        Logger.log(Logger.CYAN, "\n ------------------ " + title + "\n \n");
    }
}
