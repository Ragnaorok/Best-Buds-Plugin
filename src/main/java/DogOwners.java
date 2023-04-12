import org.bukkit.entity.Player;

import java.io.Serializable;

public class DogOwners implements Serializable {
    private String[] pets;
    private transient Player player;

    public DogOwners(){

    }
    public DogOwners(Player player) {
        String uuid = player.getUniqueId().toString();
    }
    public DogOwners(Player player, String[] pets) {
        this.player = player;
        this.pets = pets;

    }
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }


    public String getUUID() {
        return player.getUniqueId().toString();
    }
    public String[] getPets() {
        return pets;
    }

    public void setPets(String[] pets) {
        this.pets = pets;
    }


}
