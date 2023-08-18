package code.name.monkey.retromusic.interfaces

import android.view.View
import code.name.monkey.retromusic.model.GenreInfo

interface IGenreClickListener {
    fun onClickGenre(genre: GenreInfo, view: View)
}
