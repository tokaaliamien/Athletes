package com.example.android.listathletes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        var athlete: Athlete = intent.getSerializableExtra("athlete") as Athlete

        tv_name.text=athlete.name
        tv_brief.text=athlete.brief

        if (athlete.image.equals(""))
            image_view.visibility = View.GONE
        else {
            image_view.visibility = View.VISIBLE
            Glide.with(this).load(athlete.image).into(image_view);
        }

    }
}
