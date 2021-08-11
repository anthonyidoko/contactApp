package com.example.mycontactapp.Data

import android.os.Parcel
import android.os.Parcelable

data class PersonDetail(
    var firstName :String? = null,
    var lastName :String? = null,
    var phone: String? = null,
    var mobile :String? = null,
    var email :String? = null
) :Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(phone)
        parcel.writeString(mobile)
        parcel.writeString(email)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PersonDetail> {
        override fun createFromParcel(parcel: Parcel): PersonDetail {
            return PersonDetail(parcel)
        }

        override fun newArray(size: Int): Array<PersonDetail?> {
            return arrayOfNulls(size)
        }
    }
}
