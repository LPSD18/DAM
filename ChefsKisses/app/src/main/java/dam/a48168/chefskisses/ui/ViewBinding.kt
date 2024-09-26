package dam.a48168.chefskisses.ui

import android.graphics.Bitmap
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.google.android.material.card.MaterialCardView
import dam.a48168.chefskisses.R

object ViewBinding {

    @JvmStatic
    @BindingAdapter("paletteImage", "paletteView")
    fun bindLoadImagePalette(view: AppCompatImageView, url: String?, paletteView: MaterialCardView) {
        if (url == null) {
            Log.e("ViewBinding", "URL is null")
            return
        }

        Glide.with(view.context)
            .asBitmap()
            .load(url)
            .listener(object : RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.d("TAG", e?.message.toString())
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap,
                    model: Any,
                    target: Target<Bitmap>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.d("TAG", "OnResourceReady")
                    if (resource != null) {
                        val p: Palette = Palette.from(resource).generate()
                        val rgb = p.lightMutedSwatch?.rgb
                        if (rgb != null) {
                            paletteView.setCardBackgroundColor(rgb)
                        }
                    }
                    return false
                }
            })
            .into(view)
    }
}