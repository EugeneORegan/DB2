package np.com.bimalkafle.todoapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import np.com.bimalkafle.todoapp.Job

/*
DAO Class is to form a bridge between the DataBase and the object
classes used in the app.
 */
@Dao
interface JobsDao {
/*
Annotations needed to specify the SQL queries type
 */
    @Query("SELECT * FROM Job ORDER BY createdAt DESC")
    fun getAllJobs() : LiveData<List<Job>>

    @Insert
    fun addJob(todo : Job)

    @Query("Delete FROM Job where id = :id")
    fun deleteJob(id : Int)

    @Query("SELECT * FROM Job where id = :id")
    fun searchJob(id : Int):LiveData<Job>

}

/**
 * References :
 *
 * Downloaded this repo to see examples of several Android concepts
 * https://github.com/bimalkaf/JetpackCompose_Playground/tree/main
 *
 * Specific Repo where this code is stored
 * https://github.com/bimalkaf/JetpackCompose_Playground/tree/main/4_TodoApp_RoomDB
 */