package org.dimamir999.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tennis_player")
public class TennisPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(value = "player")
    private Long id;

    @Column(name = "name", length = 32)
    @JsonIgnore
    private String name;

    @Column(name = "surname", length = 32)
    @JsonIgnore
    private String surname;

    @Column(name = "atp")
    @JsonProperty(value = "atp")
    private Double atpRank;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAtpRank() {
        return atpRank;
    }

    public void setAtpRank(Double atpRank) {
        this.atpRank = atpRank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TennisPlayer player = (TennisPlayer) o;

        if (!name.equals(player.name)) return false;
        return surname.equals(player.surname);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + surname.hashCode();
        return result;
    }
}
