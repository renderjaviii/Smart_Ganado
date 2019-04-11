package com.app.smartganado.smart_ganado.model.dao;

import android.util.Log;
import android.widget.ArrayAdapter;

import com.app.smartganado.smart_ganado.model.vo.Purpose;

import java.util.ArrayList;
import java.util.List;

public class PurposeDAO {

    private static List<Purpose> purposeList;

    static {
        purposeList = new ArrayList<>();
    }

    public static List<Purpose> getPurposeList() {
        return purposeList;
    }

    public static List<Purpose> getPurposeList(ArrayAdapter<Purpose> arrayAdapter) {
        Log.i("server", "purposeList isEmpty? " + purposeList.isEmpty());

        if (purposeList.isEmpty()) {
            purposeList.add(new Purpose(1, "Leche"));
            purposeList.add(new Purpose(2, "Carne"));
            purposeList.add(new Purpose(3, "Doble proposito"));
            arrayAdapter.notifyDataSetChanged();
        }
        return purposeList;
    }
}
