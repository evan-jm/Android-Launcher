package com.example.niagaraclone

import com.example.niagaraclone.ui.theme.NiagaraCloneTheme
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var appAdapter: AppAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val packageManager: PackageManager = packageManager
        val apps: List<ApplicationInfo> = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
        appAdapter = AppAdapter(apps, this)
        recyclerView.adapter = appAdapter
    }
}
