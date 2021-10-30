package com.laszlo.musicApi;

import com.laszlo.musicApi.model.Artist;
import com.laszlo.musicApi.repository.ArtistRepository;
import com.laszlo.musicApi.repository.SongRepository;
import com.laszlo.musicApi.service.JsonService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;

@SpringBootApplication
public class MusicApiApplication {

	public static void main(String[] args) throws Exception {
		//SpringApplication.run(MusicApiApplication.class, args);
		ApplicationContext applicationContext = SpringApplication.run(MusicApiApplication.class, args);
	//	JsonService service = applicationContext.getBean(JsonService.class);

//		ArtistRepository artistRepository = applicationContext.getBean(ArtistRepository.class);
//		ArrayList<Artist> artists = new ArrayList<>();
//		Artist artist = new Artist();
//		artist.setName("nieuwe6");
//		artists.add(artist);
//
//
//		Artist artist1 = new Artist();
//		artist.setName("nieuwe7");
//		artists.add(artist1);
//
//		artistRepository.save(artist);
//		artistRepository.save(artist1);
//		//artistRepository.saveAll(artists);
//
//		//service.hello();
//		//service.fillDB();
	}
}
