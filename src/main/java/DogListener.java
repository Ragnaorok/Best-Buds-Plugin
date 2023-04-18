import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.material.Dye;

import java.util.Objects;

import static org.bukkit.DyeColor.CYAN;
import static org.bukkit.DyeColor.GRAY;
import static org.bukkit.Material.LEAD;


public class DogListener {
    @EventHandler
    public void onDogDeath(EntityDeathEvent event) {
        //check if the entity that died is a dog
        if (event.getEntity() instanceof Wolf && ((Wolf) event.getEntity()).isTamed()) {
            Wolf dog = (Wolf) event.getEntity();
            Player player = (Player) dog.getOwner();
            //teleport the dog to the player's bed using a try catch statement
            try {
                dog.teleport(player.getBedSpawnLocation());
                player.sendRawMessage("your dog is resting at home")
                dog.setHealth(dog.getMaxHealth());
                dog.setSitting(true);
                event.setCancelled(true);
            } catch (NullPointerException e) {
                player.sendMessage("Your dog's soul could not find its way home.");
            }
        }
    }
    @EventHandler
    public void OnInteract(PlayerInteractEntityEvent event) {
        //check if the entity is a player
        if (event.getRightClicked() instanceof Player) {
            Player player1 = event.getPlayer();
            Player player2 = (Player) event.getRightClicked();
            Location loc = player2.getOrigin();
            //check if the player is holding a leash
            if (player1.getInventory().getItemInMainHand().getType().equals(LEAD)) {
                //check if the player is holding a tamed Wolf
                for (Entity entity : loc.getNearbyLivingEntities(10, 10, 10)) {
                    if (entity instanceof Wolf) {
                        Wolf wolf = (Wolf) entity;
                        if (wolf.isLeashed() && wolf.getLeashHolder().getUniqueId().equals(player1.getUniqueId())) {
                            wolf.setOwner(player2);
                            player1.sendMessage("§aYou have successfully given your dog to " + player2.getName());
                            player2.sendMessage("§aYou have successfully received a dog from " + player1.getName());
                            //remove the UUID of the dog from player1's list of dogs in the hashmap
                            DogOwners owner = Constant.DOG_OWNERS_HASH_MAP.get(player1.getUniqueId().toString());
                            String[] pets = owner.getPets();
                            String[] newPets = new String[pets.length - 1];
                            for (int i = 0; i < pets.length; i++) {
                                if (pets[i] != wolf.getUniqueId().toString()) {
                                    newPets[i] = pets[i];
                                }
                            }
                            owner.setPets(newPets);
                        }
                    }
                }
            }
        }
    }
    @EventHandler
    public void onTame(EntityTameEvent event) {
        if (event.getEntity() instanceof Wolf) {
            Wolf wolf = (Wolf) event.getEntity();
            Player player = (Player) event.getOwner();
            DogOwners owner = Constant.DOG_OWNERS_HASH_MAP.get(player.getUniqueId().toString());
            String[] pets = owner.getPets();
            String[] newPets = new String[pets.length + 1];
            for (int i = 0; i < pets.length; i++) {
                newPets[i] = pets[i];
            }
            newPets[newPets.length - 1] = wolf.getUniqueId().toString();
            owner.setPets(newPets);
            Constant.DOG_OWNERS_HASH_MAP.put(player.getUniqueId().toString(), owner);
        }
    }
    @EventHandler
    public void OnCollarDye(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        //check if the entity is a dog
        if (event.getRightClicked() instanceof Wolf) {
            //cast the entity to a dog
            Wolf dog = (Wolf) event.getRightClicked();
            //check if the dog is tamed
            if (dog.isTamed()) {
                //check if the dog is owned by the player
                if (dog.getOwner().equals(player)) {
                        Dye dye = (Dye) player.getInventory().getItemInMainHand().getData();
                        //check the color of the dye
                        switch (dye.getColor()) {
                            case BLACK:
                                //set the collar color to black
                                dog.setCollarColor(DyeColor.BLACK);
                                //remove one dye from the player's inventory
                                player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
                                //send a message to the player
                                player.sendMessage("§aYou have successfully dyed your dog's collar black.");
                                break;
                            case BLUE:
                                //set the collar color to blue
                                dog.setCollarColor(DyeColor.BLUE);
                                //remove one dye from the player's inventory
                                player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
                                //send a message to the player
                                player.sendMessage("§aYou have successfully dyed your dog's collar blue.");
                                break;
                            case BROWN:
                                //set the collar color to brown
                                dog.setCollarColor(DyeColor.BROWN);
                                //remove one dye from the player's inventory
                                player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
                                //send a message to the player
                                player.sendMessage("§aYou have successfully dyed your dog's collar brown.");
                                break;
                            case CYAN:
                                //set the collar color to cyan
                                dog.setCollarColor(CYAN);
                                        //remove one dye from the player's inventory
                                        player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
                                //send a message to the player
                                player.sendMessage("§aYou have successfully dyed your dog's collar cyan.");
                                break;
                            case GRAY:
                                //set the collar color to gray
                                dog.setCollarColor(GRAY);
                                //remove one dye from the player's inventory
                                player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
                                //send a message to the player
                                player.sendMessage("§aYou have successfully dyed your dog's collar gray.");
                                break;
                            case GREEN:
                                //set the collar color to green
                                dog.setCollarColor(DyeColor.GREEN);
                                //remove one dye from the player's inventory
                                player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
                                //send a message to the player
                                player.sendMessage("You have successfully dyed your dog's collar green.");
                                break;
                            case LIGHT_BLUE:
                                //set the collar color to light blue
                                dog.setCollarColor(DyeColor.LIGHT_BLUE);
                                //remove one dye from the player's inventory
                                player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
                                //send a message to the player
                                player.sendMessage("You have successfully dyed your dog's collar light blue.");
                                break;
                            case LIME:
                                //set the collar color to lime
                                dog.setCollarColor(DyeColor.LIME);
                                //remove one dye from the player's inventory
                                player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
                                //send a message to the player
                                player.sendMessage("You have successfully dyed your dog's collar lime.");
                                break;
                            case MAGENTA:
                                //set the collar color to magenta
                                dog.setCollarColor(DyeColor.MAGENTA);
                                //remove one dye from the player's inventory
                                player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
                                //send a message to the player
                                player.sendMessage("You have successfully dyed your dog's collar magenta.");
                                break;
                            case ORANGE:
                                //set the collar color to orange
                                dog.setCollarColor(DyeColor.ORANGE);
                                //remove one dye from the player's inventory
                                player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
                                //send a message to the player
                                player.sendMessage("You have successfully dyed your dog's collar orange.");
                                break;
                            case PINK:
                                //set the collar color to pink
                                dog.setCollarColor(DyeColor.PINK);
                                //remove one dye from the player's inventory
                                player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
                                //send a message to the player
                                player.sendMessage("You have successfully dyed your dog's collar pink.");
                                break;
                            case PURPLE:
                                //set the collar color to purple
                                dog.setCollarColor(DyeColor.PURPLE);
                                //remove one dye from the player's inventory
                                player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
                                //send a message to the player
                                player.sendMessage("You have successfully dyed your dog's collar purple.");
                                break;
                            case RED:
                                //set the collar color to red
                                dog.setCollarColor(DyeColor.RED);
                                //remove one dye from the player's inventory
                                player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
                                //send a message to the player
                                player.sendMessage("You have successfully dyed your dog's collar red.");
                                break;
                            case YELLOW:
                                //set the collar color to yellow
                                dog.setCollarColor(DyeColor.YELLOW);
                                //remove one dye from the player's inventory
                                player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
                                //send a message to the player
                                player.sendMessage("You have successfully dyed your dog's collar yellow.");
                                break;
                            case WHITE:
                                //set the collar color to white
                                dog.setCollarColor(DyeColor.WHITE);
                                //remove one dye from the player's inventory
                                player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
                                //send a message to the player
                                player.sendMessage("§aYou have successfully dyed your dog's collar white.");
                                break;
                            case LIGHT_GRAY:
                                //set the collar color to light gray
                                dog.setCollarColor(DyeColor.LIGHT_GRAY);
                                //remove one dye from the player's inventory
                                player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
                                //send a message to the player
                                player.sendMessage("You have successfully dyed your dog's collar light gray.");
                                break;
                            //end of switch statement
                        }
                    }
                }
            }
        }
    }

