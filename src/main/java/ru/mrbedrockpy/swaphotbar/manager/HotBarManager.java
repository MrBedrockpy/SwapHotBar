package ru.mrbedrockpy.swaphotbar.manager;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import ru.mrbedrockpy.bedrocklib.db.DataTable;
import ru.mrbedrockpy.bedrocklib.manager.SetManager;
import ru.mrbedrockpy.swaphotbar.Plugin;
import ru.mrbedrockpy.swaphotbar.dto.HotBar;

import java.util.HashSet;
import java.util.Set;

public class HotBarManager extends SetManager<Plugin, HotBar, String> {

    private final Set<String> sneakingPlayers = new HashSet<>();

    public HotBarManager(Plugin plugin) {
        super(plugin);
        load();
    }

    @EventHandler
    public void onNewPlayer(PlayerJoinEvent event) {
        if (getById(event.getPlayer().getName()) == null) register(new HotBar(event.getPlayer()));
    }

    @EventHandler
    public void onShiftPress(PlayerToggleSneakEvent event) {
        String playerName = event.getPlayer().getName();
        if (event.isSneaking()) {
            sneakingPlayers.add(playerName);
        } else {
            sneakingPlayers.remove(playerName);
        }
    }

    @EventHandler
    public void onSwap(PlayerSwapHandItemsEvent event) {
        if (sneakingPlayers.contains(event.getPlayer().getName())) {
            getById(event.getPlayer().getName()).swap();
            event.setCancelled(true);
        }
    }

    public void load() {
        DataTable<Plugin, HotBar, String> table = getPlugin().getDataBase().getTable(HotBar.class);
        if (table == null) return;
        set.clear();
        registerAll(table.getDtos());
    }

    public void save() {
        DataTable<Plugin, HotBar, String> table = getPlugin().getDataBase().createTableIfNotExists(HotBar.class);
        table.getDtos().clear();
        table.registerAll(set);
    }
}
