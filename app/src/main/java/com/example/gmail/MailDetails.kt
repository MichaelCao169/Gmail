package com.example.gmail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MailDetails : Activity() {
    private lateinit var back: ImageButton
    private lateinit var backMail: Button
    private lateinit var reply: Button
    private lateinit var replyAll: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.maildetails)

        try {
            back = findViewById(R.id.back)
            back.setOnClickListener {
                finish()  // Better than creating new activity
            }

            backMail = findViewById(R.id.backMail)
            backMail.setOnClickListener {
                finish()  // Better than creating new activity
            }

            reply = findViewById(R.id.reply)
            reply.setOnClickListener {
                val intent = Intent(this, SendMessage::class.java).apply {
                    // Pass any necessary data for reply
                    putExtra("isReply", true)
                    putExtra("originalSubject", "Re: Original Subject")
                    putExtra("originalSender", "original@email.com")
                }
                startActivity(intent)
            }

            replyAll = findViewById(R.id.replyAll)
            replyAll.setOnClickListener {
                val intent = Intent(this, SendMessage::class.java).apply {
                    putExtra("isReplyAll", true)
                    putExtra("originalSubject", "Re: Original Subject")
                    putExtra("originalSender", "original@email.com")
                }
                startActivity(intent)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}