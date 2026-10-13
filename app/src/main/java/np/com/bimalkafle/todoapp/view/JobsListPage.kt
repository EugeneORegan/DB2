package np.com.bimalkafle.todoapp.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import np.com.bimalkafle.todoapp.Job
import np.com.bimalkafle.todoapp.R
import java.text.SimpleDateFormat
import java.util.Locale


@Composable
fun JobsListPage(viewModel: JobsViewModel) {

    val jobsList by viewModel.jobList.observeAsState()
    var inputText by remember {
        mutableStateOf("")
    }
    var jobID by remember {
        mutableStateOf("")
    }
    val searchId = jobID.toIntOrNull()
    val jobSearched by viewModel.searchJob(searchId ?: 0).observeAsState()

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(8.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = inputText,
                onValueChange = {
                    inputText = it
                },
                label = { Text("Title") }
            )
            Button(onClick = {
                if (inputText.isNotBlank()) {
                    viewModel.addJob(inputText)
                    inputText = ""
                }
            }) {
                Text(text = "Add Record")
            }
        }

        jobsList?.let {
            LazyColumn(
                content = {
                    itemsIndexed(it) { _, item: Job ->
                        JobItem(item = item, onDelete = {
                            viewModel.deleteJob(item.id)
                        })
                    }
                }
            )
        } ?: Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "No items yet",
            fontSize = 16.sp
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                value = jobID,
                onValueChange = {
                    jobID = it
                },
                label = { Text("Job ID") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
          /*  Button(onClick = {
                jobID.toIntOrNull()?.let { id ->
                 val jobSearched = viewModel.searchJob(id)

                }

            }) {
                Text(text = "Search")
            }*/
        }
       if (jobSearched != null) {
            Row {
                JobItem(item = jobSearched, onDelete = {
                    jobSearched?.id?.let { id -> viewModel.deleteJob(id) }
                })
            }
        }else {
            Row{
                Text("No record found")
            }
        }
    }
}




@Composable
fun JobItem(item: Job?, onDelete: () -> Unit) {
    if (item == null) return
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = item.createdAt.let { SimpleDateFormat("HH:mm:aa, dd/MM", Locale.ENGLISH).format(it) },
                fontSize = 12.sp,
                color = Color.LightGray
            )
            Text(
                text = item.title,
                fontSize = 20.sp,
                color = Color.White
            )
        }
        IconButton(onClick = onDelete) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_delete_24),
                contentDescription = "Delete",
                tint = Color.White
            )
        }
    }
}




















