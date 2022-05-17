package com.example.qrcodescanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val add_new_qrcode: Button = findViewById(R.id.add_new_qrcode)
        add_new_qrcode.setOnClickListener(){
            val intent = Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }
        val QRGenerator: Button = findViewById(R.id.QRGenerator)
        QRGenerator.setOnClickListener(){
            val intent = Intent(this,Activity3::class.java)
            startActivity(intent)
        }
        val showHistoryBtn: Button = findViewById(R.id.showHistoryBtn)
        showHistoryBtn.setOnClickListener(){
            val intent = Intent(this,Activity4::class.java)
            startActivity(intent)
        }



    }
}