package com.example.sf333as1

import android.graphics.Color.parseColor
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    val answer = kotlin.random.Random.nextInt(1, 1001)
                    GuessNumber(answer = answer)
                }
            }
        }
    }
}

@Composable
fun GuessNumber(answer: Int) {
    var guess by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var count by remember { mutableStateOf(0) }
    var isWin by remember { mutableStateOf(true) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = stringResource(R.string.header),
            style = TextStyle(
                fontSize = 21.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(parseColor("#1b3f75")))
                .padding(vertical = 15.dp, horizontal = 10.dp),
            color = Color.White
        )
        Text(
            text = stringResource(R.string.main_topic),
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .padding(vertical = 20.dp)
        )
        Spacer(modifier = Modifier.height(150.dp))
        EditInputField(
            value = guess,
            onValueChange = { guess = it },
            enabled = isWin,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(120.dp))
        Text(
            text = result,
            style = TextStyle(
                fontSize = 28.sp,
                color = Color.Gray
            )
        )
        Button(
            onClick = {
                result = checkAnswer(number = guess, answer = answer)
                if (result != "You win. The answer is ${answer}") {
                    count++
                }
                else isWin = false
            },
            enabled = isWin,
            modifier = Modifier.padding(vertical = 30.dp)
        ) {
            Text(
                text = stringResource(id = R.string.label_btn),
            )
        }
        Text(
            text = "Count: ${count}",
            style = TextStyle(
                fontSize = 18.sp
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditInputField(
    value: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean,
    modifier: Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = stringResource(R.string.placeholder),
                style = TextStyle(
                    fontSize = 18.sp
                )
            )
        },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        enabled = enabled,
        // shape = CircleShape, alternative
        modifier = modifier,
    )
}

private fun checkAnswer(number: String, answer: Int): String {
    val guess = Integer.parseInt(number)
    return if (guess < answer) "Hint: It's lower."
    else if (guess in (answer + 1)..1000) "Hint: It's higher."
    else if (guess > 1000) "Your answer over a range."
    else "You win. The answer is ${answer}"
}