package com.br.app.study.ui

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.app.study.R
import com.br.app.study.create.LinkedList
import com.br.app.study.model.Vertex

class LinkedListActivity : AppCompatActivity() {

    private val tvList: TextView by lazy { findViewById(R.id.tv_list) }
    private val btReload: AppCompatButton by lazy { findViewById(R.id.bt_reload) }
    private val imgClose: AppCompatImageView by lazy { findViewById(R.id.img_close) }

    private lateinit var vertex: Vertex

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_linked_list)
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

        imgClose.setOnClickListener {
            finish()
        }
    }

    companion object {
        fun start(activity: AppCompatActivity) {
            activity.run {
                startActivity(Intent(this, LinkedListActivity::class.java))
            }
        }
    }
}
