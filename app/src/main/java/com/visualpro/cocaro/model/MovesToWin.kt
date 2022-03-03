package com.visualpro.cocaro.model

class MovesToWin(
    var a: Int,
    var b: Int,
    var c: Int,
    var d: Int,
    var e: Int,
    var f: Int,
) {


    fun getAB(): String = "$a$b"
    fun getCD(): String = "$c$d"
    fun getEF(): String = "$e$f"
}
