package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="CHART_ALBUMS")
public class Ranking {
    @Id
    @Column(name="ID")
    @GeneratedValue(generator = "incremental")
    @GenericGenerator(name="incrementator",strategy="increment")
    private int id;
    @Column(name="RANKING")
    private int ranking;
    @Column(name="ALBUM_ID")
    private int albumId;
    @Column(name="CHART_ID")
    private int chartId;

    public Ranking() {
        this.ranking=0;
        this.albumId=1;
        this.chartId=1;
    }
    public Ranking(int albumId, int chartId, int ranking)
    {
        this.albumId=albumId;
        this.chartId=chartId;
        this.ranking=ranking;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getChartId() {
        return chartId;
    }

    public void setChartId(int chartId) {
        this.chartId = chartId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ranking that = (Ranking) o;
        return id == that.id &&
                ranking == that.ranking &&
                albumId == that.albumId &&
                chartId == that.chartId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ranking, albumId, chartId);
    }
}
