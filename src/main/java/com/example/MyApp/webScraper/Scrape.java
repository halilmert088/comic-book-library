package com.example.MyApp.webScraper;

import com.example.MyApp.entity.Comic;
import com.example.MyApp.repository.ComicRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Scrape {
    private final ComicRepository comicRepository;

    //maybe make the lists static?
    private List<String> dateList = new ArrayList<String>();
    private List<String> publisherList = new ArrayList<String>();
    private List<String> titleList = new ArrayList<String>();
    private List<String> coverList = new ArrayList<String>();

    Elements titles;
    Elements dates;
    Elements publishers;
    Elements covers;

    //Get the necessary values from each element and store them in separate string lists
    public void initializeLists() throws IOException {
        scrape();
        for(Element title : this.titles)
        {
            titleList.add(title.text());
        }

        for(Element date : this.dates)
        {
            dateList.add(date.child(0).text());
        }

        for (Element publisher : this.publishers)
        {
            publisherList.add(publisher.text());
        }

        for (Element cover : this.covers)
        {
            coverList.add(cover.attr("data-src"));
        }

    }

    //Clear every entry in the database
    public void purgeDatabase()
    {
        comicRepository.deleteAll();
        assert(comicRepository.count()) == 0; //To check if the database has been purged correctly
    }

    //Update the database with the new release entries
    public void updateDatabase() throws IOException {
        List<Comic> comicList = new ArrayList<Comic>();

        scrape();
        for(int i = 0; i < titleList.size(); i++)
        {
            Comic comic = new Comic();

            comic.setTitle(titleList.get(i));
            comic.setRelease_date(dateList.get(i));
            comic.setPublisher(publisherList.get(i));
            comic.setImage_url(coverList.get(i));

            comicList.add(comic); // UserList created to be able to sort the comics as we wish later
        }

        for(int i = 0; i < comicList.size(); i++)
        {
            comicRepository.save(comicList.get(i));
        }
    }

    //Web scrape
    public void scrape() throws IOException
    {
        Document url = Jsoup.connect("https://www.comics.org/on_sale_weekly//").get();

        Elements table = url.select("table.sortable_listing").select("tr");
        Elements e = table.select("td");
        System.out.println(e.get(1));

    }
}
