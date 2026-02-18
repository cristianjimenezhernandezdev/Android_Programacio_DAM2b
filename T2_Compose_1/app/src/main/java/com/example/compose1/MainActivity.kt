package com.example.compose1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose1.ui.theme.Compose1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Compose1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {



    var input by remember { mutableStateOf("") }

    LazyColumn (modifier = modifier
        .padding(24.dp)
        .fillMaxWidth()

    ){
        item{

            Text(text = "Hello $name")

            TextField(
                value = input,
                onValueChange = { newValue ->
                    input = newValue
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true)
            Spacer(modifier = Modifier.height(8.dp))
            // Nota: Spacer con weight no es fiable dentro de LazyColumn; lo elimino
            }


        item{

    Row (modifier = Modifier
        .padding(24.dp)
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically

    ) {
        TextButton(onClick = { input += "CE" }) { Text(text = "CE") }
        TextButton(onClick = { input += "C" }) { Text(text = "C") }
        TextButton(onClick = { input += "%" }) { Text(text = "%") }

    }
    Row (modifier = Modifier
        .padding(24.dp)
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically

    ) {

        TextButton(onClick = { input += "7" }) { Text(text = "7") }
        TextButton(onClick = { input += "8" }) { Text(text = "8") }
        TextButton(onClick = { input += "9" }) { Text(text = "9") }

    }
        Row (modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically

        ) {
            TextButton(onClick = { input += "4" }) { Text(text = "4") }
            TextButton(onClick = { input += "5" }) { Text(text = "5") }
            TextButton(onClick = { input += "6" }) { Text(text = "6") }
        }
        Row (modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically

        ) {
            TextButton(onClick = { input += "1" }) { Text(text = "1") }
            TextButton(onClick = { input += "2" }) { Text(text = "2") }
            TextButton(onClick = { input += "3" }) { Text(text = "3") }
        }}}
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Compose1Theme {
        Greeting("Android")
    }
}