package com.app.smartganado.smart_ganado.view.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.dao.CattleDAO;
import com.app.smartganado.smart_ganado.model.dao.EstateDAO;
import com.app.smartganado.smart_ganado.model.dao.HistoryBookDAO;
import com.app.smartganado.smart_ganado.model.vo.CattleHistoryBook;
import com.app.smartganado.smart_ganado.model.vo.HistoryType;
import com.app.smartganado.smart_ganado.model.vo.UserApp;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<CattleHistoryBook> historyBook;
    private Activity activity;
    private UserApp user;

    public RecyclerAdapter(Activity activity, List<CattleHistoryBook> historyBook, UserApp user) {
        this.historyBook = historyBook;
        this.activity = activity;
        this.user = user;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        //inflate your layout and pass it to view holder
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder viewHolder, int position) {

        //setting data to view holder elements
        viewHolder.tvName.setText(getHistoryName(historyBook.get(position).getIdHistoryType()));
        viewHolder.tvEstate.setText(getEstateName(historyBook.get(position).getIdCattle()));
        viewHolder.tvCode.setText(getCodeCattle(historyBook.get(position).getIdCattle()));
        viewHolder.tvDate.setText(historyBook.get(position).getDate().toString());


        //set on click listener for each element
        viewHolder.container.setOnClickListener(onClickListener(position));
    }

    private void setDataToView(TextView details,  int position) {
        details.setText(historyBook.get(position).getDetails());
    }


    public String getHistoryName(int idHistoryType){
        for(int i = 0; i< HistoryBookDAO.getHistoryType().size(); i++){
            if(HistoryBookDAO.getHistoryType().get(i).getId()==idHistoryType){
                return HistoryBookDAO.getHistoryType().get(i).getName();
            }
        }
        return "";
    }

    public String getEstateName(int idCattle){
        int idEstate= -1;
        CattleDAO.getCattles(user.getPhone(), new CattleAdapter());
        EstateDAO.getEstates(user.getPhone(),null);

        for(int i = 0; i< CattleDAO.getCattleList().size(); i++){
            if(CattleDAO.getCattleList().get(i).getId()==idCattle){
                idEstate =  CattleDAO.getCattleList().get(i).getIdEstate();
            }
        }

        for(int i = 0; i< EstateDAO.getEstateList().size(); i++){
            if(EstateDAO.getEstateList().get(i).getId()==idEstate){
                return EstateDAO.getEstateList().get(i).getName();
            }
        }

        return "";
    }
    public String getCodeCattle(int idCattle){
        CattleDAO.getCattles(user.getPhone(), new CattleAdapter());

        for(int i = 0; i< CattleDAO.getCattleList().size(); i++){
            if(CattleDAO.getCattleList().get(i).getId()==idCattle){
               return   String.valueOf(CattleDAO.getCattleList().get(i).getCode());
            }
        }
        return "";
    }

    @Override
    public int getItemCount() {
        return (null != historyBook ? historyBook.size() : 0);
    }

    private View.OnClickListener onClickListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(activity);
                dialog.setContentView(R.layout.item_recycler);
                dialog.setTitle("Position " + position);
                dialog.setCancelable(true); // dismiss when touching outside Dialog

                // set the custom dialog components - texts and image
               // TextView details = (TextView) dialog.findViewById(R.id.tvDetails);


               // setDataToView(details, position);

                dialog.show();
            }
        };
    }

    /**
     * View holder to display each RecylerView item
     */
    protected class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCode, tvEstate, tvName, tvDate;
        private View container;

        public ViewHolder(View view) {
            super(view);
            tvCode = (TextView) view.findViewById(R.id.name);
            tvEstate = (TextView) view.findViewById(R.id.job);
            tvName = (TextView) view.findViewById(R.id.name);
            tvDate = (TextView) view.findViewById(R.id.job);
            container = view.findViewById(R.id.card_view);
        }
    }
}