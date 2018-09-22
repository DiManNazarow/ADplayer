package dmitriy_nazarov.ru.adplayer.features.albumlist.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import dmitriy_nazarov.ru.adplayer.R
import dmitriy_nazarov.ru.adplayer.features.albumlist.models.Album
import dmitriy_nazarov.ru.adplayer.features.list.BaseRecyclerViewHolder

class AlbumListViewHolder(rootView: ViewGroup) : BaseRecyclerViewHolder<Album>(LayoutInflater.from(rootView.context).inflate(R.layout.layout_album_list_view_holder, rootView, false)) {

    private val albumArtImageView: ImageView = itemView.findViewById(R.id.album_art_image_view)
    private val albumNameTextView: TextView = itemView.findViewById(R.id.album_name_text_view)
    private val albumArtistTextView: TextView = itemView.findViewById(R.id.album_artist_text_view)

    override fun fill() {
        Picasso.get().load(R.drawable.art).into(albumArtImageView)
        albumNameTextView.text = model!!.albumName
        albumArtistTextView.text = model!!.albumArtist
    }


}