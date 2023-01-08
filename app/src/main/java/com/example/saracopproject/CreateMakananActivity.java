package com.example.saracopproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CreateMakananActivity extends AppCompatActivity implements
    View.OnClickListener {
    //kode yang ditambahkan 1
    private EditText edtNama, edtBahan, edtStep;
    private Button btnSave;
    private Notes notes;
    DatabaseReference nDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_makanan);
        //kode yang ditambahkan 2
        nDatabase = FirebaseDatabase.getInstance().getReference();
        edtNama = findViewById(R.id.edt_edit_nama);
        edtBahan = findViewById(R.id.edt_edit_bahan);
        edtStep = findViewById(R.id.edt_edit_step);
        btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(this);
        notes = new Notes();

    }


    //kode yang ditambahkan 3
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_save) {
            saveNotes();
        }
    }
    private void saveNotes()
    {
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
            Toast.makeText(CreateMakananActivity.this, "Saving Data...",
                    Toast.LENGTH_SHORT).show();
            DatabaseReference dbNotes =
                    nDatabase.child("notes");
            String id = dbNotes.push().getKey();
            notes.setId(id);
            notes.setNama(nama);
            notes.setBahan(bahan);
            notes.setStep(step);
            //insert data
            dbNotes.child(id).setValue(notes);


            finish();
        }
    }

//    private void upload (){
//        gambar.setDrawingCacheEnabled(true);
//        gambar.buildDrawingCache();
//        Bitmap bitmap = ((BitmapDrawable) gambar.getDrawable()).getBitmap();
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//        byte[] data = baos.toByteArray();
//
//        //UPLOAD
//        FirebaseStorage storage = FirebaseStorage.getInstance();
//        StorageReference reference = storage.getReference("images");
//        UploadTask uploadtask = reference.putBytes(data);
//        UploadTask.addOnFailureListener(new OnFailureListener(){
//
//        });
//    }
//
//    private void selectImage(){
//        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
//        AlertDialog.Builder builder = new AlertDialog.Builder(CreateMakananActivity.this);
//        builder.setTitle(getString(R.string.app_name));
//        builder.setIcon(R.mipmap.ic_launcher);
//        builder.setItems(items, (dialog, item)->{
//            if(items[item].equals("Take Photo")){
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent, 10);
//            } else if (items[item].equals("Choose from Library")){
//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent.setType("image/*");
//                startActivityForResult(Intent.createChooser(intent, "select image"), 20);
//            } else if(items[item].equals("Cancel")){
//                dialog.dismiss();
//            }
//        });
//        builder.show();
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 20 && resultCode == RESULT_OK && data != null) {
//            final Uri path = data.getData();
//            Thread thread = new Thread(() -> {
//                try {
//                    InputStream inputStream = getContentResolver().openInputStream(path);
//                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                    gambar.post(() -> {
//                        gambar.setImageBitmap(bitmap);
//                    });
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            });
//            thread.start();
//        }
//        if (requestCode == 10 && resultCode == RESULT_OK) {
//            final Bundle extras = data.getExtras();
//            Thread thread = new Thread(() -> {
//                Bitmap bitmap = (Bitmap) extras.get("data");
//                gambar.post(() -> {
//                    gambar.setImageBitmap(bitmap);
//                });
//            });
//            thread.start();
//        }
//    }
}