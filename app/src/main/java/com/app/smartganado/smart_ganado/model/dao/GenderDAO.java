package com.app.smartganado.smart_ganado.model.dao;

import android.widget.ArrayAdapter;

import com.app.smartganado.smart_ganado.model.vo.Gender;

import java.util.ArrayList;
import java.util.List;

public class GenderDAO {

    private List<Gender> genderList;

    public GenderDAO() {
        genderList = new ArrayList<>();
    }

    public List<Gender> getGenderList() {
        return genderList;
    }

    public List<Gender> getGenderList(ArrayAdapter<Gender> arrayAdapter) {
        genderList.add(new Gender(1, "Hembra"));
        genderList.add(new Gender(2, "Macho"));
        arrayAdapter.notifyDataSetChanged();
        return genderList;
    }
}
