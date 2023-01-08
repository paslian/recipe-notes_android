package com.example.saracopproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
public class NotesDrinkAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<NotesDrink> notesDrinkList = new
            ArrayList<>();
    public void setNotesDrinkList(ArrayList<NotesDrink>
                                     notesDrinkList) {
        this.notesDrinkList = notesDrinkList;
    }
    public NotesDrinkAdapter(Context context) {
        this.context = context;
    }
    @Override
    public int getCount() {
        return notesDrinkList.size();
    }
    @Override
    public Object getItem(int i) {
        return notesDrinkList.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = view;
        if (itemView == null) {
            itemView = LayoutInflater.from(context)
                    .inflate(R.layout.notesdrink_list,
                            viewGroup, false);
        }
        ViewHolder viewHolder = new ViewHolder(itemView);
        NotesDrink notesDrink = (NotesDrink) getItem(i);
        viewHolder.bind(notesDrink);
        return itemView;
    }
    private class ViewHolder {
        private TextView txtFoto, txtName, Detail;
        ViewHolder(View view) {
            txtName = view.findViewById(R.id.txt_nama);
        }
        void bind(NotesDrink notesDrink) {
            txtName.setText(notesDrink.getNama());
        }
    }

}

