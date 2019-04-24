package com.app.smartganado.smart_ganado.model.dao;

import android.util.Log;
import android.widget.ArrayAdapter;

import com.app.smartganado.smart_ganado.model.vo.Gender;

import java.util.ArrayList;
import java.util.List;

public class GenderDAO {

    private static List<Gender> genderList;

    static {
        genderList = new ArrayList<>();
    }

    public static List<Gender> getGenderList() {
        return genderList;
    }

    public static List<Gender> getGenderList(ArrayAdapter<Gender> arrayAdapter) {
        Log.i("server", "genderList isEmpty? " + genderList.isEmpty());

        if (genderList.isEmpty()) {
            genderList.add(new Gender(1, "Hembra"));
            genderList.add(new Gender(2, "Macho"));
            arrayAdapter.notifyDataSetChanged();
        }
        return genderList;
    }
}
