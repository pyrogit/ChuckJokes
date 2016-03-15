package hr.pyro.chuckjokes.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Created on 07.03.16..
 */
public final class JokeViewModel implements Parcelable {

    public static final JokeViewModel EMPTY = new JokeViewModel(-1, "", false);

    private final int id;
    private final String joke;
    private boolean isFavorite;

    public JokeViewModel(final int id, @NonNull final String joke, final boolean isFavorite) {
        this.id = id;
        this.joke = joke;
        this.isFavorite = isFavorite;
    }

    public int getId() {
        return id;
    }

    public String getJoke() {
        return joke;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.joke);
        dest.writeByte(isFavorite ? (byte) 1 : (byte) 0);
    }

    protected JokeViewModel(Parcel in) {
        this.id = in.readInt();
        this.joke = in.readString();
        this.isFavorite = in.readByte() != 0;
    }

    public static final Parcelable.Creator<JokeViewModel> CREATOR = new Parcelable.Creator<JokeViewModel>() {
        public JokeViewModel createFromParcel(Parcel source) {
            return new JokeViewModel(source);
        }

        public JokeViewModel[] newArray(int size) {
            return new JokeViewModel[size];
        }
    };
}
