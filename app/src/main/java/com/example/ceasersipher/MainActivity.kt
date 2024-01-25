package com.example.ceasersipher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // We need a list of letters for reference
    val letterList = ('A'..'Z').toMutableList() + ('a'..'z').toMutableList() + ' '

    // Encrypt the message when the encrypt button is pressed
    fun encryptMessage(view: View){
        val editText = findViewById<EditText>(R.id.messageInput)
        val message = editText.text.toString()
        val keyInput = findViewById<EditText>(R.id.keyInput)
        val key = keyInput.text.toString().toInt()

        // Here we save the encrypted message
        var newMessage = ""

        // Here is the encryption proper
        for (letter in message) {
            if (letter.isLetter() || letter == ' ') {
                val letterIndex = letterList.indexOf(letter)
                var newIndex = letterIndex + key
                // If we have an overflow, we want to make sure it loops around the letter list
                while (newIndex >= letterList.size) {
                    newIndex -= letterList.size
                }
                val newLetter = letterList[newIndex]
                newMessage += newLetter
            }
            else {
                newMessage += letter
            }
        }
        val textView = findViewById<TextView>(R.id.messageDisplay).apply {
            text = newMessage
        }
    }

    fun decryptMessage(view: View){
        val editText = findViewById<EditText>(R.id.messageInput)
        val message = editText.text.toString()
        val keyInput = findViewById<EditText>(R.id.keyInput)
        val key = keyInput.text.toString().toInt()

        // Here we save the decrypted message
        var newMessage = ""

        // Here is the decryption proper
        for (letter in message) {
            if (letter.isLetter() || letter == ' ') {
                val letterIndex = letterList.indexOf(letter)
                var newIndex = letterIndex - key
                // If we have an underflow, we want to make sure it loops around the letter list
                while (newIndex < 0) {
                    newIndex += letterList.size
                }
                val newLetter = letterList[newIndex]
                newMessage += newLetter
            }
            else{
                newMessage += letter
            }
        }
        val textView = findViewById<TextView>(R.id.messageDisplay).apply {
            text = newMessage
        }
    }
}