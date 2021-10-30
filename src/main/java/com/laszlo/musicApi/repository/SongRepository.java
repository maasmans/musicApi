package com.laszlo.musicApi.repository;

import com.laszlo.musicApi.model.Song;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SongRepository extends CrudRepository<Song,Integer> {
    List<Song> findAll();
    List<Song> findByYear(int year);
}
