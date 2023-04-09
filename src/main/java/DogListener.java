import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTameEvent;


public class DogListener {
    @EventHandler
    public void onDeath(EntityDeathEvent event) {

    }
    @EventHandler public void onTame(EntityTameEvent event) {
        Entity entity = event.getEntity();
        World world = entity.getWorld();
        Player player = (Player) event.getOwner();
        String uuid = String.valueOf(player.getUniqueId());
        DogOwners.addDog(player, entity);
    }
}
