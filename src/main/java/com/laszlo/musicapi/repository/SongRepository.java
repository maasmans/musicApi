package com.laszlo.musicapi.repository;

import com.laszlo.musicapi.model.Song;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SongRepository extends CrudRepository<Song,Integer> {
    List<Song> findAll();
    List<Song> findByYear(int year);
}
