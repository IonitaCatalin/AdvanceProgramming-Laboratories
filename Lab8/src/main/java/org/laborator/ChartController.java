package org.laborator;
import java.util.List;

public interface ChartController {
    void create(String name);
    void create(Chart chart);
    void addAlbumToChart(Album album,Chart chart);
    Chart findByName(String name);
    int getChartId(Chart chart);
    Chart getRandomChart();
    void printArtistsRanking();


}
