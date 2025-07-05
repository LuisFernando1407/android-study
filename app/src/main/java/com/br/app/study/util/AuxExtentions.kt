package com.br.app.study.util

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import com.br.app.study.model.Cluster
import com.br.app.study.model.Vertex

const val HYPHEN = "-"
const val ARROW = "\u279E"

fun Vertex.whileIfNextNonNull(rs: (vertex: Vertex?) -> Unit) {
    var edge = this.edge

    while (edge?.next != null) {
        val auxVertex = edge.next
        rs(auxVertex)
        edge = auxVertex?.edge
    }
}

fun Cluster.whileIfNextNonNull(
    rs: (vertex: Vertex?, isRoot: Boolean, isNextRoot: Boolean, total: Int) -> Unit
) {
    var edge = this.vertex.edge
    var value = this.vertex.value

    while (edge?.next != null) {
        val auxVertex = edge.next
        value += (auxVertex?.value ?: 0)
        edge = auxVertex?.edge

        val isCurrentRoot = auxVertex?.isRoot == true
        val isNextRoot = edge?.next?.isRoot == true || edge?.next == null

        rs(auxVertex, isCurrentRoot, isNextRoot, value)

        if (isNextRoot) value = 0
    }
}

fun Vertex.whenFinalEdge(rs: (vertex: Vertex?) -> Unit) {
    var edge = this.edge

    while (edge?.next != null) {
        val auxVertex = edge.next
        edge = auxVertex?.edge

        if (edge?.next == null) rs(auxVertex)
    }
}

fun Vertex.setRootColor(): SpannableStringBuilder {
    val sValue = this.value.toString()
    val list = SpannableStringBuilder(if (this.isRoot) sValue else HYPHEN)
    val rootColor = ForegroundColorSpan(Color.RED)
    list.setSpan(
        rootColor,
        0,
        sValue.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    return list
}
