package com.example.uwucount2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uwucount2.ui.theme.UwUCount2Theme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.text.style.TextAlign

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var countState: Double by remember { mutableStateOf(0.0) }
            val marksList = remember { mutableStateListOf<Int>() }
            if (marksList.size == 0) {
                countState = 0.00
            } else
                countState = Math.round(marksList.average()*100).toDouble()/100
            UwUCount2Theme() {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(top = 30.dp)
                    ) {
                        if (marksList.size==0) {
                            Text(
                                text = "UwU",
                                fontSize = 50.sp,
                                modifier = Modifier
                                    .padding(vertical = 40.dp)
                            )
                        }
                        if (marksList.size>0) {
                            Text(
                                text = countState.toString(),
                                fontSize = 50.sp,
                                modifier = Modifier
                                    .padding(vertical = 40.dp)
                            )
                        }

                        Text(
                            text = "${marksList.size} marks\n",
                            fontSize = 25.sp
                        )
                        Text(
                            text = marksList.joinToString("  "),
                            fontSize = 25.sp,
                            lineHeight = 40.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(horizontal = 60.dp, vertical = 30.dp)
                                .size(
                                    width = 400.dp,
                                    height = 200.dp,
                                )
                                .verticalScroll(rememberScrollState())
                        )
                        // marks adding buttons
                        LazyRow (
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.Bottom,
                            modifier = Modifier.weight(1f, true)
                        ) {
                            items(4) { index ->
                                FB(Mark = index+2, marks=marksList)
                            }
                        }
                        // deleting buttons
                        LazyRow(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.Bottom,
                            modifier = Modifier
                                .padding(
                                    top = 15.dp,
                                    bottom = 50.dp
                                )
                        ) {
                            items(2) { index ->
                                OtherB(marks=marksList, index=index)
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun FB (
    Mark: Int,
    marks: SnapshotStateList<Int>
) {
    FilledTonalButton(
        onClick = {
            if (marks.size <= 199) {
                marks.add(Mark)
            }
        },
        modifier = Modifier
            .size(width = 90.dp, height = 60.dp)
            .padding(horizontal = 8.dp)
    ) {
        Text(text = Mark.toString(), fontSize = 20.sp)
    }
}

@Composable
fun OtherB (
    marks: SnapshotStateList<Int>,
    index: Int
) {
    FilledTonalButton(
        onClick = {
            if (index==0) {
                marks.removeAll(marks)
            }
            if (index==1 && marks.size != 0) {
                marks.removeLast()
            }
        },
        modifier = Modifier
            .size(
                width = 90.dp,
                height = 60.dp
            )
            .padding(
                horizontal = 8.dp
            )
    ) {
        if (index==0) {
            Icon(imageVector = Icons.Default.Clear, contentDescription = "delete all")
        }
        if (index==1) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "delete last")
        }
    }
}