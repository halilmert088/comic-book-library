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

    //List to store the raw string data we get from the database
    List<String> data = new ArrayList<>();

    //Scrape the database for relevant data and store the values in a string list
    public void scrape() throws IOException
    {
        //Sayfa sayfa olunca diğer sayfalara da bakabilecek bir impl lazım

        //Şu an sitede ikinci bir sayfa olmadığı için impl denesem bile test edemem, ikinci bir sayfa eklenince buraya
        //yeniden bakacağım
        Document url = Jsoup.connect("https://www.comics.org/on_sale_weekly//").get();

        Elements table = url.select("table.sortable_listing").select("tr");

        for (Element e : table.select("td"))
        {
            data.add(e.text());
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

        List<Comic> tmp = new ArrayList<>();

        //Set a comic entity list with the raw text data
        for(int i = 0; i < data.size(); i = i + 4)
        {
            Comic temp = new Comic();

            temp.setPublisher(data.get(i));
            temp.setTitle(data.get(i+1));

            //Mostly for cosmetics, I thought NA would look fancier than just a dash
            if(data.get(i+2).equals("—"))
                temp.setPublication_date("NA");
            else
                temp.setPublication_date(data.get(i+2));

            temp.setRelease_date(data.get(i+3));

            tmp.add(temp);

            temp = null;
        }

        //Saves the entity list in the database
        for(int i=0; i < tmp.size(); i++)
        {
            comicRepository.save(tmp.get(i));
        }
    }

}
