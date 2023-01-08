package com.example.saracopproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReadMinumanActivity extends AppCompatActivity implements View.OnClickListener{
    //kode yang ditambahkan 1
    private ListView listView;
    private Button btnAdd;
    private TextView detail;
    //tambahkan kode ini
    private NotesDrinkAdapter adapter;
    private ArrayList<NotesDrink> notesDrinkList;
    DatabaseReference dbNotesDrink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_minuman);
        //kode yang ditambahkan 2
        listView = findViewById(R.id.lv_list);
        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);
        dbNotesDrink = FirebaseDatabase.getInstance().getReference("notesDrink");
        notesDrinkList = new ArrayList<>();

        //see detail
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Intent intent = new Intent(ReadMinumanActivity.this, UpdateMinumanActivity.class);
                intent.putExtra(UpdateMinumanActivity.EXTRA_NOTESDRINK, notesDrinkList.get(i));
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        dbNotesDrink.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                notesDrinkList.clear();
                for (DataSnapshot notesDrinkSnapshot :
                        dataSnapshot.getChildren()) {
                    NotesDrink notesDrink =
                            notesDrinkSnapshot.getValue(NotesDrink.class);
                    notesDrinkList.add(notesDrink);
                }
                NotesDrinkAdapter adapter = new
                        NotesDrinkAdapter(ReadMinumanActivity.this);
                adapter.setNotesDrinkList(notesDrinkList);
                listView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ReadMinumanActivity.this, "Terjadi kesalahan.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    //kode yang ditambahkan 3
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_add) {
            Intent intent = new Intent(ReadMinumanActivity.this,
                    CreateMinumanActivity.class);
            startActivity(intent);
        }
    }
}