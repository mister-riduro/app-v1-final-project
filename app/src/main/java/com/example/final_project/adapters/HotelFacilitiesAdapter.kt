//package com.example.final_project.adapters
//
//import android.content.Context
//import android.graphics.BitmapFactory
//import android.provider.ContactsContract.CommonDataKinds.Website.URL
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ArrayAdapter
//import android.widget.ImageView
//import android.widget.TextView
//import com.example.final_project.R
//import com.example.final_project.models.hotelFacilities.HotelFacilities
//import java.net.URL
//
//
//class HotelFacilitiesAdapter(context: Context, hotelModelArraylist: ArrayList<HotelFacilities?>?) :
//    ArrayAdapter<HotelFacilities?>(context, 0, hotelModelArraylist!!) {
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//
//        var listitemView = convertView
//        if (listitemView == null) {
//            // Layout Inflater inflates each item to be displayed in GridView.
//            listitemView = LayoutInflater.from(context).inflate(R.layout.item_hotel_facilities, parent, false)
//        }
//
//        val url = URL("http://image10.bizrate-images.com/resize?sq=60&uid=2216744464")
//        val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
//
//        val hotelModel: HotelFacilities? = getItem(position)
//        val facilitiesTV = listitemView!!.findViewById<TextView>(R.id.tv_facilities_hotel)
//        val facilitiesIV = listitemView.findViewById<ImageView>(R.id.iv_icon_facilities)
//
////        facilitiesTV.text = hotelModel?.facilities_name
//        facilitiesIV.setImageBitmap(bmp)
//        return listitemView
//    }
//}