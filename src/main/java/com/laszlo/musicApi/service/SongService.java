package com.laszlo.musicApi.service;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import utility.JsonUtility;
import com.laszlo.musicApi.model.Song;
import com.laszlo.musicApi.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SongService {
    private final SongRepository songRepository;

    public SongService(SongRepository serviceRepository) {
        this.songRepository = serviceRepository;
    }

    public Collection<Song> findAll() {
        return songRepository.findAll();
    }

    public Song save(Song song) {
        return songRepository.save(song);
    }

    public void delete(Song song) {
        songRepository.delete(song);
    }

    public List<Song> songsByGenre(String genre) {
        List<Song> songList = songRepository.findAll();
        return songList.stream()
                .filter(song -> song.getGenre() != null)
                .filter(song -> song.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }

    public List<Song> songsByYear(int year) {
        return songRepository.findByYear(year);
    }

    //TODO: Put the bulkinsert logic in one place instead of two.
    public void bulkInsert() {
        Gson serialiser = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();
        Type targetClassType = new TypeToken<ArrayList<Song>>() {  }.getType();
        List<Song> toInsert = serialiser.fromJson(JsonUtility.readFileAsString(JsonUtility.SONGSPATH), targetClassType);
        if (toInsert.size() > 0) {
            toInsert.stream().forEach(x -> x.setId(null));
            songRepository.saveAll(toInsert);
        }
    }

    public void deleteById(Integer id) {
        songRepository.deleteById(id);
    }

    /**
     * Still questionable if you want to save the new song if it wasn't in the database yet.
     */
    public Song updateSong(Integer id, Song updatedSong) {
        Optional<Song> optionalDbSong = songRepository.findById(id);
        if(optionalDbSong.isPresent()){
            Song dbSong = optionalDbSong.get();
            if(updatedSong.getName() != null){
                dbSong.setName(updatedSong.getName());
            }
            if(updatedSong.getYear() != null){
                dbSong.setYear(updatedSong.getYear());
            }
            if(updatedSong.getArtist() != null){
                dbSong.setArtist(updatedSong.getArtist());
            }
            if(updatedSong.getShortname() != null){
                dbSong.setShortname(updatedSong.getShortname());
            }
            if(updatedSong.getBpm() != null){
                dbSong.setBpm(updatedSong.getBpm());
            }
            if(updatedSong.getDuration() != null){
                dbSong.setDuration(updatedSong.getDuration());
            }
            if(updatedSong.getGenre() != null){
                dbSong.setGenre(updatedSong.getGenre());
            }
            if(updatedSong.getSpotifyId() != null){
                dbSong.setSpotifyId(updatedSong.getSpotifyId());
            }
            if(updatedSong.getAlbum() != null){
                dbSong.setAlbum(updatedSong.getAlbum());
            }
            return songRepository.save(dbSong);
        }
        return songRepository.save(updatedSong);
    }
}
