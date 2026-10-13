package np.com.bimalkafle.todoapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Job(
    @PrimaryKey(autoGenerate = true)
    var id: Int =0,
    var title : String,
    var worker: String,
    var createdAt : Date
)



