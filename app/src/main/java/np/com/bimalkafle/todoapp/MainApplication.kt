package np.com.bimalkafle.todoapp

import android.app.Application
import androidx.room.Room.databaseBuilder
import np.com.bimalkafle.todoapp.db.JobsDatabase


class MainApplication : Application() {

    companion object {
        lateinit var jobsDatabase: JobsDatabase // Creating our database object
    }

    override fun onCreate() {
        super.onCreate()
        jobsDatabase = databaseBuilder(
            applicationContext,
            JobsDatabase::class.java,
            JobsDatabase.NAME // NAME is defined in the Database class JobsDatabase.kt
        ).build()
    }

}