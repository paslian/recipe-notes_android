package com.example.saracopproject;

import android.os.Parcel;
import android.os.Parcelable;

public class NotesDrink implements Parcelable {
    private String id;
    private String foto;
    private String nama;
    private String bahan;
    private String step;
    public NotesDrink() {
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNama() {
        return nama;

    }
    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getBahan() {
        return bahan;
    }

    public void setBahan(String bahan) {
        this.bahan = bahan;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }


    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.foto);
        dest.writeString(this.nama);
        dest.writeString(this.bahan);
        dest.writeString(this.step);
    }
    protected NotesDrink(Parcel in) {
        this.id = in.readString();
        this.foto = in.readString();
        this.nama = in.readString();
        this.bahan = in.readString();
        this.step = in.readString();
    }
    public static final Creator<NotesDrink> CREATOR
            = new Creator<NotesDrink>() {
        @Override
        public NotesDrink createFromParcel(Parcel source) {
            return new NotesDrink(source);
        }
        @Override
        public NotesDrink[] newArray(int size) {
            return new NotesDrink[size];
        }

    };
}

