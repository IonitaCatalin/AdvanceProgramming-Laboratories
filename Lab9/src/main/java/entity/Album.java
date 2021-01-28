package entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.util.Objects;

@Entity
@javax.persistence.Table(name="Albums")
@NamedQueries({
        @NamedQuery(name="Album.findByName",query="SELECT album FROM Album album WHERE album.name=:name"),
        @NamedQuery(name="Album.findByArtist",query="SELECT album FROM Album album WHERE album.artistId=:id")
})
public class Album {
    @Id
    @Column(name="ID")
    @GeneratedValue(generator="incrementator")
    @GenericGenerator(name="incrementator",strategy="increment")
    private int id;
    @Column(name="NAME")
    private String name;
    @Column(name="RELEASE_YEAR")
    private int releaseYear;
    @Column(name="ARTIST_ID")
    private int artistId;

    public Album() {
        this.name="UNKNOWN";
        this.artistId=1;
        this.releaseYear=0000;
    }
    public Album(String name,int releaseYear,int artistId)
    {
        this.name=name;
        this.releaseYear=releaseYear;
        this.artistId=artistId;
    }

   public int getId()
   {
       return id;
   }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", releaseYear=" + releaseYear +
                ", artistId=" + artistId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return releaseYear == album.releaseYear &&
                artistId == album.artistId &&
                Objects.equals(id, album.id) &&
                Objects.equals(name, album.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, releaseYear, artistId);
    }
}
