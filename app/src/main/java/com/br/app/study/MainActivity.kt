package com.br.app.study

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.br.app.study.ui.ClusterListActivity
import com.br.app.study.ui.LinkedListActivity
import com.br.app.study.util.statusBar

class MainActivity : AppCompatActivity() {

    private val btLinked: AppCompatButton by lazy { findViewById(R.id.bt_linked) }
    private val btCluster: AppCompatButton by lazy { findViewById(R.id.bt_cluster) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setupView()
    }

    private fun setupView() {
        statusBar()
        listeners()
    }

    private fun listeners() {
        btLinked.setOnClickListener {
            LinkedListActivity.start(this)
        }

        btCluster.setOnClickListener {
            ClusterListActivity.start(this)
        }
    }
}
