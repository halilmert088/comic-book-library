package com.example.MyApp;

import java.io.IOException;

import com.example.MyApp.Repository.ComicRepository;
import com.example.MyApp.WebScraper.Scrape;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
public class ComicApplication {
	@Autowired private ComicRepository comicRepository;

	@PostConstruct //To initialize the database on the very first startup
	public void initializeDatabase() throws IOException
	{
		Scrape scrape = new Scrape();

		if (comicRepository.count() == 0)
			scrape.updateDatabase(comicRepository);

		scrape = null;
	}

	public static void main(String[] args) {
		SpringApplication.run(ComicApplication.class, args);
	}


	//Scrapes the upcoming comics list every day at 10 AM GMT
	@Scheduled(cron = "0 10 * * *")
	public void updateDatabase() throws IOException
	{
		Scrape scrape = new Scrape();

		if (comicRepository.count() != 0)
			scrape.purgeDatabase(comicRepository); //Purges the current database if it's not null

		scrape.updateDatabase(comicRepository); //Updates the database with new releases
		scrape = null; //Empty the scrape object to better manage memory
	}
}
