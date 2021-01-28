package app;

import entity.Album;
import entity.Artist;
import entity.Chart;
import entity.Ranking;
import repo.AlbumRepository;
import repo.ArtistRepository;
import repo.ChartRepository;
import repo.RankingRepository;
import util.DatabaseEntityGenerator;

public class AlbumManager {
    public static void main(String[] args)
    {
//        if(testAddAndSearchForArtist())
//            System.out.println("Functiile de add si search merg corespunzator");
//        else System.out.println("Functiile de add si search nu functioneaza");
//        if(testAddAndSearchForAlbum())
//            System.out.println("Functiile de add si search merg corespunzator");
//        else System.out.println("Functiile de add si search nu functioneaza");
        Artist firstArtist=new Artist("Bon Jovi","Germany");
        Artist secondArtist=new Artist("Johny Crash","USA");
        Artist thirdArtist=new Artist("Thousand Foot Krutch","Danmark");
        ArtistRepository artistRepository=new ArtistRepository();
        artistRepository.create(firstArtist);
        artistRepository.create(secondArtist);
        artistRepository.create(thirdArtist);

        Album firstAlbum=new Album("When the roses dies",1998,firstArtist.getId());
        Album secondAlbum=new Album("Unravel Roads",2012,firstArtist.getId());
        Album thirdAlbum=new Album("Death of a Bachelor",1996,secondArtist.getId());
        Album fourthAlbum=new Album("What am i ?",2020,secondArtist.getId());
        Album fifthAlbum=new Album("Son of Apocalypse",1998,thirdArtist.getId());

        AlbumRepository albumRepository=new AlbumRepository();
        albumRepository.create(firstAlbum);
        albumRepository.create(secondAlbum);
        albumRepository.create(thirdAlbum);
        albumRepository.create(fourthAlbum);
        albumRepository.create(fifthAlbum);

        Chart firstChart=new Chart("Best rock of all time");
        ChartRepository chartRepo=new ChartRepository();
        chartRepo.create(firstChart);
        int count=0;
        Ranking firstRank =new Ranking(firstAlbum.getId(),firstChart.getId(),count++);
        Ranking secondRank =new Ranking(fifthAlbum.getId(),firstChart.getId(),count++);
        Ranking thirdRank =new Ranking(thirdAlbum.getId(),firstChart.getId(),count++);

        RankingRepository rankRepo=new RankingRepository();
        rankRepo.create(firstRank);
        rankRepo.create(secondRank);
        rankRepo.create(thirdRank);


    }
    public static boolean testAddAndSearchForArtist()
    {
        Artist artist=new Artist("Thousand Foot Krutch","USA");
        ArtistRepository artistRepo=new ArtistRepository();
        artistRepo.create(artist);
        Artist artistFromDb=artistRepo.findById(artist.getId());
        if(artist.equals(artistFromDb))
            return true;
        else return false;
    }
    public static boolean testAddAndSearchForAlbum() {
        Artist artist = new Artist("Bon Jovvi", "Germany");
        AlbumRepository albumRepo = new AlbumRepository();
        ArtistRepository artistRepo = new ArtistRepository();
        artistRepo.create(artist);
        Album album = new Album("It's my life", 2005, artist.getId());
        albumRepo.create(album);
        Album albumFromDb = albumRepo.findById(album.getId());
        if (album.equals(albumFromDb))
            return true;
        else return false;
    }

}
