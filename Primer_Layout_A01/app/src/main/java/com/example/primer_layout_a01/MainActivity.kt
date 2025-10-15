package com.example.primer_layout_a01

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar
import android.view.View
import android.widget.Toast



//He afegit imports perque funcioni el DatePickerDialog i el TextInputEditText. juntament
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Ajust dels insets (barra d’estat/navigation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // DatePicker ChatGPT no he sabut com fer-ho d'una altre manera. Pero bàsicament fa com els videojocs.
        //te un listener que esta sempre pendent de si es clica, llavors una funció que s'encarrega de obrir el calendari
        //Calendar.getInstance amb els valors que ens interessa de Any, mes i dia. Llavors el DatePickerDialog ens dona
        //el calendari en una finestra emergent. i li posa el que cliquem dins el EditText amb el format que li hem dit
        val etNaixement: TextInputEditText = findViewById(R.id.etNaixement)
        etNaixement.setOnClickListener {
            val cal = Calendar.getInstance()
            val any = cal.get(Calendar.YEAR)
            val mes = cal.get(Calendar.MONTH)
            val dia = cal.get(Calendar.DAY_OF_MONTH)

            val dialog = DatePickerDialog(
                this,
                { _, y, m, d ->
                    // Format
                    val text = String.format("%02d/%02d/%04d", d, m + 1, y)
                    etNaixement.setText(text)
                },
                any, mes, dia
            )
            dialog.show()
        }
    }
    //Per fer que funcionin les funcions he afegit dos imports, el de View i el de Toast i la resta de codi de les funcions amb el chat
    //el d'enviar Form com que no tenim base de dades doncs no l'enviem, i el de netejar el que fem és repetir la funcio de sistema
    //Clear per cada TextInputEdit, és a dir camps que hem creat i ens permeten escriure.
    fun enviarForm(view: View) {
        Toast.makeText(this, "Formulari enviat correctament!", Toast.LENGTH_SHORT).show()
    }

    fun netejarForm(view: View) {
        findViewById<TextInputEditText>(R.id.etNom).text?.clear()
        findViewById<TextInputEditText>(R.id.etCognom).text?.clear()
        findViewById<TextInputEditText>(R.id.etNaixement).text?.clear()
        findViewById<TextInputEditText>(R.id.etContrasenya).text?.clear()
    }

}
