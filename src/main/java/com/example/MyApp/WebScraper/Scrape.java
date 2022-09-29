package com.example.MyApp.WebScraper;

import java.io.IOException;
import java.util.*;


import com.example.MyApp.Entity.Comic;
import com.example.MyApp.Repository.ComicRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;


public class Scrape {
    @Autowired private ComicRepository comicRepository;

    List<String> dateList = new ArrayList<String>();
    List<String> publisherList = new ArrayList<String>();
    List<String> allPublishers = new ArrayList<String>();
    List<String> titleList = new ArrayList<String>();
    List<String> coverList = new ArrayList<String>();

    Elements titles;
    Elements dates;
    Elements publishers;
    Elements covers;

    //Scrapes the data from leagueofcomicgeeks.com and initializes the lists with the data when the instance is created
    public Scrape() throws IOException {
        Document newReleases = Jsoup.connect("https://leagueofcomicgeeks.com/comics/new-comics").get();

        this.titles = newReleases.select("li.issue").select("div.title");
        this.dates = newReleases.select("li.issue").select("div.details");
        this.publishers = newReleases.select("li.issue").select("div.publisher");
        this.covers = newReleases.select("li.issue").select("div.cover").select("img");

        initializeLists(this.titles, this.dates, this.publishers, this.covers);
    }

    //Get the necessary values from each element and store them in separate spring lists
    private void initializeLists(Elements titles, Elements dates, Elements publishers, Elements covers)
    {
        for(Element title : titles)
        {
            titleList.add(title.text());
        }

        for(Element date : dates)
        {
            dateList.add(date.child(0).text());
        }

        for (Element publisher : publishers)
        {
            publisherList.add(publisher.text());
        }

        for (Element cover : covers)
        {
            coverList.add(cover.attr("data-src"));
        }

    }

    //Clear every entry in the database
    public void purgeDatabase(ComicRepository comicRepository)
    {
        comicRepository.deleteAll();
        assert(comicRepository.count()) == 0; //To check if the database has been purged correctly
    }

    //Update the database with the new release entries
    public void updateDatabase(ComicRepository comicRepository)
    {
        List<Comic> comicList = new ArrayList<Comic>();

        for(int i = 0; i < titleList.size(); i++)
        {
            Comic comic = new Comic();

            comic.setTitle(titleList.get(i));
            comic.setRelease_date(dateList.get(i));
            comic.setPublisher(publisherList.get(i));
            comic.setImage_url(coverList.get(i));

            comicList.add(comic); // UserList created to be able to sort the comics as we wish later
            comic = null; // to better manage memory
        }

        for(int i = 0; i < comicList.size(); i++)
        {
            comicRepository.save(comicList.get(i));
        }
    }
}
