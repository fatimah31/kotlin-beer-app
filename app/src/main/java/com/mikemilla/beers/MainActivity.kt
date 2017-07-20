package com.mikemilla.beers

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso

/**
 * Main Activity
 */
class MainActivity : AppCompatActivity() {

    // Create the view
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // List of beers
        val beers: MutableList<Beer> = mutableListOf(
                Beer("http://apintofhoppiness.com/wp-content/uploads/2016/01/founders-all-day-ipa-set.jpg", "All Day IPA", "Founders"),
                Beer("https://www.bellsbeer.com/sites/default/files/brands/Amber_WebPic_736X736.png", "Amber Ale", "Bells"),
                Beer("https://3.bp.blogspot.com/-Z8M7BBbWhk8/WNa3udwOooI/AAAAAAAAH5s/bcr3hkdSqSAUY5S_fU8z05cX2JVugirwACLcB/s1600/Perrin%2BGrapefruit%2BIPA.JPG", "Grapefruit IPA", "Perrin"),
                Beer("http://wwtv.images.worldnow.com/images/12506813_G.jpg", "Super Trooper", "Petosky"),
                Beer("https://2.bp.blogspot.com/-ZOmGgBcAcWA/V3VutWFj6tI/AAAAAAAAN3Q/JGzGr9xv2wIU7tXgiXCE6vwLycmlQvlDwCLcB/s1600/Terrapin%2BWatermelon%2BGose.jpg", "Watermelon Gose", "Terrapin"),
                Beer("http://buffalobeerbiochemist.com/wp-content/uploads/2015/05/Citra-Pale-Ale-773x1030.jpg", "Citra Pale Ale", "Oddside Ales"),
                Beer("http://1.bp.blogspot.com/-2_93-bESxeY/T34Pac0gO_I/AAAAAAAAD5E/8vK03O6dDE0/s1600/B+Nektar+Zombie+Killer+Cherry+Cyser.JPG", "Zombie Killer", "Bnektar"),
                Beer("http://media.mlive.com/grpress/entertainment_impact/photo/bells-two-hearted-alejpg-cc8168547d269f35.jpg", "Two Hearted Ale", "Bells"),
                Beer("http://apintofhoppiness.com/wp-content/uploads/2016/01/founders-all-day-ipa-set.jpg", "All Day IPA", "Founders"),
                Beer("https://www.bellsbeer.com/sites/default/files/brands/Amber_WebPic_736X736.png", "Amber Ale", "Bells"),
                Beer("https://3.bp.blogspot.com/-Z8M7BBbWhk8/WNa3udwOooI/AAAAAAAAH5s/bcr3hkdSqSAUY5S_fU8z05cX2JVugirwACLcB/s1600/Perrin%2BGrapefruit%2BIPA.JPG", "Grapefruit IPA", "Perrin"),
                Beer("http://wwtv.images.worldnow.com/images/12506813_G.jpg", "Super Trooper", "Petosky"),
                Beer("https://2.bp.blogspot.com/-ZOmGgBcAcWA/V3VutWFj6tI/AAAAAAAAN3Q/JGzGr9xv2wIU7tXgiXCE6vwLycmlQvlDwCLcB/s1600/Terrapin%2BWatermelon%2BGose.jpg", "Watermelon Gose", "Terrapin"),
                Beer("http://buffalobeerbiochemist.com/wp-content/uploads/2015/05/Citra-Pale-Ale-773x1030.jpg", "Citra Pale Ale", "Oddside Ales"),
                Beer("http://1.bp.blogspot.com/-2_93-bESxeY/T34Pac0gO_I/AAAAAAAAD5E/8vK03O6dDE0/s1600/B+Nektar+Zombie+Killer+Cherry+Cyser.JPG", "Zombie Killer", "Bnektar"),
                Beer("http://media.mlive.com/grpress/entertainment_impact/photo/bells-two-hearted-alejpg-cc8168547d269f35.jpg", "Two Hearted Ale", "Bells")
        )

        // Build RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = ListAdapter(beers)
    }
}

/**
 * Set data for our list
 */
class ListAdapter(val beer: MutableList<Beer>) : RecyclerView.Adapter<ViewHolder>() {

    // Toast variable
    // Allows us to reuse one toast
    var toast: Toast? = null

    // Gets the number of items the recycle view will show
    override fun getItemCount(): Int = beer.size

    // Creates the view for an item in the list
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        return ViewHolder(layoutInflater.inflate(R.layout.main_item, parent, false))
    }

    // Binds the data of an item in the list
    @SuppressLint("ShowToast")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // Get proper index
        val index = holder.adapterPosition

        // Get beer at position
        val beer = beer[index]

        // Set values
        holder.title.text = beer.name
        holder.subtitle.text = beer.brewery
        Picasso.with(holder.itemView.context).load(beer.image).into(holder.image) // ** Don't forget to add internet permission to manifest

        // Handle clicks
        holder.itemView.setOnClickListener {

            // Check if we need to create the toast
            if (toast == null) {
                toast = Toast.makeText(holder.itemView.context, "${beer.name} \uD83C\uDF7B", Toast.LENGTH_SHORT)
            } else {
                toast!!.setText("${beer.name} \uD83C\uDF7B")
            }

            // Show toast
            toast!!.show()
        }
    }

}

/**
 * List item
 */
class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    // "!!" is added to the end of these lines because these views cannot be null
    val image = view.findViewById<AppCompatImageView>(R.id.image_view)!!
    val title = view.findViewById<BeerAppTextView>(R.id.title)!!
    val subtitle = view.findViewById<BeerAppTextView>(R.id.subtitle)!!
}

/**
 * Custom Font TextView
 * Must have @JvmOverloads so it can be compiled
 */
class BeerAppTextView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0)
    : TextView(context, attrs, defStyle) {

    // Set the font for the view
    init {
        typeface = Typeface.createFromAsset(resources.assets, resources.getString(R.string.fontABeeZeeRegular))
    }
}

/**
 * Custom class for each beer
 */
class Beer(var image: String? = null, var name: String? = null, var brewery: String? = null)