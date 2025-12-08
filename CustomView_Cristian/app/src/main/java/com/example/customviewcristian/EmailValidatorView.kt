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

    var successColor: Int
    var errorColor: Int

    private val etMail: EditText
    private val tvErrorCode: TextView

    init {
        inflate(context, R.layout.email_validator, this)

        etMail = findViewById(R.id.etMail)
        tvErrorCode = findViewById(R.id.tvErrorCode)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.EmailValidatorView)
        tvErrorCode.text = attributes.getString(R.styleable.EmailValidatorView_textError)
        errorColor = attributes.getColor(R.styleable.EmailValidatorView_underlineErrorColor, ContextCompat.getColor(context, R.color.red))
        successColor = attributes.getColor(R.styleable.EmailValidatorView_underlineSuccessColor, ContextCompat.getColor(context, R.color.green))
        attributes.recycle()
        //perque es mostri el hunt hem de posar la propietat aqui
        val hint = attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "hint")
        etMail.hint = hint
        etMail.addTextChangedListener(this)
    }

    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        val pattern = Pattern.compile("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b")
        val matcher = pattern.matcher(s.toString())
        val valid = matcher.matches()

        if (valid) {
            tvErrorCode.visibility = View.INVISIBLE
            etMail.backgroundTintList = android.content.res.ColorStateList.valueOf(successColor)
        } else {
            tvErrorCode.visibility = View.VISIBLE
            etMail.backgroundTintList = android.content.res.ColorStateList.valueOf(errorColor)
        }
    }

}


