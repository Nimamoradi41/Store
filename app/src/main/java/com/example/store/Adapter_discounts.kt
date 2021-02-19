package com.example.store

import android.R
import android.animation.AnimatorInflater
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.squareup.picasso.Picasso
import com.example.store.*
import kotlinx.android.synthetic.main.custome_modal.view.*
import kotlinx.android.synthetic.main.custome_special.view.*
import kotlinx.android.synthetic.main.custome_special.view.imageView2
import kotlinx.android.synthetic.main.custome_special.view.imageView3
import kotlinx.android.synthetic.main.custome_special.view.textView3
import kotlinx.android.synthetic.main.custome_special.view.textView4
import kotlinx.android.synthetic.main.custome_special.view.textView5
import kotlinx.android.synthetic.main.custome_special.view.textView6
import kotlinx.android.synthetic.main.custome_special.view.textView7
import kotlinx.android.synthetic.main.custome_special_4.view.*
import kotlinx.android.synthetic.main.fragment_custome_slider.view.*


class Adapter_discounts(var c: Activity, var list: discounts, var H: Int, var W: Int) : RecyclerView.Adapter<Adapter_discounts.view>() {
    var interface_1:Data_dis ?=null
    var Avd:AnimatedVectorDrawable ?=null
    var Avd_Compat:AnimatedVectorDrawableCompat ?=null
    public  class view(itemView: View) : RecyclerView.ViewHolder(itemView)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view {
//         var V=LayoutInflater.from(parent.context).inflate(R.layout.custome_special, parent, false)
         var V=LayoutInflater.from(parent.context).inflate(
//             com.example.store.R.layout.custome_special_3,
             com.example.store.R.layout.custome_special_4,
             parent,
             false
         )

        return  view(V)
    }


    fun click(da: Data_dis)
    {
        this.interface_1=da
    }


    fun pxToDp(px: Float): Float {
        return (px / Resources.getSystem().getDisplayMetrics().density)
    }
    @SuppressLint("WrongConstant")
    override fun onBindViewHolder(holder: view, position: Int) {
        var v=holder.itemView.layoutParams as RecyclerView.LayoutParams
        v.width=W/2-(W/20)
//        v.width=W/2
        v.height=W*3/4
        holder.itemView.layoutParams= ViewGroup.LayoutParams(v)
        holder.itemView.requestLayout()
        var Item=list.getProducts().get(position)
//        holder.itemView.imageView2.setImageResource(list.get(position).img!!)

        if(Item?.getFirstImage()!=null)
        {
            Log.i("sfkmsmlbab", "" + Constants.BASE_URL + "/Sliders/" + Item?.getFirstImage())
//            Glide.with(c).load(Constants.BASE_URL+"/Images/"+Item?.getFirstImage()).into(holder.itemView.imageView2);
            Picasso.get().load(Constants.BASE_URL + "/Images/" + Item?.getFirstImage()).placeholder(
                com.example.store.R.drawable.holder
            ).into(holder.itemView.imageView2)
        }




//        var ddgg=AnimationUtils.loadAnimation(c,R.anim.anim_1)

//        ddgg.setRepeatCount(Animation.INFINITE);
//        ddgg.setRepeatMode(Animation.RESTART);
//        holder.itemView.imageView3.startAnimation(ddgg)


        var animator = AnimatorInflater.loadAnimator(
            c,
            com.example.store.R.animator.animatior_1
        )
        animator.setTarget(holder.itemView.imageView3)
// set all the animation-rholder.itemView.imageView3elated stuff you want (interpolator etc.)
// set all the animation-related stuff you want (interpolator etc.)
        animator.start()


//        var vss=ObjectAnimator.ofFloat(holder.itemView.imageView3,"scaleX",1f,1.5f)
//        vss.target=holder.itemView.imageView3
//        vss.setRepeatCount(Animation.INFINITE);
//        vss.setRepeatMode(Animation.RESTART);
//        vss.setDuration(2000)
//        vss.start()
//        var vss_2=ObjectAnimator.ofFloat(holder.itemView.imageView3,"scaleY",1f,1.5f)
//        vss_2.target=holder.itemView.imageView3
//        vss_2.setRepeatCount(Animation.INFINITE);
//        vss_2.setRepeatMode(Animation.RESTART);
//        vss_2.setDuration(2000)
//        vss_2.start()
//
//
//        var vss_3=ObjectAnimator.ofFloat(holder.itemView.imageView3,"scaleX",1.5f,1f)
//        vss_3.target=holder.itemView.imageView3
//        vss_3.setRepeatCount(Animation.INFINITE);
//        vss_3.startDelay=2000
//        vss_3.setRepeatMode(Animation.RESTART);
//        vss_3.setDuration(2000)
//        vss_3.start()
//        var vss_4=ObjectAnimator.ofFloat(holder.itemView.imageView3,"scaleY",1.5f,1f)
//        vss_4.target=holder.itemView.imageView3
//        vss_4.setRepeatCount(Animation.INFINITE);
//        vss_4.startDelay=2000
//        vss_4.setRepeatMode(Animation.RESTART);
//        vss_4.setDuration(2000)
//        vss_4.start()

//        var vv:AnimatedVectorDrawable
//        vv= holder.itemView.imageView3.drawable as AnimatedVectorDrawable
//
//        vv.start()










        // todo درصد تخفیف گرداشت
        if (Item.getDiscountPercent()!=null)
        {
            if (Item.getDiscountPercent()>0)
            {
                holder.itemView.textView3.setText(
                    "%" + list.getProducts().get(position).getDiscountPercent()
                )
                holder.itemView.textView5.setText(
                    list.getProducts().get(position).getPriceForShow()
                )
                holder.itemView.textView7.setText(
                    list.getProducts().get(position).getPriceForShow()
                )
            }else{
                holder.itemView.textView3.visibility=View.GONE
                holder.itemView.imageView3.visibility=View.GONE
                holder.itemView.textView5.visibility=View.GONE
                holder.itemView.textView7.visibility=View.GONE

            }
        }else{
            holder.itemView.textView3.visibility=View.GONE
            holder.itemView.imageView3.visibility=View.GONE
            holder.itemView.textView5.visibility=View.GONE
            holder.itemView.textView7.visibility=View.GONE
        }



        holder.itemView.textView4.setText(list.getProducts().get(position).getTitle())
        holder.itemView.textView6.setText(
            list.getProducts().get(position).getPriceAfterDiscountForShow()
        )


//        holder.itemView.textView5.setText(list.getProducts().get(position).getPriceForShow())
//        holder.itemView.textView7.setText(list.getProducts().get(position).getPriceForShow())



        if (Item.getCurrentReserved()>0)
        {
            holder.itemView.button_22.setText(Item.getCurrentReserved().toString())
            holder.itemView.button_22.animate().alpha(1f).setDuration(500).start()
        }else  {
            holder.itemView.button_22.setText("0")
            holder.itemView.button_22.animate().alpha(1f).setDuration(500).start()
        }


//        if (Item.getCurrentReserved()>0)
//        {
//            holder.itemView.button.setText(Item.getCurrentReserved().toString() + " عدد ")
//            holder.itemView.button.animate().alpha(1f).setDuration(500).start()
//        }else  {
//            holder.itemView.button.setText("افزودن به سبد خرید")
//            holder.itemView.button.animate().alpha(1f).setDuration(500).start()
//        }


//        if (Item.getCurrentReserved()>0)
//        {
//            holder.itemView.button.setText(Item.getCurrentReserved().toString()+" عدد ")
////            holder.itemView.button.animate().alpha(1f).setDuration(500).start()
//            holder.itemView.button.animate().scaleX(0f).scaleY(0f).setDuration(200).withEndAction {
//                holder.itemView.button.animate().scaleX(1f).scaleY(1f).setDuration(200)
//            }.start()
//        }else  {
//            holder.itemView.button.setText("افزودن به سبد خرید")
////            holder.itemView.button.animate().alpha(1f).setDuration(500).start()
//            holder.itemView.button.animate().scaleX(0f).scaleY(0f).setDuration(200).withEndAction {
//                holder.itemView.button.animate().scaleX(1f).scaleY(1f).setDuration(200)
//            }.start()
//        }


        holder.itemView.setOnClickListener {
            var I=Intent(c, ItemDetail::class.java)
            I.putExtra("data", Item)
            I.putExtra("pos", position)
            c.startActivityForResult(I, 20)
        }
      holder.itemView.constraintLayout888.setOnClickListener {
          val d=Dialog(c, com.example.store.R.style.CustomDialog)
          d.setCancelable(true)
      var v=LayoutInflater.from(c).inflate(com.example.store.R.layout.custome_modal, null, false)
          d.setContentView(v)
          var i_Count=ArrayList<String> ()

          var r = list.getProducts().get(position).getMaxCountReserve()
              var i = 0
              while (i < r) {
                  i++
                  i_Count.add(i.toString())
              }
          i_Count.add(0, "0")
          var ad_=adapter_count_shop(i_Count, d, list.getProducts().get(position).getId())
          v.recy_itemmmms.adapter=ad_
          ad_.clicl(object : adapter_count_shop.data_i {
              override fun Data_d(I: Int, Id: String) {
                  if (Item.getCount() == 0) {
                      Toast.makeText(c, "عدم موجودی", Toast.LENGTH_SHORT).show()
                      return
                  }
                  if (Item.getCurrentReserved() != I) {
//
                      interface_1?.Data(I, Id, position)
                  }
              }

          })
          d.window?.setLayout(
              ConstraintLayout.LayoutParams.MATCH_PARENT,
              ConstraintLayout.LayoutParams.MATCH_PARENT
          )
          d.window?.setBackgroundDrawable(ColorDrawable((Color.TRANSPARENT)))
          v.imageView15.setOnClickListener {
              d.dismiss()
          }

          v.cccv.setOnClickListener {

          }
          v.close.setOnClickListener {
              d.dismiss()
          }
          d.show()
      }
    }

    override fun getItemCount(): Int {
       return  list.getProducts().size
    }




public interface  Data_dis{
    public fun Data(I: Int, ID: String, Pos: Int)
}

}


