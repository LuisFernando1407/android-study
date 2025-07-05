package com.br.app.study.create

import android.text.SpannableStringBuilder
import android.util.Log
import com.br.app.study.model.Cluster
import com.br.app.study.model.Edge
import com.br.app.study.util.ARROW
import com.br.app.study.util.setRootColor
import com.br.app.study.util.whenFinalEdge
import com.br.app.study.util.whileIfNextNonNull

object ClusterList {

    private fun Cluster.kEdge(cluster: Cluster) {
        vertex.whenFinalEdge {
            if (!cluster.vertex.isRoot) {
                Log.e(TAG, "The next cluster needs to start with a root vertex")
                return@whenFinalEdge
            }

            it?.edge = Edge(next = cluster.vertex)

            val edge = cluster.vertex.edge
            cluster.vertex.edge = Edge(
                next = edge?.next,
                previous = it
            )
        }
    }

    private fun vertices() = LinkedList.generate()

    fun generate(): Cluster {
        val k1 = Cluster(vertices())
        val k2 = Cluster(vertices())
        val k3 = Cluster(vertices())
        val k4 = Cluster(vertices())

        k1.kEdge(k2)
        k2.kEdge(k3)
        k3.kEdge(k4)

        return k1
    }

    fun simplePreview(cluster: Cluster): SpannableStringBuilder {
        val spannable: SpannableStringBuilder = cluster.vertex.setRootColor()
        var rootValue = cluster.vertex.value.toString()

        val totals: ArrayList<String> = arrayListOf()
        spannable.append(" $ARROW ")

        cluster.whileIfNextNonNull { vertex, isRoot, isNextRoot, total ->
            if (isRoot) {
                rootValue = vertex?.value?.toString() ?: "S/I"
                spannable.append(vertex?.setRootColor())
                spannable.append(" $ARROW ")
            } else {
                spannable.append("${vertex?.value} ${if (vertex?.edge?.next != null) ARROW else ""} ")
            }

            if (isNextRoot) {
                totals.add(formatTotal(total, rootValue))
            }
        }

        totals.forEach { spannable.append(it) }

        return spannable
    }

    private fun formatTotal(total: Int, id: String) = "\n\nC${id} Total: $total"

    private val TAG = ClusterList::class.java.canonicalName
}