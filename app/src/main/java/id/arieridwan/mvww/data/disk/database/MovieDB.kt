package id.arieridwan.mvww.data.disk.database

import androidx.room.Database
import androidx.room.RoomDatabase
import id.arieridwan.mvww.data.disk.dao.MovieDao
import id.arieridwan.mvww.data.disk.entity.Movie

/**
 * Created by arieridwan on 27/12/18.
 */

@Database(entities = [Movie::class], version = 1)
abstract class MovieDB: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}