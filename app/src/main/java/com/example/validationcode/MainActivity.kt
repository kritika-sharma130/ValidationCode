package com.example.validationcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import java.util.regex.Pattern
import android.text.TextUtils
import android.util.Patterns


class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var editText2: EditText
    private lateinit var editText3: EditText
    private lateinit var textView : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView);
        editText=findViewById(R.id.editText)
        editText2=findViewById(R.id.edittextemail)
        editText3=findViewById(R.id.edittextphone)
        textView.text = "Password minimum length 8"
        textView.append("\n1 uppercase")
        textView.append("\n1 number")
        textView.append("\n1 special character")



        editText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?,
                                           start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?,
                                       start: Int, before: Int, count: Int) {
                s?.apply {

                    if (isValidPassword() && toString().length>=8) {
                        editText.error == null
                    }else{
                        editText.error = "invalid password."
                    }
                }
            }
            override fun afterTextChanged(s: Editable?) {

            }
        })




        editText2.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?,
                                           p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?,
                                       p1: Int, p2: Int, p3: Int) {
                // check inputted text that it is a valid email address or not
                if (p0.isValidEmail()){
                    editText.error = null
                }else{
                    editText.error = "Invalid email."
                }
            }

            override fun afterTextChanged(p0: Editable?) { }
        })




        editText3.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?,
                                           p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?,
                                       p1: Int, p2: Int, p3: Int) {
                // check inputted characters is a valid phone number or not
                if (p0.isValidPhoneNumber()){
                    editText.error = null
                }else{
                    editText.error = "Invalid phone number."
                }
            }

            override fun afterTextChanged(p0: Editable?) { }
        })


    }
}



fun CharSequence.isValidPassword(): Boolean {
    val passwordPattern = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$"
    val pattern = Pattern.compile(passwordPattern)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}
fun CharSequence?.isValidEmail():Boolean{
    return !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun CharSequence?.isValidPhoneNumber():Boolean{
    return !isNullOrEmpty() && Patterns.PHONE.matcher(this).matches()
}