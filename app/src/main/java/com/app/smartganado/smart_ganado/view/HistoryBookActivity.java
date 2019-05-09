package com.app.smartganado.smart_ganado.view;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.app.smartganado.smart_ganado.model.dao.HistoryBookDAO;
import com.app.smartganado.smart_ganado.model.vo.CattleHistoryBook;
import com.app.smartganado.smart_ganado.model.vo.UserApp;
import com.app.smartganado.smart_ganado.view.adapter.RecyclerAdapter;
import com.app.smartganado.smart_ganado.R;


import java.util.ArrayList;

public class HistoryBookActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private ArrayList<CattleHistoryBook> cattleHistoryBooks;
    private FloatingActionButton fab;
    private UserApp user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_book);

        if (user == null)
            user = (UserApp) getIntent().getSerializableExtra("user");//Getting user

        cattleHistoryBooks = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyle_view);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerAdapter(this, cattleHistoryBooks, user);
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(onAddingListener());
    }

    @Override
    protected void onResume() {
        HistoryBookDAO.getHistoryBook(adapter, user.getPhone());
        super.onResume();
    }


    private View.OnClickListener onAddingListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(HistoryBookActivity.this);
                dialog.setContentView(R.layout.dialog_add); //layout for dialog
                dialog.setTitle("Add a new friend");
                dialog.setCancelable(false); //none-dismiss when touching outside Dialog

                // set the custom dialog components - texts and image
                EditText name = (EditText) dialog.findViewById(R.id.name);
                EditText job = (EditText) dialog.findViewById(R.id.job);
                Spinner spnGender = (Spinner) dialog.findViewById(R.id.gender);
                View btnAdd = dialog.findViewById(R.id.btn_ok);
                View btnCancel = dialog.findViewById(R.id.btn_cancel);

                //set spinner adapter
                ArrayList<String> gendersList = new ArrayList<>();
                gendersList.add("Male");
                gendersList.add("Female");
                ArrayAdapter<String> spnAdapter = new ArrayAdapter<String>(HistoryBookActivity.this,
                        android.R.layout.simple_dropdown_item_1line, gendersList);
                spnGender.setAdapter(spnAdapter);

             //   //set handling event for 2 buttons and spinner
               // spnGender.setOnItemSelectedListener(onItemSelectedListener());
                btnAdd.setOnClickListener(onConfirmListener(name, job, dialog));
                btnCancel.setOnClickListener(onCancelListener(dialog));

                dialog.show();
            }
        };
    }


    private View.OnClickListener onConfirmListener(final EditText name, final EditText job, final Dialog dialog) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // CattleHistoryBook friend = new CattleHistoryBook(name.getText().toString().trim(), gender, job.getText().toString().trim());

                //adding new object to arraylist
             //   cattleHistoryBooks.add(friend);

                //notify data set changed in RecyclerView adapter
                adapter.notifyDataSetChanged();

                //close dialog after all
                dialog.dismiss();
            }
        };
    }

    private View.OnClickListener onCancelListener(final Dialog dialog) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        };
    }
}