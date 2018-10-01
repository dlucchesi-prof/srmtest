package br.com.srmtest.testdaniel.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long                                        id;
    @Column(nullable = false)
    private String                                      name;
    @Column(nullable = false, name = "lm")
    private Double                                      limit;
    @Column(nullable = false)
    private Risk                                        risk;
    @Column(nullable = false)
    private Double                                      rate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLimit() {
        return limit;
    }

    public void setLimit(Double limit) {
        this.limit = limit;
    }

    public Risk getRisk() {
        return risk;
    }

    public void setRisk(Risk risk) {
        this.risk = risk;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return Objects.equals(getId(), client.getId()) &&
                Objects.equals(getName(), client.getName()) &&
                Objects.equals(getLimit(), client.getLimit()) &&
                getRisk() == client.getRisk() &&
                Objects.equals(getRate(), client.getRate());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), getLimit(), getRisk(), getRate());
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", limit=" + limit +
                ", risk=" + risk +
                ", rate=" + rate +
                '}';
    }
}
