package com.app.smartganado.smart_ganado.model.dao;

import android.util.Log;
import android.widget.ArrayAdapter;

import com.app.smartganado.smart_ganado.model.vo.Breed;
import com.app.smartganado.smart_ganado.model.vo.CattleHistoryBook;
import com.app.smartganado.smart_ganado.model.vo.HistoryType;
import com.app.smartganado.smart_ganado.remote.APIUtils;
import com.app.smartganado.smart_ganado.view.adapter.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryBookDAO {
    private static List<CattleHistoryBook> historyBook;
    private static List<HistoryType> historyTypes;

    static {
        historyBook = new ArrayList<>();
    }

    public static List<CattleHistoryBook> getHistoryBook() {
        return historyBook;
    }

    public static List<HistoryType> getHistoryType() {
        getHistoryTypes();
        return historyTypes;
    }

    public int getPositionID(int id) {
        Log.i("server", historyBook.size() + " " + id);

        for (int i = 0; i < historyBook.size(); i++) {
            if (historyBook.get(i).getId() == id)
                return i;
        }
        return -1;
    }

    public static void getHistoryBook(final RecyclerAdapter adapter, Long phone){
        APIUtils.getAPIService().getHistoryBook("getAll", phone).enqueue(new Callback<List<CattleHistoryBook>>() {
            @Override
            public void onResponse(Call<List<CattleHistoryBook>> call, Response<List<CattleHistoryBook>> response) {
                if(response.isSuccessful())
                    if(response!=null){

                        Log.i("server", "breedList isEmpty? " + historyBook.isEmpty());

                        if (response.body().size() != historyBook.size()) {
                            historyBook.clear();
                            historyBook.addAll(response.body());
                            adapter.notifyDataSetChanged();
                        }

                    }else call.clone().enqueue(this);
            }

            @Override
            public void onFailure(Call<List<CattleHistoryBook>> call, Throwable t) {
                Log.i("server", "error: " + t.getMessage());

            }
        });
    }

    private static void getHistoryTypes(){
        if(historyTypes==null){
        historyTypes =new ArrayList<>();
        historyTypes.add(new HistoryType(1,"Vacunación"));
        historyTypes.add(new HistoryType(2,"Destete"));
        historyTypes.add(new HistoryType(3,"Secado"));
        historyTypes.add(new HistoryType(4,"Parto"));
        historyTypes.add(new HistoryType(5,"Muerte"));
        historyTypes.add(new HistoryType(6,"Inseminación"));
        }





    }

}
