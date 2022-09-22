package com.app.ducatusapplication

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*

import android.widget.ArrayAdapter
import android.widget.TextView


class EditGoal1 : AppCompatActivity() {
    val listItemColor = ArrayList<String>()
    val listColor = ArrayList<Int>()
    lateinit var spinner: Spinner
    lateinit var result: TextView
    lateinit var adapterColor: ArrayAdapter<String>
    lateinit var context: Context



    fun View.setMargins(
        left: Int = 0,
        top: Int = 0,
        right: Int = 0,
        bottom: Int = 0,
    ) {
        layoutParams = (layoutParams as ViewGroup.MarginLayoutParams).apply {
            setMargins(left, top, right, bottom)
        }
    }

    fun addColor(name: String, color: Int){
        if(name != null && color != null){
            listItemColor.add(name)
            listColor.add(color)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_goal1)
        context = this
        addColor("red", getColor(R.color.red_text))
        addColor("blue", getColor(R.color.blue_color))
        addColor("yellow", getColor(R.color.yellow))

        spinner = findViewById(R.id.spinner_color)

        val adapter:ArrayAdapter<String> = object: ArrayAdapter<String>(
            context,
            R.layout.color_spinner_item,
            R.id.txt_bundle,
            listItemColor
        ){
            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View
            {
                val view = getView(position, convertView, parent)
                val color = view.findViewById<View>(R.id.colorBlob)
                val db: GradientDrawable = color.background as GradientDrawable
                db.setColor(listColor[position])
                db.cornerRadius = 20f
                color.background = db
                return view
            }


        }

        spinner.adapter = adapter


        spinner.onItemSelectedListener = object
            : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                Toast.makeText(context, listItemColor[position], Toast.LENGTH_SHORT).show()
//                spinner.emptyView = spinner.selectedView
//                spinner.setBackgroundColor(listColor[position])

                val sv = spinner.selectedView
                val svv = sv.findViewById<View>(R.id.colorBlob)
                val db: GradientDrawable = GradientDrawable()
                db.setColor(listColor[position])
                db.cornerRadius = 20f
                svv.background = db
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // another interface callback
            }
        }

    }


}



private fun <T> ArrayAdapter<T>.getDropDownView() {
    TODO("Not yet implemented")
}
