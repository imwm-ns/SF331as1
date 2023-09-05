package com.example.sf333as1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sf333as1.ui.theme.SF333as1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SF333as1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GuessNumber()
                }
            }
        }
    }
}

@Composable
fun GuessNumber() {
    Column {
        Text(
            text = stringResource(R.string.header),
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Blue, )
                .padding(vertical = 15.dp, horizontal = 10.dp),
            color = Color.White
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GuessNumberPreview() {
    SF333as1Theme {
        GuessNumber()
    }
}