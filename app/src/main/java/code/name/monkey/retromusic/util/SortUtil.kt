package code.name.monkey.retromusic.util

import code.name.monkey.retromusic.helper.SortOrder
import code.name.monkey.retromusic.model.Song
import org.koin.core.component.KoinComponent

object SortUtil : KoinComponent {
    fun sortSongsByPreference(songs: List<Song>): List<Song> {
        return when (PreferenceUtil.songSortOrder) {
            SortOrder.SongSortOrder.SONG_A_Z -> songs.sortedBy { it.title }
            SortOrder.SongSortOrder.SONG_Z_A -> songs.sortedByDescending { it.title }
            SortOrder.SongSortOrder.SONG_ARTIST -> songs.sortedBy { it.artistName }
            SortOrder.SongSortOrder.SONG_ALBUM_ARTIST -> songs.sortedBy { it.albumArtist }
            SortOrder.SongSortOrder.SONG_ALBUM -> songs.sortedBy { it.albumName }
            SortOrder.SongSortOrder.SONG_YEAR -> songs.sortedBy { it.year }
            SortOrder.SongSortOrder.SONG_DURATION -> songs.sortedBy { it.duration }
            SortOrder.SongSortOrder.SONG_DATE -> songs.sortedBy { it.dateModified }
            SortOrder.SongSortOrder.SONG_DATE_MODIFIED -> songs.sortedBy { it.dateModified }
            SortOrder.SongSortOrder.COMPOSER -> songs.sortedBy { it.composer }
            SortOrder.SongSortOrder.SONG_DEFAULT -> songs.sortedBy { it.title }
            else -> songs.sortedBy { it.title }
        }
    }
}
