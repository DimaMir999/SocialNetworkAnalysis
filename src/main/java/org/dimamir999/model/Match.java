package org.dimamir999.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class Match {

    @JsonUnwrapped
    private Tournament tournament;

    @JsonUnwrapped(suffix = "1")
    private TennisPlayer player1;

    @JsonUnwrapped(suffix = "2")
    private TennisPlayer player2;

    public TennisPlayer getPlayer1() {
        return player1;
    }

    public void setPlayer1(TennisPlayer player1) {
        this.player1 = player1;
    }

    public TennisPlayer getPlayer2() {
        return player2;
    }

    public void setPlayer2(TennisPlayer player2) {
        this.player2 = player2;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Match match = (Match) o;

        if (!tournament.equals(match.tournament)) return false;
        if (!player1.equals(match.player1)) return false;
        return player2.equals(match.player2);
    }

    @Override
    public int hashCode() {
        int result = tournament.hashCode();
        result = 31 * result + player1.hashCode();
        result = 31 * result + player2.hashCode();
        return result;
    }
}
