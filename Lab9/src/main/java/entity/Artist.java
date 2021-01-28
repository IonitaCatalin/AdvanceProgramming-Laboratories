package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@javax.persistence.Table(name = "Artists")
@NamedQueries({
        @NamedQuery(name="Artist.findByName",query="SELECT artist FROM Artist artist WHERE artist.name=:name")
})
public class Artist {
    @Id
    @Column(name="ID")
    @GeneratedValue(generator = "incremental")
    @GenericGenerator(name="incrementator",strategy="increment")
    private int id;
    @Column(name="NAME")
    private String name;
    @Column(name="COUNTRY")
    private String country;

    public Artist(String name,String country)
    {
        this.country=country;
        this.name=name;
    }
    public Artist()
    {
        this.name="UNKNOWN";
        this.country="UNKNOWN";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return id == artist.id &&
                Objects.equals(name, artist.name) &&
                Objects.equals(country, artist.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country);
    }
}
