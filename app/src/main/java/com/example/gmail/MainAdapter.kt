package com.example.gmail

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class MailAdapter(
    activity: Activity,
    private val mailArrayList: ArrayList<MailList>
) : BaseAdapter() {

    private val mInflater: LayoutInflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int = mailArrayList.size

    override fun getItem(position: Int): Any = mailArrayList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = mInflater.inflate(R.layout.mail_list, null)
        
        val mailName: TextView = view.findViewById(R.id.mailName)
        val mailContent: TextView = view.findViewById(R.id.mailContent)
        val mailNames: TextView = view.findViewById(R.id.mailNames)
        val imageView: ImageView = view.findViewById(R.id.imageView)
        
        val mail = mailArrayList[position]
        
        mailName.text = mail.name
        mailContent.text = mail.mail
        mailNames.text = mail.mailNames
        imageView.setImageResource(mail.image)
        
        return view
    }
}
