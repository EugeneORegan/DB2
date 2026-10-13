package np.com.bimalkafle.todoapp.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import np.com.bimalkafle.todoapp.Job
import np.com.bimalkafle.todoapp.MainApplication
import java.time.Instant
import java.util.Date

class JobsViewModel : ViewModel() {

    val jobDao = MainApplication.jobsDatabase.getJobsDao()
    val jobList: LiveData<List<Job>> = jobDao.getAllJobs()

    fun addJob(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            jobDao.addJob(
                Job(
                    title = title, createdAt = Date.from(Instant.now()),
                    worker = "Bert"

                )
            )
        }
    }

    fun deleteJob(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            jobDao.deleteJob(id)
        }
    }
    fun searchJob(id: Int): LiveData<Job> {
        return jobDao.searchJob(id)
    }
}







