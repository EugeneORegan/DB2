package np.com.bimalkafle.todoapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import np.com.bimalkafle.todoapp.Job


@Database(entities = [Job::class], version = 1)
@TypeConverters(Converters::class)
abstract class JobsDatabase : RoomDatabase(){

    companion object {
        const val NAME = "Jobs_DB"
    }

    abstract fun getJobsDao() : JobsDao

}