package com.example.saracopproject;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class UpdateMinumanActivity extends AppCompatActivity implements
        View.OnClickListener {
    //tambahkan 1
    private EditText edtNama, edtBahan, edtStep;
    private Button btnUpdate;
    public static final String EXTRA_NOTESDRINK = "extra_notesDrink";
    public final int ALERT_DIALOG_CLOSE = 10;
    public final int ALERT_DIALOG_DELETE = 20;
    private NotesDrink notesDrink;
    private String notesDrinkId;
    DatabaseReference nDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_minuman);
        //tambahkan 2
        nDatabase = FirebaseDatabase.getInstance().getReference();
        edtNama = findViewById(R.id.edt_edit_nama);
        edtBahan = findViewById(R.id.edt_edit_bahan);
        edtStep = findViewById(R.id.edt_edit_step);
        btnUpdate = findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(this);
        notesDrink = getIntent().getParcelableExtra(EXTRA_NOTESDRINK);
        if (notesDrink != null) {
            notesDrinkId = notesDrink.getId();
        } else {
            notesDrink = new NotesDrink();
        }
        if (notesDrink != null) {
            edtNama.setText(notesDrink.getNama());
            edtBahan.setText(notesDrink.getBahan());
            edtStep.setText(notesDrink.getStep());
        }
    }
    //tambahkan 3
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_update) {
            updateNotesDrink();
        }
    }
    private void updateNotesDrink() {
        String nama = edtNama.getText().toString().trim();
        String  bahan= edtBahan.getText().toString().trim();
        String step = edtStep.getText().toString().trim();
        boolean isEmptyFields = false;
        if (TextUtils.isEmpty(nama)) {
            isEmptyFields = true;
            edtNama.setError("Field ini tidak boleh kosong");

        }
        if (TextUtils.isEmpty(bahan)) {
            isEmptyFields = true;
            edtBahan.setError("Field ini tidak boleh kosong");
        }
        if (TextUtils.isEmpty(step)) {
            isEmptyFields = true;
            edtStep.setError("Field ini tidak boleh kosong");

        }
        if (! isEmptyFields) {
            Toast.makeText(UpdateMinumanActivity.this, "Updating Data...",
                    Toast.LENGTH_SHORT).show();
            notesDrink.setNama(nama);
            notesDrink.setBahan(bahan);
            notesDrink.setStep(step);
            DatabaseReference dbNotes =
                    nDatabase.child("notes");
            //update data
            dbNotes.child(notesDrinkId).setValue(notesDrink);
            finish();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_form, menu);
        return super.onCreateOptionsMenu(menu);
    }
    //pilih menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                showAlertDialog(ALERT_DIALOG_DELETE);
                break;
            case android.R.id.home:
                showAlertDialog(ALERT_DIALOG_CLOSE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {

        showAlertDialog(ALERT_DIALOG_CLOSE);
    }

    private void showAlertDialog(int type) {
        final boolean isDialogClose = type == ALERT_DIALOG_CLOSE;
        String dialogTitle, dialogMessage;
        if (isDialogClose) {
            dialogTitle = "Batal";
            dialogMessage = "Apakah anda ingin membatalkan perubahan pada form?";
        } else {
            dialogTitle = "Hapus Data";
            dialogMessage = "Apakah anda yakin ingin menghapus item ini?";
        }
        AlertDialog.Builder alertDialogBuilder = new
                AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(dialogTitle);
        alertDialogBuilder.setMessage(dialogMessage)
                .setCancelable(false)
                .setPositiveButton("Ya", new
                        DialogInterface.OnClickListener() {
                            @Override

                            public void onClick(DialogInterface

                                                        dialogInterface, int i) {
                                if (isDialogClose) {
                                    finish();
                                } else {
                                    //hapus data

                                    DatabaseReference dbNotesDrink =

                                            nDatabase.child("notesDrink").child(notesDrinkId);
                                    dbNotesDrink.removeValue();
                                    Toast.makeText(UpdateMinumanActivity.this,
                                            "Deleting data...",
                                            Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }
                        }).setNegativeButton("Tidak", new
                        DialogInterface.OnClickListener() {
                            @Override

                            public void onClick(DialogInterface

                                                        dialogInterface, int i) {
                                dialogInterface.cancel();

                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}