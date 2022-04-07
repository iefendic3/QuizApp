package ba.etf.rma22.projekat

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView


class MySpinnerAdapter(context: Context?, resourceId: Int, items: List<String?>?) :
        ArrayAdapter<String?>(context!!,resourceId,items!!){
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        var v: View
        if(position==0){
            var tv = TextView(getContext())
            tv.setHeight(0)
            tv.setVisibility(View.GONE)
            v = tv
        }
        else{
            v=super.getDropDownView(position, null, parent)
        }
        parent.setVerticalScrollBarEnabled(false)
        return v
    }

}