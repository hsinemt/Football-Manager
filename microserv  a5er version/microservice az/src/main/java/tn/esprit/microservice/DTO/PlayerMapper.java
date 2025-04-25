package tn.esprit.microservice.DTO;
import tn.esprit.microservice.entity.Player;
import tn.esprit.microservice.DTO.PlayerDTO;


public class PlayerMapper {
    public static Player toEntity(PlayerDTO playerDTO) {
        Player player = new Player();
        player.setId(playerDTO.getId());
        player.setNom(playerDTO.getNom());
        player.setPoste(playerDTO.getPoste());
        player.setAge(playerDTO.getAge());
        player.setNationalite(playerDTO.getNationalite());
        player.setButs(playerDTO.getButs());
        player.setPassesDecisives(playerDTO.getPassesDecisives());
        player.setCartonsJaunes(playerDTO.getCartonsJaunes());
        player.setCartonsRouges(playerDTO.getCartonsRouges());
        player.setMatchsJoues(playerDTO.getMatchsJoues());
        player.setEquipeId(playerDTO.getEquipeId());
        return player;
    }

    public static PlayerDTO toDTO(Player player) {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId(player.getId());
        playerDTO.setNom(player.getNom());
        playerDTO.setPoste(player.getPoste());
        playerDTO.setAge(player.getAge());
        playerDTO.setNationalite(player.getNationalite());
        playerDTO.setButs(player.getButs());
        playerDTO.setPassesDecisives(player.getPassesDecisives());
        playerDTO.setCartonsJaunes(player.getCartonsJaunes());
        playerDTO.setCartonsRouges(player.getCartonsRouges());
        playerDTO.setMatchsJoues(player.getMatchsJoues());
        playerDTO.setEquipeId(player.getEquipeId());
        return playerDTO;
    }}
