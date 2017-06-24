package com.jackrockz.root.events

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import com.celerysoft.imagepager.adapter.SimpleImagePagerAdapter
import com.jackrockz.MyApplication
import com.jackrockz.R
import com.jackrockz.api.EventModel
import com.jackrockz.api.GalleryModel
import com.jackrockz.commons.RxBaseActivity
import com.jackrockz.utils.Utils
import kotlinx.android.synthetic.main.activity_event_detail.*
import kotlinx.android.synthetic.main.image_slider.*
import com.celerysoft.imagepager.animation.DepthPageTransformer



class EventDetailActivity : RxBaseActivity(), View.OnClickListener {
    lateinit var event: EventModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)

        event = MyApplication.instance.currentEvent

        supportActionBar!!.subtitle = Utils.getStringFromTwoDates(event.start_date, event.end_date)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        txtRegularPrice.paintFlags = txtRegularPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        btnGet.setOnClickListener(this)

        txtTitle.text = event.title
        txtSubtitle.text = event.subtitle
        txtViewsCount.text = event.views_count.toString() + "x"
        txtExpCount.text = event.experience_count.toString() + "x"
        txtGuestCount.text = event.guestlist_count.toString() + "x"
        txtPrice.text = event.price
        txtRegularPrice.text = event.regular_price

        if (event.gallery?.items != null) {
//            viewpager.adapter = ImagePagerAdapter(this, event.gallery!!.items!! as ArrayList<GalleryModel>)
//            indicator.setViewPager(viewpager)

            viewpager.setPageTransformer(true, DepthPageTransformer())
            viewpager.adapter = SimpleImagePagerAdapter(this).apply {
                scaleType = ImageView.ScaleType.CENTER_INSIDE
                images = ArrayList<SimpleImagePagerAdapter.Image>(
                        event.gallery!!.items!!.map {
                            val imageUrl = it.image!!.medium
                            SimpleImagePagerAdapter.Image().apply { setImageUrl(imageUrl) }
                        })
            }
        }
        supportActionBar!!.title = event.venue.name

//        try{
//            initMap()
//        } catch (e: Exception) {
//
//        }
    }

    override fun onResume() {
        super.onResume()
//        initMap()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        startActivity(Intent(this, EventPaymentActivity::class.java))
        this.overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right)
    }
}
