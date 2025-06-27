package com.br.app.study.create

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import com.br.app.study.model.Edge
import com.br.app.study.model.Vertex
import kotlin.random.Random

object LinkedList {
    fun simplePreview(vertex: Vertex): SpannableStringBuilder {
        val spannable = vertex.setRootColor()
        spannable.append(" $ARROW ")

        vertex.whileIfNextNonNull {
            spannable.append("${it?.value} ${if (it?.edge?.next != null) ARROW else ""} ")
        }

        return spannable
            .append(formatSum(vertex))
            .append(formatBigger(vertex))
            .append(formatMinor(vertex))
    }

    fun generate(): Vertex {
        val root = createRoot()
        val vertexO = createVertex()
        val vertexT = createVertex()
        val vertexTh = createVertex()
        val vertexF = createVertex()
        val vertexFv = createVertex()
        val vertexS = createVertex()
        val vertexSv = createVertex()

        root.edge = createEdge(next = vertexO)
        vertexO.edge = createEdge(next = vertexT, previous = root)
        vertexT.edge = createEdge(next = vertexTh, previous = vertexO)
        vertexTh.edge = createEdge(next = vertexF, previous = vertexT)
        vertexF.edge = createEdge(next = vertexFv, previous = vertexTh)
        vertexFv.edge = createEdge(next = vertexS, previous = vertexF)
        vertexS.edge = createEdge(next = vertexSv, previous = vertexFv)
        vertexSv.edge = createEdge(previous = vertexS)

        return root
    }

    private fun createVertex(value: Int = Random.nextInt(1, 20)) = Vertex(
        value = value
    )

    private fun createRoot(value: Int = Random.nextInt(1, 20)) = Vertex(
        isRoot = true,
        value = value
    )

    private fun createEdge(next: Vertex? = null, previous: Vertex? = null): Edge {
        return Edge(next, previous)
    }

    private fun Vertex.setRootColor(): SpannableStringBuilder {
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

    private fun formatSum(vertex: Vertex) = "\n\n\n Sum: ${sum(vertex)}"

    private fun formatBigger(vertex: Vertex) = "\n\n Bigger: ${bigger(vertex)}"

    private fun formatMinor(vertex: Vertex) = "\n\n Minor: ${minor(vertex)}"

    private fun bigger(vertex: Vertex): Int {
        var root = if (vertex.isRoot) vertex.value else 0
        vertex.whileIfNextNonNull {
            val value = it?.value ?: 0
            if (value > root) {
                root = value
            }
        }

        return root
    }

    private fun minor(vertex: Vertex): Int {
        var root = if (vertex.isRoot) vertex.value else 0

        vertex.whileIfNextNonNull {
            val value = it?.value ?: 0
            if (value < root) {
                root = value
            }
        }

        return root
    }

    private fun sum(vertex: Vertex): Int {
        var amount = if (vertex.isRoot) vertex.value else 0

        vertex.whileIfNextNonNull {
            amount += it?.value ?: 0
        }

        return amount
    }

    private fun Vertex.whileIfNextNonNull(rs: (vertex: Vertex?) -> Unit) {
        var edge = this.edge

        while (edge?.next != null) {
            val auxVertex = edge.next
            rs(auxVertex)
            edge = auxVertex?.edge
        }
    }

    private const val HYPHEN = "-"
    private const val ARROW = "\u279E"
}