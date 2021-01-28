package org.laborator;

import com.github.javafaker.Faker;

import java.util.Random;

public class DatabaseEntityGenerator {
    ArtistController artistController=new ArtistControllerImpl();
    AlbumController albumController=new AlbumControllerImpl();
    ChartController chartController=new ChartControllerImpl();

    int numberOfArtists;
    int albumsPerArtist;
    int numberOfCharts;

    public DatabaseEntityGenerator(int numberOfArtists, int albumsPerArtist, int numberOfCharts) {
        this.numberOfArtists = numberOfArtists;
        this.albumsPerArtist = albumsPerArtist;
        this.numberOfCharts = numberOfCharts;
    }

    public void fill()
    {
        Random rand=new Random();
        Faker faker=new Faker();
        while(numberOfCharts>0)
        {
            Chart chart=new Chart(faker.music().genre());
            chartController.create(chart);
            numberOfCharts--;
        }
        while(numberOfArtists>0)
        {
            Artist artistToDb=new Artist(faker.artist().name().toString(),faker.country().name());
            artistController.create(artistToDb);
            int id=artistController.getArtistId(artistToDb);
            numberOfArtists--;
            int insertedAlbums=albumsPerArtist;
            while(insertedAlbums>0)
            {
                Album albumToDb=new Album(id,faker.book().title(),faker.date().birthday().getYear());
                albumController.create(albumToDb);
                if(rand.nextInt(2)==1)
                    chartController.addAlbumToChart(albumToDb,chartController.getRandomChart());
                insertedAlbums--;
            }
        }
    }
}
