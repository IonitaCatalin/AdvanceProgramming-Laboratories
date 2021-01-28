package org.laborator;

public interface ArtistController {
    void create(String name, String country);
    Artist findByName(String name);
    int getArtistId(Artist actor);
    void create(Artist artist);
}
