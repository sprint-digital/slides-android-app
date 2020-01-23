package com.freddie.photoslidetv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_screen_slide_page.*

class ScreenSlidePageFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_screen_slide_page, container, false)
        val image = view.findViewById<ImageView>(R.id.image)
        context?.apply {
            arguments?.getInt(arg_index)?.let {
                Glide.with(this)
                    .load(it)
                    .into(image)
                //image.setImageDrawable(ContextCompat.getDrawable(this, it))
            }
        }
        return view
    }



    companion object{
        val arg_index = "ARG_index"
        fun newInstance(index : Int) : ScreenSlidePageFragment
        {
            val bundle = Bundle()
            bundle.putInt(arg_index, index)
            val f = ScreenSlidePageFragment()
            f.arguments = bundle
            return f
        }
    }

}