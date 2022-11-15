package com.laszlo.musicapi.service;

import com.laszlo.musicapi.model.Artist;
import com.laszlo.musicapi.model.Song;
import com.laszlo.musicapi.repository.ArtistRepository;
import com.laszlo.musicapi.repository.SongRepository;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ArtistServiceTest {
    private final ArtistRepository artistRepository = mock(ArtistRepository.class);
    private final SongRepository songRepository = mock(SongRepository.class);
    private final SongService songService = new SongService(songRepository);
    private final ArtistService artistService = new ArtistService(artistRepository, songService);
    private final String genre = "ExistingGenre";
    private final String artistName1 = "artistMatchingGenre1";
    private final String artistName2 = "artistMatchingGenre2";

    @Test
    void artistsByGenreNoMatches() {
        String genre = "Non existing genre";
        when(songRepository.findAll()).thenReturn(Collections.emptyList());
        when(artistRepository.findAll()).thenReturn(List.of(
                createArtist("artist1"),
                createArtist("artist2"),
                createArtist("artist3")));

        Set<Artist> artists = artistService.artistsByGenre(genre);

        assertThat(artists).isEmpty();
    }

    @Test
    void artistsByGenreOneMatch() {
        when(songRepository.findAll()).thenReturn(List.of(
                createSong(artistName1, genre),
                createSong(artistName1, genre)));
        Artist artistWithGenre = createArtist(artistName1);
        when(artistRepository.findAll()).thenReturn(List.of(artistWithGenre));

        Set<Artist> matchingArtists = artistService.artistsByGenre(genre);

        assertThat(matchingArtists).isNotEmpty();
        assertThat(matchingArtists).containsExactly(artistWithGenre);
    }

    @Test
    void artistsByGenreTwoMatches() {
        when(songRepository.findAll()).thenReturn(List.of(
                createSong(artistName1, genre),
                createSong(artistName2, genre)));
        Artist artistWithGenre1 = createArtist(artistName1);
        Artist artistWithGenre2 = createArtist(artistName2);
        when(artistRepository.findAll()).thenReturn(List.of(artistWithGenre1,artistWithGenre2));

        Set<Artist> matchingArtists = artistService.artistsByGenre(genre);

        assertThat(matchingArtists).isNotEmpty();
        assertThat(matchingArtists).containsExactlyInAnyOrder(artistWithGenre1, artistWithGenre2);
    }

    @Test
    void artistByGenreOneMatchWithNullValues(){
        when(songRepository.findAll()).thenReturn(List.of(
                createSong(null, null),
                createSong(null, genre),
                createSong("artistName", null),
                createSong(artistName1, genre)));
        Artist artistWithGenre1 = createArtist(artistName1);
        Artist artistWithGenre2 = createArtist(null);
        when(artistRepository.findAll()).thenReturn(List.of(artistWithGenre1,artistWithGenre2));

        Set<Artist> matchingArtists = artistService.artistsByGenre(genre);

        assertThat(matchingArtists).isNotEmpty();
        assertThat(matchingArtists).containsExactlyInAnyOrder(artistWithGenre1);
    }

    private Song createSong(String artist, String genre) {
        Song song = new Song();
        song.setGenre(genre);
        song.setArtist(artist);
        return song;
    }

    private Artist createArtist(String artistName) {
        Artist artist = new Artist();
        artist.setName(artistName);
        return artist;
    }
}
