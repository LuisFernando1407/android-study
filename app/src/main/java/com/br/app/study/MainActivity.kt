package com.br.app.study

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.app.study.create.LinkedList
import com.br.app.study.model.Vertex

class MainActivity : AppCompatActivity() {

    private val tvList: TextView by lazy { findViewById(R.id.tv_list) }
    private val btReload: AppCompatButton by lazy { findViewById(R.id.bt_reload) }

    private lateinit var vertex: Vertex

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setupView()
    }

    private fun setupView() {
        statusBar()
        configure()
        preview()
        listeners()
    }

    private fun statusBar() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun configure() {
        vertex = LinkedList.generate()
    }

    private fun preview() {
        tvList.text = LinkedList.simplePreview(vertex)
    }

    private fun listeners() {
        btReload.setOnClickListener {
            configure()
            preview()
        }
    }
}
