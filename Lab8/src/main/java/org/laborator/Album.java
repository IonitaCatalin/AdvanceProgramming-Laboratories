package org.laborator;

import java.util.Objects;

public class Album {
    private int artistId;
    private String name;
    private int releaseYear;


    public Album(int artistId, String name, int releaseYear) {
        this.artistId = artistId;
        this.name = name;
        this.releaseYear = releaseYear;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
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

    public void setRelease_year(int release_year) {
        this.releaseYear = release_year;
    }

    @Override
    public String toString() {
        return "Album{" +
                "artistId=" + artistId +
                ", name='" + name + '\'' +
                ", releaseYear=" + releaseYear +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return artistId == album.artistId &&
                releaseYear == album.releaseYear &&
                Objects.equals(name, album.name);
    }


    @Override
    public int hashCode() {
        return Objects.hash(artistId, name, releaseYear);
    }

}
