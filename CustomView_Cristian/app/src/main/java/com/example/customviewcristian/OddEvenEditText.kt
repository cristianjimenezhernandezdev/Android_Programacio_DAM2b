package com.example.customviewcristian

import android.content.Context
import android.graphics.PorterDuff
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat

class OddEvenEditText : AppCompatEditText {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        // Configurem el camp de text perquè només accepti números
        inputType = InputType.TYPE_CLASS_NUMBER

        // Afegim el hint
        hint = "Escriu un número"

        // Afegim un listener per detectar canvis en el text
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                // Comprovem si el text no està buit
                if (!p0.isNullOrEmpty()) {
                    // Si el número és parell, canviem el color de la línia inferior a blau
                    if (p0.toString().toDouble() % 2 == 0.0) {
                        //Mètode deprecated background.setColorFilter(ContextCompat.getColor(context, R.color.blue), PorterDuff.Mode.SRC_IN)
                        backgroundTintList = android.content.res.ColorStateList.valueOf(ContextCompat.getColor(context, R.color.blue))

                    } else {
                        // Si el número és imparell, canviem el color de la línia inferior a vermell
                        //Mètode antic deprecated background.setColorFilter(ContextCompat.getColor(context, R.color.red), PorterDuff.Mode.SRC_IN)
                        backgroundTintList = android.content.res.ColorStateList.valueOf(ContextCompat.getColor(context, R.color.red))

                    }
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // No fem res abans del canvi
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // No fem res durant el canvi
            }
        })
    }
}
