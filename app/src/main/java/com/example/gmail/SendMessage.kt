package com.example.gmail

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class SendMessage : Activity() {
    private lateinit var primaryTextView: TextView
    private lateinit var send: Button
    private lateinit var from: EditText
    private lateinit var to: EditText
    private lateinit var subject: EditText
    private lateinit var mail: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mailsend)

        try {
            initializeViews()
            setupListeners()
            handleIncomingIntent()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Error initializing: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initializeViews() {
        from = findViewById(R.id.from)
        to = findViewById(R.id.to)
        subject = findViewById(R.id.subject)
        primaryTextView = findViewById(R.id.primaryTextView)
        mail = findViewById(R.id.mail)
        send = findViewById(R.id.send)
    }

    private fun setupListeners() {
        primaryTextView.setOnClickListener {
            finish()  // Better than creating new activity
        }

        send.setOnClickListener {
            sendEmail()
        }
    }

    private fun handleIncomingIntent() {
        // Handle reply/replyAll data if coming from MailDetails
        intent?.let { intent ->
            if (intent.getBooleanExtra("isReply", false)) {
                to.setText(intent.getStringExtra("originalSender") ?: "")
                subject.setText(intent.getStringExtra("originalSubject") ?: "")
            }
        }
    }

    private fun sendEmail() {
        try {
            val toAddress = to.text.toString()
            val subjectText = subject.text.toString()
            val messageText = mail.text.toString()

            if (toAddress.isBlank()) {
                to.error = "Please enter recipient email"
                return
            }

            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:") // only email apps should handle this
                putExtra(Intent.EXTRA_EMAIL, arrayOf(toAddress))
                putExtra(Intent.EXTRA_SUBJECT, subjectText)
                putExtra(Intent.EXTRA_TEXT, messageText)
            }

            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
                Toast.makeText(applicationContext, "Opening email app...", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "No email app found", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Error sending email: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}