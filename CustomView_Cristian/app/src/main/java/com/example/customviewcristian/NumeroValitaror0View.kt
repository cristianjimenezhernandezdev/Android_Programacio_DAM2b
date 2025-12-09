package com.example.customviewcristian

import android.content.Context
import android.content.res.ColorStateList
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat

class NumeroValitaror0View(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs), TextWatcher {

    var successColor: Int // Color quan el número és vàlid
    var errorColor: Int // Color quan el número no és vàlid

    private val etNumero: EditText  // Camp de text per introduir números
    private val tvErrorNumero: TextView  // Missatge d'error per números no vàlids

    init {
        // Inflem el layout i inicialitzem els elements visuals
        inflate(context, R.layout.numero_validator_0, this)

        etNumero = findViewById(R.id.etNumber)
        tvErrorNumero = findViewById(R.id.tvErrorNumber)

        // Assignem colors per a èxit i error
        errorColor = ContextCompat.getColor(context, R.color.red)
        successColor = ContextCompat.getColor(context, R.color.green)

        // Missatge d'error per defecte si no està configurat
        if (tvErrorNumero.text.isNullOrEmpty()) {
            tvErrorNumero.text = "El número no pot acabar en 0"
        }

        // Configurar el hint
        val hint = attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "hint")
        if (!hint.isNullOrEmpty()) {
            etNumero.hint = hint
        }

        // Afegim un listener per els canvis al text
        etNumero.addTextChangedListener(this)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // No fem res abans del canvi
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // No fem res durant el canvi
    }

    override fun afterTextChanged(s: Editable?) {
        val text = s.toString()

        // Comprovem si el text acaba en 0
        if (text.isNotEmpty() && text.last() == '0') {
            // Mostrem error i canviem el color de la línia
            tvErrorNumero.visibility = View.VISIBLE
            etNumero.backgroundTintList = ColorStateList.valueOf(errorColor)

            // Eliminem l'últim caràcter 0 i actualitzem el text
            etNumero.removeTextChangedListener(this)
            etNumero.setText(text.dropLast(1))
            etNumero.setSelection(etNumero.text.length)
            etNumero.addTextChangedListener(this)
        } else {
            // Amaguem l'error i canviem el color a correcte
            tvErrorNumero.visibility = View.INVISIBLE
            etNumero.backgroundTintList = ColorStateList.valueOf(successColor)
        }
    }
}
