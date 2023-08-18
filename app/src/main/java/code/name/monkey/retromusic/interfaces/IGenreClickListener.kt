package code.name.monkey.retromusic.interfaces

import android.view.View
import code.name.monkey.retromusic.model.GenreSplit

interface IGenreClickListener {
    fun onClickGenre(genre: GenreSplit, view: View)
}
