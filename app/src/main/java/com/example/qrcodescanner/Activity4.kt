package com.example.qrcodescanner

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main2.*

class Activity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_4)

        val actionBar = supportActionBar
        actionBar!!.title = "History"
        actionBar.setDisplayHomeAsUpEnabled(true)

        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)==
            PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA),123)
        }
        val list: ArrayList<String> = ArrayList()

        // Khởi tạo array adapter
        val adapter: ArrayAdapter<String> = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line, list
        )
        // Xác định button và listview
        val listView = findViewById<ListView>(R.id.qr_list)
        val newScanButton = findViewById<Button>(R.id.add_new_qrcode)
        val removeAll = findViewById<Button>(R.id.remove_qrcode)
        // Đang nhận kết quả từ activity con
        val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result: ActivityResult ->
            // Kiểm tra kết quả
            if (result.resultCode == Activity.RESULT_OK) {
                val qrCode = result.data?.getStringExtra("qrCode").toString()
                if (qrCode != "") {
                    // Thêm vào list
                    list.add(qrCode)
                    adapter.notifyDataSetChanged()
                    listView.adapter = adapter
                }
            }
        }
        // Lắng nghe sự kiện click của button scan qrcode
        newScanButton.setOnClickListener {
            // Mở form ScanBarCode
            startForResult.launch(Intent(this, MainActivity2::class.java))
        }

        removeAll.setOnClickListener {
            list.clear()
            adapter.notifyDataSetChanged()
            listView.adapter = adapter
        }

    }}