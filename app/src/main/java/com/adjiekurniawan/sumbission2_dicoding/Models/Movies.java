package com.adjiekurniawan.sumbission2_dicoding.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Movies implements Parcelable {

    private String posterName;
    private String posterDescription;
    private String posterRating;
    private int posterCover;
    private String posterDuration;
    private String posterCategory;
    private String posterReleaseDate;
    private String posterReleaseDateLong;
    private String posterDirector;
    private String category;

    public String getPosterName() {
        return posterName;
    }

    public void setPosterName(String posterName) {
        this.posterName = posterName;
    }

    public String getPosterDescription() {
        return posterDescription;
    }

    public void setPosterDescription(String posterDescription) {
        this.posterDescription = posterDescription;
    }

    public String getPosterRating() {
        return posterRating;
    }

    public void setPosterRating(String posterRating) {
        this.posterRating = posterRating;
    }

    public int getPosterCover() {
        return posterCover;
    }

    public void setPosterCover(int posterCover) {
        this.posterCover = posterCover;
    }

    public String getPosterDuration() {
        return posterDuration;
    }

    public void setPosterDuration(String posterDuration) {
        this.posterDuration = posterDuration;
    }

    public String getPosterCategory() {
        return posterCategory;
    }

    public void setPosterCategory(String posterCategory) {
        this.posterCategory = posterCategory;
    }

    public String getPosterReleaseDate() {
        return posterReleaseDate;
    }

    public void setPosterReleaseDate(String posterReleaseDate) {
        this.posterReleaseDate = posterReleaseDate;
    }

    public String getPosterReleaseDateLong() {
        return posterReleaseDateLong;
    }

    public void setPosterReleaseDateLong(String posterReleaseDateLong) {
        this.posterReleaseDateLong = posterReleaseDateLong;
    }

    public String getPosterDirector() {
        return posterDirector;
    }

    public void setPosterDirector(String posterDirector) {
        this.posterDirector = posterDirector;
    }


    public Movies() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.posterName);
        dest.writeString(this.posterDescription);
        dest.writeString(this.posterRating);
        dest.writeInt(this.posterCover);
        dest.writeString(this.posterDuration);
        dest.writeString(this.posterCategory);
        dest.writeString(this.posterReleaseDate);
        dest.writeString(this.posterReleaseDateLong);
        dest.writeString(this.posterDirector);
        dest.writeString(this.category);
    }

    private Movies(Parcel in) {
        this.posterName = in.readString();
        this.posterDescription = in.readString();
        this.posterRating = in.readString();
        this.posterCover = in.readInt();
        this.posterDuration = in.readString();
        this.posterCategory = in.readString();
        this.posterReleaseDate = in.readString();
        this.posterReleaseDateLong = in.readString();
        this.posterDirector = in.readString();
        this.category = in.readString();
    }

    public static final Creator<Movies> CREATOR = new Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel source) {
            return new Movies(source);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };
}
