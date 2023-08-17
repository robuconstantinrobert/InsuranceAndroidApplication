package com.example.incercarelicenta6.UserInfo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class UserInfo implements Parcelable {
    private String name;
    private String adress;
    private String license;
    private int tickets;
    private int suspensionThirty;
    private int suspensionSixty;
    private int suspensionNinety;

    public UserInfo(String name, String adress, String license, int tickets,
                    int suspensionThirty, int suspensionSixty, int suspensionNinety) {
        this.name = name;
        this.adress = adress;
        this.license = license;
        this.tickets = tickets;
        this.suspensionThirty = suspensionThirty;
        this.suspensionSixty = suspensionSixty;
        this.suspensionNinety = suspensionNinety;
    }

    protected UserInfo(Parcel in) {
        name = in.readString();
        adress = in.readString();
        license = in.readString();
        tickets = in.readInt();
        suspensionThirty = in.readInt();
        suspensionSixty = in.readInt();
        suspensionNinety = in.readInt();
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel in) {
            return new UserInfo(in);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public String getLicense() {
        return license;
    }

    public int getTickets() {
        return tickets;
    }

    public int getSuspensionThirty() {
        return suspensionThirty;
    }

    public int getSuspensionSixty() {
        return suspensionSixty;
    }

    public int getSuspensionNinety() {
        return suspensionNinety;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(adress);
        dest.writeString(license);
        dest.writeInt(tickets);
        dest.writeInt(suspensionThirty);
        dest.writeInt(suspensionSixty);
        dest.writeInt(suspensionNinety);
    }
}
