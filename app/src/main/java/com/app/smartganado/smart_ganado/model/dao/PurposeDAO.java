package com.app.smartganado.smart_ganado.model.dao;

import android.widget.ArrayAdapter;

import com.app.smartganado.smart_ganado.model.vo.Purpose;

import java.util.ArrayList;
import java.util.List;

public class PurposeDAO {

    private List<Purpose> purposeList;

    public PurposeDAO() {
        purposeList = new ArrayList<>();
    }

    public List<Purpose> getPurposeList() {
        return purposeList;
    }

    public List<Purpose> getPurposeList(ArrayAdapter<Purpose> arrayAdapter) {
        purposeList.add(new Purpose(1, "Leche"));
        purposeList.add(new Purpose(2, "Carne"));
        purposeList.add(new Purpose(3, "Doble proposito"));
        arrayAdapter.notifyDataSetChanged();
        return purposeList;
    }
}
