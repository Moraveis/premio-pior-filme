package com.premiopiorfilme.premiopiorfilme;

import com.premiopiorfilme.premiopiorfilme.model.entities.Movie;
import com.premiopiorfilme.premiopiorfilme.model.entities.Producer;
import com.premiopiorfilme.premiopiorfilme.model.entities.Studio;
import com.premiopiorfilme.premiopiorfilme.model.repositories.MovieRepository;
import com.premiopiorfilme.premiopiorfilme.model.repositories.ProducerRepository;
import com.premiopiorfilme.premiopiorfilme.model.repositories.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

@SpringBootApplication
public class PremioPiorFilmeApplication implements CommandLineRunner {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private ProducerRepository producerRepository;

	@Autowired
	private StudioRepository studioRepository;


	public static void main(String[] args) {
		SpringApplication.run(PremioPiorFilmeApplication.class, args);
	}

	@Override
	public void run(String... args) {
		loadDataFromFile();
	}

	private void loadDataFromFile() {
		String fileName = "./src/main/resources/static/movielist.csv";

		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			stream.forEach(this::buildMovie);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void buildMovie(String line) {
		String[] record = line.split(";");

		Movie movie = new Movie();
		movie.setYear(record[0]);
		movie.setTitle(record[1]);

		boolean isWinner = false;
		if(record.length > 4 && record[4].equalsIgnoreCase("yes")) {
			isWinner = true;
		}
		movie.setWinner(isWinner);

		Optional.ofNullable(record[2]).ifPresent(item -> {
			for(String name : item.split(", ")) {

				Studio studio = studioRepository.findByName(name).orElse(new Studio());

				if(studio.getId() == null){
					studio.setName(name);
					studio.setWinCount(0L);
				}

				Long countWins = studio.getWinCount();
				studio.setWinCount( (movie.getWinner() != null && movie.getWinner()) ? ++countWins : countWins);

				studioRepository.save(studio);

				movie.getStudios().add(studio);
			}
		});

		Optional.ofNullable(record[3]).ifPresent(item -> {
			for(String name : item.split(", ")) {

				Producer producer = producerRepository.findByProducer(name).orElse(new Producer());

				if(producer.getId() == null) {
					producer.setProducer(name);
					producerRepository.save(producer);
				}

				movie.getProducers().add(producer);
			}
		});

		movieRepository.save(movie);
	}
}

