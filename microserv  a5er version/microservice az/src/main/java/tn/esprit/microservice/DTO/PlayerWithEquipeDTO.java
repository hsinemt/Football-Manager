package tn.esprit.microservice.DTO;

public class PlayerWithEquipeDTO {
    private PlayerDTO player;
    private EquipeDTO equipe;

    public PlayerDTO getPlayer() {
        return player;
    }

    public void setPlayer(PlayerDTO player) {
        this.player = player;
    }

    public PlayerWithEquipeDTO(PlayerDTO player, EquipeDTO equipe) {
        this.player = player;
        this.equipe = equipe;
    }

    public EquipeDTO getEquipe() {
        return equipe;
    }

    public void setEquipe(EquipeDTO equipe) {
        this.equipe = equipe;
    }
}
