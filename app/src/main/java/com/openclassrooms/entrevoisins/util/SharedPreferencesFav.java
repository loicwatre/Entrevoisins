package com.openclassrooms.entrevoisins.util;

import java.util.ArrayList;
import java.util.List;

public class SharedPreferencesFav {

    public static String prerareDataForPreferences(List<Integer> idList){
        StringBuilder builder = new StringBuilder();

        for (int id : idList){
            builder.append(String.valueOf(id));
            builder.append("-");
        }

        return builder.toString();
    }

    public static ArrayList<Integer> getPreferencesToArrayList(String preferences){
        ArrayList<Integer> favorites = new ArrayList<>();

        if (preferences == null || preferences.equals("")){
            return favorites;
        }

        String[] preferencesSplit = preferences.split("-");

        for (String pref : preferencesSplit){
            favorites.add(Integer.parseInt(pref));
        }

        return favorites;
    }
}
