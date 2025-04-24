package tn.esprit.football.Entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Spectator extends User{
    public void followTeam() {
    }

    public void followMatch() {
    }

    public void followCompetition() {
    }
}
