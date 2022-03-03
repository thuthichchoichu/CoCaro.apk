package com.visualpro.cocaro
import android.content.Context


import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup

import android.widget.BaseAdapter
import android.widget.ImageView


class ImgRenderAdapter(var context: Context, var img: Int,  list1: ArrayList<Int>) : BaseAdapter() {
    var list= ArrayList<Int>()
    init {
        list.clear()
        list.addAll(list1)
    }


    fun setList1(list1:ArrayList<Int>){
        list.clear()
        list.addAll(list1)
    }
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    inner class ViewHoder {
        var imgHinh: ImageView? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView: View? = convertView
        val viewHoder: ViewHoder
        if (convertView == null) {
            viewHoder = ViewHoder()
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(img, null)
            viewHoder.imgHinh = convertView.findViewById(R.id.imgView12) as ImageView
            convertView.setTag(viewHoder)
        } else {
            viewHoder = convertView.tag as ViewHoder
        }
        val img = list[position]
        viewHoder.imgHinh!!.setImageResource(img)
        return convertView!!
    }
}