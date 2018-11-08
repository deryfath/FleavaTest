package com.example.dai_01.fleavatest.activity.main.adapter

import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.dai_01.fleavatest.R
import com.example.dai_01.fleavatest.R.drawable.sun
import com.example.dai_01.fleavatest.activity.main.MainActivity
import com.example.dai_01.fleavatest.activity.main.MainFragment
import com.example.dai_01.fleavatest.extension.debug
import com.example.dai_01.fleavatest.extension.inflate
import com.mikhaellopez.circularimageview.CircularImageView



class RecyclerCardMainAdapter (private val items:MutableList<Map<String,String>>) : RecyclerView.Adapter<RecyclerCardMainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val inflatedView= parent!!.inflate(R.layout.main_card,false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val view=holder?.itemView
        val data=items[position]
        view?.let {
            it.visibility= View.VISIBLE

            if(position%2==0){
                Glide.with(it.context).load(R.drawable.image_1).into(it.findViewById(R.id.image_card) as ImageView)
                Glide.with(it.context).load(R.drawable.photo_female_1).into(it.findViewById(R.id.image_profile) as CircularImageView)

            }else{

                Glide.with(it.context).load(R.drawable.image_2).into(it.findViewById(R.id.image_card) as ImageView)
                Glide.with(it.context).load(R.drawable.photo_female_2).into(it.findViewById(R.id.image_profile) as CircularImageView)

            }

            (it.findViewById(R.id.title_card) as TextView).text=data.get("title").toString()
            (it.findViewById(R.id.desc_card) as TextView).text=data.get("desc").toString()
            (it.findViewById(R.id.author) as TextView).text= "Author By "+data.get("name").toString()
            debug("DATA : $data")

        }

    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v){

    }
}