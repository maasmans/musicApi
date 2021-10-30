package com.laszlo.musicApi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.laszlo.musicApi.model.Artist;
import com.laszlo.musicApi.model.Song;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import static com.laszlo.musicApi.model.JsonUtility.ARTISTSPATH;
import static com.laszlo.musicApi.model.JsonUtility.SONGSPATH;
import static com.laszlo.musicApi.model.JsonUtility.createPojo;
import static com.laszlo.musicApi.model.JsonUtility.parse;
import static com.laszlo.musicApi.model.JsonUtility.readFileAsString;

@Service
public class JsonService {

    private final SongService songService;
    private final ArtistService artistService;

    public JsonService(SongService songService, ArtistService artistService) {
        this.songService = songService;
        this.artistService = artistService;
    }

    private void addSongsToDB() throws Exception {
        String content = readFileAsString(SONGSPATH);
        JsonNode node = parse(content);
        Iterator<JsonNode> nodeIterator = node.iterator();
        while (nodeIterator.hasNext()) {
            JsonNode songNode = nodeIterator.next();
            Song song = createPojo(songNode, Song.class);
            songService.save(song);
        }
    }

    private void addArtistsToDB() throws Exception {
        String content = readFileAsString(ARTISTSPATH);
        JsonNode node = parse(content);
        Iterator<JsonNode> nodeIterator = node.iterator();
        while (nodeIterator.hasNext()) {
            JsonNode songNode = nodeIterator.next();
            Artist artist = createPojo(songNode, Artist.class);
            artistService.save(artist);
        }
    }
    public void fillDB() throws Exception {
        addSongsToDB();
        addArtistsToDB();
    }

    public void hello(){
        ArrayList<Artist> artists = new ArrayList<>();
        Artist artist = new Artist();
        artist.setName("nieuwe1");
        artists.add(artist);


        Artist artist1 = new Artist();
        artist.setName("nieuwe2");
        artists.add(artist1);

        artistService.saveAll(artists);
    }


}
