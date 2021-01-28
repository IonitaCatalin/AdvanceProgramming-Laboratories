package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@javax.persistence.Table(name= "CHARTS")
@NamedQueries({
        @NamedQuery(name="Chart.findByName",query="SELECT chart FROM Chart chart WHERE chart.name=:name")
})
public class Chart {
    @Id
    @Column(name="ID")
    @GeneratedValue(generator = "incremental")
    @GenericGenerator(name="incrementator",strategy="increment")
    private int id;
    @Column(name="NAME")
    private String name;

    public Chart() {
        this.name="UNKNOWN";
    }
    public Chart(String name)
    {
        this.name=name;
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


}
