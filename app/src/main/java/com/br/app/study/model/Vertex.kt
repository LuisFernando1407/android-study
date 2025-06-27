package com.br.app.study.model

data class Vertex(
    val isRoot: Boolean = false,
    val value: Int,
    var edge: Edge? = null,
)