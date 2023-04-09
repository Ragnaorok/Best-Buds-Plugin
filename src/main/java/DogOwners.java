import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.io.Serializable;

public class DogOwners implements Serializable {
    private Entity[] pets;
    private transient Player player;

    public DogOwners(){

    }
    public DogOwners(Player player) {
        String uuid = player.getUniqueId().toString();
    }
    public DogOwners(Player player, Entity[] pets) {
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
    public Entity[] getPets() {
        return pets;
    }
    public static void addDog(Player player, Entity dog) {
        DogOwners dogOwners = Constant.DOG_OWNERS_HASH_MAP.get(player.getUniqueId().toString());
        Entity[] pets = dogOwners.getPets();
        Entity[] newPets = new Entity[pets.length + 1];
        for (int i = 0; i < pets.length; i++) {
            newPets[i] = pets[i];
        }
        newPets[newPets.length - 1] = dog;
        dogOwners.setPets(newPets);
        Constant.DOG_OWNERS_HASH_MAP.put(player.getUniqueId().toString(), dogOwners);
    }

    public void setPets(Entity[] pets) {
        this.pets = pets;
    }




}
