package org.laborator;

import java.sql.SQLException;

public class App 
{
    public static void main( String[] args ) throws SQLException, ClassNotFoundException {
        System.out.println(testControllers()?"Testul s-a incheiat cu succes!":"Testul a esuat!");
       // DatabaseEntityGenerator dbFill=new DatabaseEntityGenerator(10,3,4);
       // ChartController chartController=new ChartControllerImpl();
       // dbFill.fill();
       // chartController.printArtistsRanking();


    }
    public static boolean testControllers()
    {
        ArtistController artistController=new ArtistControllerImpl();
        AlbumController albumController=new AlbumControllerImpl();

        Artist artist=new Artist("Post Malone","USA");
        artistController.create(artist.getName(),artist.getCountry());
        Artist artistFromDb=artistController.findByName(artist.getName());

        if(!artist.equals(artistFromDb)) {
            return false;
        }

        Album album=new Album(artistController.getArtistId(artistFromDb),"Stoney",2013);
        albumController.create(album.getName(),artistController.getArtistId(artistFromDb),album.getReleaseYear());
        Album albumFromDb=albumController.findByArtist(artistController.getArtistId(artistFromDb));
        if(!album.equals(albumFromDb)) {
            return false;
        }
        return true;

    }
}


