package com.example.MyApp;

import java.io.IOException;

import com.example.MyApp.repository.ComicRepository;
import com.example.MyApp.webScraper.Scrape;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ComicApplication {
	private final ComicRepository comicRepository;
	private final Scrape scrape;

	@PostConstruct //To initialize the database on the very first startup
	public void initializeDatabase() throws IOException
	{
		scrape.scrape();
		if(comicRepository.count() == 0)
		{
			scrape.updateDatabase();
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(ComicApplication.class, args);
	}

	//Scrapes the upcoming comics list every day at 10 AM GMT (and apparently doesn't work)
	@Scheduled(cron = "0 10 * * *")
	public void updateDatabase() throws IOException
	{
		if (comicRepository.count() != 0)
		{
			scrape.purgeDatabase(); //Purges the current database if it's not empty
			System.out.println("Database purged.");
		}

		scrape.scrape();
		scrape.updateDatabase(); //Updates the database with new releases
		System.out.println("Database updated.");
	}
}
