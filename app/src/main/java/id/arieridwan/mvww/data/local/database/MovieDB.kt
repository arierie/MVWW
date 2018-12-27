package id.arieridwan.mvww.data.local.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import id.arieridwan.mvww.data.local.dao.MovieDao
import id.arieridwan.mvww.data.local.entity.Movie

/**
 * Created by arieridwan on 27/12/18.
 */

@Database(entities = [Movie::class], version = 1)
abstract class MovieDB: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}