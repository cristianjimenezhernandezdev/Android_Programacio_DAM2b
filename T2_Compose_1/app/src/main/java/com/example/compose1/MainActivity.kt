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
import com.notkamui.keval.Keval

// Activity principal: carrega el contingut amb Jetpack Compose
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // setContent() defineix la UI amb Compose
        setContent {
            Compose1Theme {
                // Scaffold dona una estructura base (padding, topBar/bottomBar, etc.)
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Passem el padding que dóna el Scaffold perquè el contingut no quedi sota barres del sistema
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

    // Estat del TextField.
    // remember + mutableStateOf fa que:
    //  - El valor es mantingui entre recomposicions
    //  - Quan canviï, la UI es redibuixi automàticament
    var input by remember { mutableStateOf("") }

    // LazyColumn: columna "lazy" (només composa els ítems visibles). Aquí la fem servir com a contenidor vertical.
    LazyColumn(
        modifier = modifier
            .padding(24.dp)
            .fillMaxWidth()
    ) {
        item {
            Text(text = "Hello $name")

            // TextField de dalt: mostra el que vas escrivint amb els botons.
            // També permet escriure manualment amb el teclat.
            TextField(
                value = input,
                onValueChange = { newValue ->
                    // Quan l’usuari escriu, actualitzem l’estat
                    input = newValue
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            // Cada Row representa una fila de botons, com una calculadora

            Row(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // CE: esborra l’últim caràcter
                TextButton(onClick = { if (input.isNotEmpty()) input = input.dropLast(1) }) {
                    Text(text = "CE")
                }

                // C: esborra tota l’expressió
                TextButton(onClick = { input = "" }) { Text(text = "C") }

                // Aquests botons només afegeixen símbols al TextField
                TextButton(onClick = { input += "%" }) { Text(text = "%") }
                TextButton(onClick = { input += "÷" }) { Text(text = "÷") }
            }

            Row(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = { input += "7" }) { Text(text = "7") }
                TextButton(onClick = { input += "8" }) { Text(text = "8") }
                TextButton(onClick = { input += "9" }) { Text(text = "9") }
                TextButton(onClick = { input += "×" }) { Text(text = "×") }
            }

            Row(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = { input += "4" }) { Text(text = "4") }
                TextButton(onClick = { input += "5" }) { Text(text = "5") }
                TextButton(onClick = { input += "6" }) { Text(text = "6") }
                TextButton(onClick = { input += "-" }) { Text(text = "-") }
            }

            Row(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = { input += "1" }) { Text(text = "1") }
                TextButton(onClick = { input += "2" }) { Text(text = "2") }
                TextButton(onClick = { input += "3" }) { Text(text = "3") }
                TextButton(onClick = { input += "+" }) { Text(text = "+") }
            }

            Row(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = { input += "0" }) { Text(text = "0") }
                TextButton(onClick = { input += "." }) { Text(text = ".") }

                // '=': avalua l’expressió i posa el resultat dins del mateix TextField.
                // Keval entén '*' i '/', però nosaltres mostrem '×' i '÷' per estètica.
                // Per això fem replace abans d’avaluar.
                TextButton(onClick = {
                    val expression = input
                        .replace("×", "*")
                        .replace("÷", "/")

                    // Avalua l’expressió (ex: "2+3*4")
                    val evalResult = Keval.eval(expression)

                    // Si és un número sencer, el mostrem sense decimals (ex: 5 en lloc de 5.0)
                    input = if (evalResult == evalResult.toLong().toDouble()) {
                        evalResult.toLong().toString()
                    } else {
                        evalResult.toString()
                    }
                }) { Text(text = "=") }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Compose1Theme {
        Greeting("Android")
    }
}