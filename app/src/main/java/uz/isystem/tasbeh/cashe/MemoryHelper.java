package uz.isystem.tasbeh.cashe;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

public class MemoryHelper {
    private static MemoryHelper helper;
    private final SharedPreferences preferences;

    public MemoryHelper(Context context) {
        preferences = context.getSharedPreferences("Tasbeh", Context.MODE_PRIVATE);
    }

    public static MemoryHelper getHelper() {
        return helper;
    }

    public static void init(Context context) {
        if (helper == null) {
            helper = new MemoryHelper(context);
        }
    }

    public void saveData(UserData userData) {

        preferences.edit().putString("name_" + getLastIndex(), userData.getName()).apply();
        preferences.edit().putInt("step_" + getLastIndex(), userData.getStep()).apply();
        saveLastIndex();
    }

    private void saveLastIndex() {
        preferences.edit().putInt("index", getLastIndex() + 1).apply();
    }

    private int getLastIndex() {
        return preferences.getInt("index", 0);
    }

    public UserData getData(int index) {
        UserData userData = new UserData(
                preferences.getInt("step_" + index, 0),
                preferences.getString("name_" + index, "")
        );
        return userData;
    }

    public ArrayList<UserData> getLastResult() {
        ArrayList<UserData> list = new ArrayList<>();
        int n = getLastIndex();
        for (int i = 0; i < n; i++) {
            list.add(getData(i));
        }
        return list;
    }

}
