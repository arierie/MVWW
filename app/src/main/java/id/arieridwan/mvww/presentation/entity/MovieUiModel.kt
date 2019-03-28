package id.arieridwan.mvww.presentation.entity

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by arieridwan on 20/12/18.
 */

class MovieUiModel(val title: String, val posterPath: String, val overview: String,
                   val backdropPath: String, val releaseDate: String,
                   val id: Int = 0, val video: Boolean = false, val voteAverage: Double = 0.toDouble(),
                   val popularity: Double = 0.toDouble()): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readDouble(),
        parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(posterPath)
        parcel.writeString(overview)
        parcel.writeString(backdropPath)
        parcel.writeString(releaseDate)
        parcel.writeInt(id)
        parcel.writeByte(if (video) 1 else 0)
        parcel.writeDouble(voteAverage)
        parcel.writeDouble(popularity)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieUiModel> {
        override fun createFromParcel(parcel: Parcel): MovieUiModel {
            return MovieUiModel(parcel)
        }

        override fun newArray(size: Int): Array<MovieUiModel?> {
            return arrayOfNulls(size)
        }
    }

}