package org.laborator;

public interface AlbumController {
    void create(String name,int artistId,int releaseYear);
    void create(Album album);
    Album findByArtist(int artistId);
    Album findByName(String name);
    int getAlbumId(Album album);
}
