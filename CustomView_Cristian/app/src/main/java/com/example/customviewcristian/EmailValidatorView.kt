package com.example.customviewcristian

import android.content.Context
import android.graphics.PorterDuff
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import java.util.regex.Pattern
import kotlin.text.matches

class EmailValidatorView(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs), TextWatcher {

    var successColor: Int // Color per a l'estat de validació correcta
    var errorColor: Int // Color per a l'estat de validació incorrecta

    private val etMail: EditText // Camp de text per introduir l'email
    private val tvErrorCode: TextView // Missatge d'error visible quan l'email no és vàlid

    init {
        // Inflem el layout associat a aquest component personalitzat
        inflate(context, R.layout.email_validator, this)

        etMail = findViewById(R.id.etMail)
        tvErrorCode = findViewById(R.id.tvErrorCode)

        // Obtenim els atributs personalitzats definits al XML
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.EmailValidatorView)
        tvErrorCode.text = attributes.getString(R.styleable.EmailValidatorView_textError) // Text d'error personalitzat
        errorColor = attributes.getColor(R.styleable.EmailValidatorView_underlineErrorColor, ContextCompat.getColor(context, R.color.red))
        successColor = attributes.getColor(R.styleable.EmailValidatorView_underlineSuccessColor, ContextCompat.getColor(context, R.color.green))
        attributes.recycle()

        // Configurar el hint si està definit al XML, si no posar un per defecte
        val hint = attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "hint")
        if (!hint.isNullOrEmpty()) {
            etMail.hint = hint
        }
        // Si el hint del layout també està buit, posem un per defecte
        if (etMail.hint.isNullOrEmpty()) {
            etMail.hint = "Escriu un mail"
        }

        // Afegim un listener per escoltar els canvis en el text
        etMail.addTextChangedListener(this)
    }

    override fun afterTextChanged(s: Editable?) {
        // No fem
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // No fem
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // Patró per validar un email
        val pattern = Pattern.compile("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b")
        val matcher = pattern.matcher(s.toString())
        val valid = matcher.matches()

        if (valid) {
            // Si l'email és vàlid, amaguem el missatge d'error i canviem el color de la línia inferior
            tvErrorCode.visibility = View.INVISIBLE
            etMail.backgroundTintList = android.content.res.ColorStateList.valueOf(successColor)
        } else {
            // Si l'email no és vàlid, mostrem el missatge d'error i canviem el color de la línia inferior
            tvErrorCode.visibility = View.VISIBLE
            etMail.backgroundTintList = android.content.res.ColorStateList.valueOf(errorColor)
        }
    }

}
