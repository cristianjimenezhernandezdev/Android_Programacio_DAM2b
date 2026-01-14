package com.example.examen
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.core.content.ContextCompat

class CustomLayout(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs), TextWatcher {
    private var editText: EditText
    private var errorColor: Int = 0

    init {
        // posa el layout custom
        LayoutInflater.from(context).inflate(R.layout.custom_layout, this, true)
        editText = findViewById(R.id.customEdit)
        editText.addTextChangedListener(this)


        val a: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomLayout)
        errorColor = a.getColor(R.styleable.CustomLayout_errorColor, ContextCompat.getColor(context, android.R.color.holo_red_dark))
        a.recycle()
    }
//Faig el mateix truco que amb l'exercici del DNI, i limitar a 9 numeros i mirar que siguin digit
    override fun afterTextChanged(s: Editable?) {
        val text = s?.toString() ?: ""
        if (text.length == 9 && text.all { it.isDigit() }) {
            // Correcte: subratllat normal
            editText.background = ContextCompat.getDrawable(context, android.R.drawable.edit_text)
            editText.error = null
        } else {

            // Faig el GradientDrawable que tinc en una prova
            val drawable = android.graphics.drawable.GradientDrawable()
            drawable.setColor(android.graphics.Color.TRANSPARENT)
            drawable.cornerRadius = 8f
            drawable.setStroke(4, errorColor)
            editText.background = drawable
            editText.error = "El telèfon ha de tenir exactament 9 dígits"
            if (text.length > 0) {
                Toast.makeText(context, "El telèfon ha de tenir exactament 9 dígits", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
}