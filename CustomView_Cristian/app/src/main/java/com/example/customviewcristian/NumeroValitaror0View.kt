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

    var successColor: Int
    var errorColor: Int

    private val etNumero: EditText  // abans etMail
    private val tvErrorNumero: TextView  // abans tvErrorCode


    init {
        inflate(context, R.layout.numero_validator_0, this)  // abans email_validator


        etNumero = findViewById(R.id.etNumber)
        tvErrorNumero = findViewById(R.id.tvErrorNumber)


        val attributes = context.obtainStyledAttributes(attrs, R.styleable.EmailValidatorView)
        tvErrorNumero.text = attributes.getString(R.styleable.EmailValidatorView_textError)
        errorColor = attributes.getColor(R.styleable.EmailValidatorView_underlineErrorColor, ContextCompat.getColor(context, R.color.red))
        successColor = attributes.getColor(R.styleable.EmailValidatorView_underlineSuccessColor, ContextCompat.getColor(context, R.color.green))
        attributes.recycle()
        //perque es mostri el hunt hem de posar la propietat aqui
        val hint = attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "hint")
        etNumero.hint = hint
        etNumero.addTextChangedListener(this)
    }



    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(s: Editable?) {
        val text = s.toString()

        if (text.isNotEmpty() && text.last() == '0') {
            tvErrorNumero.visibility = View.VISIBLE
            etNumero.backgroundTintList = ColorStateList.valueOf(errorColor)

            etNumero.removeTextChangedListener(this)
            etNumero.setText(text.dropLast(1))
            etNumero.setSelection(etNumero.text.length)
            etNumero.addTextChangedListener(this)
        } else {
            tvErrorNumero.visibility = View.INVISIBLE
            etNumero.backgroundTintList = ColorStateList.valueOf(successColor)
        }
    }

}
