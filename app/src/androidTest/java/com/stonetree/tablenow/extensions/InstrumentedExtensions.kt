package com.stonetree.tablenow.extensions

import android.content.Context

fun String.readFile(context: Context): String {
    context.assets.open(this)
        .bufferedReader()
        .use { buffer ->
            return buffer.readText()
        }
}