package com.example.saracopproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
public class NotesAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Notes> notesList = new
            ArrayList<>();
    public void setNotesList(ArrayList<Notes>
                                     notesList) {
        this.notesList = notesList;
    }
    public NotesAdapter(Context context) {
        this.context = context;
    }
    @Override
    public int getCount() {
        return notesList.size();
    }
    @Override
    public Object getItem(int i) {
        return notesList.get(i);
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
                    .inflate(R.layout.notes_list,
                            viewGroup, false);
        }
        ViewHolder viewHolder = new ViewHolder(itemView);
        Notes notes = (Notes) getItem(i);
        viewHolder.bind(notes);
        return itemView;
    }
    private class ViewHolder {
        private TextView txtFoto, txtName, Detail;
        ViewHolder(View view) {
            txtName = view.findViewById(R.id.txt_nama);
        }
        void bind(Notes notes) {
            txtName.setText(notes.getNama());
        }
    }

}

