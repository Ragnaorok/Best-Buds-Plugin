import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class LoginListener implements Listener, Serializable{

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws IOException { // adds/loads player to a global Hashmap when they join
        Player player = event.getPlayer();
        File file = new File(player.getUniqueId()+ "pets" + ".dat");

        if (!file.exists()) {  // case that file doesn't exist, creates a new DogOwners object for new player and pushes to global hashmap
            DogOwners owner = new DogOwners(player, new String[]{});
            System.out.println("Player id when joining: " + player.getUniqueId());
            Constant.DOG_OWNERS_HASH_MAP.put(player.getUniqueId().toString(), owner);
            System.out.println("Player Map Size: " + Constant.DOG_OWNERS_HASH_MAP.size());
        } else {
            FileInputStream fis = new FileInputStream(file);
            GZIPInputStream gzip = new GZIPInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(gzip);

            try (ObjectInputStream input = ois) {
                DogOwners owner = (DogOwners) input.readObject();
                owner.setPlayer(player);
                Constant.DOG_OWNERS_HASH_MAP.put(player.getUniqueId().toString(), owner);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) throws IOException { // saves a player.dat when they exit the server
        Player player = event.getPlayer();
        String uuid = player.getUniqueId().toString();
        File file = new File(player.getUniqueId() + "pets" + ".dat");

        System.out.println("Actual player info: " + player.getUniqueId());

        System.out.println("Map contents: " + Constant.DOG_OWNERS_HASH_MAP.size());


        if (!file.exists()) {  // case that file doesn't exist, creates a new file
            file.createNewFile();
        }

        // if (Constant.DOG_OWNERS_HASH_MAP.containsKey(uuid)) {
        try (ObjectOutputStream output = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(file)))) {
            DogOwners owner = Constant.DOG_OWNERS_HASH_MAP.get(uuid);

            System.out.println("Player info: " + owner.getUUID());

            output.writeObject(owner);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
