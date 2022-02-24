package com.visualpro.cocaro.model


data class Coordinates(val x:Int, val y:Int) {

    override fun equals(other: Any?): Boolean {
        if(other is Coordinates){
            return other.x==this.x && other.y==this.y
        }
        return false
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }
}

