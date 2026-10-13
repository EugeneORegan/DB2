package np.com.bimalkafle.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import np.com.bimalkafle.todoapp.ui.theme.TodoAppTheme
import np.com.bimalkafle.todoapp.view.JobsListPage
import np.com.bimalkafle.todoapp.view.JobsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val jobViewModel = ViewModelProvider(this)[JobsViewModel::class.java]
        setContent {
            TodoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    JobsListPage(jobViewModel)
                }
            }
        }
    }
}

