package util;

import com.github.javafaker.Faker;
import entity.Album;
import entity.Artist;
import entity.Chart;
import entity.Ranking;
import repo.AlbumRepository;
import repo.ArtistRepository;
import repo.ChartRepository;
import repo.RankingRepository;

import java.util.Random;

public class DatabaseEntityGenerator {
    private ArtistRepository artistRepo=new ArtistRepository();
    private AlbumRepository albumRepo=new AlbumRepository();
    private ChartRepository chartRepo=new ChartRepository();
    private RankingRepository rankingRepo=new RankingRepository();

    private int numberOfArtists;
    private int albumsPerArtist;
    private int numberOfCharts;

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
            chartRepo.create(chart);
            numberOfCharts--;
        }
        while(numberOfArtists>0)
        {
            Artist artistToDb=new Artist(faker.artist().name(),faker.country().name());
            artistRepo.create(artistToDb);
            int id=artistRepo.findByName(artistToDb.getName()).get(0).getId();
            numberOfArtists--;
            int insertedAlbums=albumsPerArtist;
            int ranking=0;
            while(insertedAlbums>0)
            {
                Album albumToDb=new Album(faker.book().title(),faker.date().birthday().getYear(),id);
                albumRepo.create(albumToDb);
                if(rand.nextInt(2)==1)
                    rankingRepo.create(new Ranking(albumToDb.getId(),chartRepo.getRandomChart().getId(),ranking++));
                insertedAlbums--;
            }
        }
    }
}