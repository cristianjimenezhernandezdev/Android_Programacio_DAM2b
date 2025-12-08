package com.example.customviewcristian

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat

class EmailValidatorView(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs), TextWatcher {

    var successColor: Int
    var errorColor: Int

    private lateinit var etMail: EditText
    private lateinit var tvErrorCode: TextView

    init {
        inflate(context, R.layout.email_validator, this)

        etMail = findViewById(R.id.etMail)
        tvErrorCode = findViewById(R.id.tvErrorCode)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.EmailValidatorView)
        tvErrorCode.text = attributes.getString(R.styleable.EmailValidatorView_textError)
        errorColor = attributes.getColor(R.styleable.EmailValidatorView_underlineErrorColor, ContextCompat.getColor(context, R.color.red))
        successColor = attributes.getColor(R.styleable.EmailValidatorView_underlineSuccessColor, ContextCompat.getColor(context, R.color.green))
        attributes.recycle()

        etMail.addTextChangedListener(this)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
    }
}

