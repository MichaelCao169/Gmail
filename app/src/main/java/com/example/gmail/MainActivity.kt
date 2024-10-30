package com.example.gmail

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var gmailListView: ListView
    private val mailList = ArrayList<MailList>()
    private lateinit var sendMessage: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            initializeViews()
            setupListeners()
            populateMailList()
            setupMailAdapter()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun initializeViews() {
        sendMessage = findViewById(R.id.sendMessage)
        gmailListView = findViewById(R.id.gmailListView)
    }

    private fun setupListeners() {
        sendMessage.setOnClickListener {
            val intent = Intent(this, SendMessage::class.java)
            startActivity(intent)
        }

        gmailListView.setOnItemClickListener { _, _, position, _ ->
            val selectedMail = mailList[position]
            val intent = Intent(this, MailDetails::class.java).apply {
                putExtra("sender", selectedMail.name)
                putExtra("subject", selectedMail.mail)
                putExtra("senderInitial", selectedMail.mailNames)
            }
            startActivity(intent)
        }
    }

    private fun populateMailList() {
        mailList.apply {
            add(MailList("Alice Johnson", "Hello! Let's catch up soon. How about lunch this Friday?", "A", R.menu.circle))
            add(MailList("Bob's Book Club", "New book recommendation: 'The Last Adventure'. Join us this weekend for a discussion!", "B", R.menu.circle2))
            add(MailList("Nature Photography Group", "Capture the beauty of autumn with us! Join our photo walk this Saturday.", "N", R.menu.circle3))
            add(MailList("Health & Wellness", "Stay hydrated and get enough sleep for a healthier life. Read our latest tips!", "H", R.menu.circle4))
            add(MailList("Culinary Delights", "Recipe of the day: Classic Carbonara. Try it out and share your feedback!", "C", R.menu.circle5))
            add(MailList("Alex from Tech Support", "Your recent ticket has been resolved. Let us know if you have further issues.", "T", R.menu.circle))
            add(MailList("Finance News", "Market update: Stocks saw significant growth today. Check out the details.", "F", R.menu.circle2))
            add(MailList("Emma Green", "Had a great time at the reunion! Let's plan another meet-up soon.", "E", R.menu.circle3))
        }
    }

    private fun setupMailAdapter() {
        val mailAdapter = MailAdapter(this, mailList)
        gmailListView.adapter = mailAdapter
    }
}