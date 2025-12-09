package com.example.customviewcristian

import android.content.Context
import android.content.res.ColorStateList
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat



class DNIValid(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs), TextWatcher {

    var successColor: Int
    var errorColor: Int

    private val etDNI: EditText
    private val tvErrorDNI: TextView


    init {
        inflate(context, R.layout.dni_valid, this)

        etDNI = findViewById(R.id.etDNI)
        tvErrorDNI = findViewById(R.id.tvErrorDNI)

        // Configurar colors directament
        errorColor = ContextCompat.getColor(context, R.color.red)
        successColor = ContextCompat.getColor(context, R.color.green)

        // Assegurar que el text d'error està configurat
        if (tvErrorDNI.text.isNullOrEmpty()) {
            tvErrorDNI.text = "TUU, posa-ho bé. No siguis PILLO!"
        }

        // Configurar hint
        val hint = attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "hint")
        if (!hint.isNullOrEmpty()) {
            etDNI.hint = hint
        }

        etDNI.addTextChangedListener(this)
    }



    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(s: Editable?) {
        val text = s.toString()

        // Validar DNI: ha de tenir 9 caràcters i l'últim ha de ser una lletra
        if (text.length == 9) {
            val lastChar = text.last()

            // Si el ultim és numero mostra error
            if (lastChar.isDigit()) {
                tvErrorDNI.visibility = VISIBLE
                etDNI.backgroundTintList = ColorStateList.valueOf(errorColor)
            } else {
                // mostra correcte si posa algo que no sigui numero
                tvErrorDNI.visibility = INVISIBLE
                etDNI.backgroundTintList = ColorStateList.valueOf(successColor)
            }
        } else {
            // Si no té 9 caràcters, no mostrem error però tampoc va bé
            tvErrorDNI.visibility = INVISIBLE
            etDNI.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, android.R.color.darker_gray))
        }
    }

}
