package com.br.app.study

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.app.study.ui.ClusterListActivity
import com.br.app.study.ui.LinkedListActivity

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

    private fun statusBar() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
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
