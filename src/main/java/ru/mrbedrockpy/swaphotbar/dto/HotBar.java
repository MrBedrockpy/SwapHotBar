package ru.mrbedrockpy.swaphotbar.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ru.mrbedrockpy.bedrocklib.manager.ManagerItem;

@Getter
@Setter
@NoArgsConstructor
public class HotBar implements ManagerItem<String> {

    private String playerName;
    private ItemStack[] swapHotBar;

    public HotBar(Player player) {
        this.playerName = player.getName();
        this.swapHotBar = new ItemStack[9];
        for (int i = 0; i < swapHotBar.length; i++) {
            this.swapHotBar[i] = new ItemStack(Material.AIR);
        }
    }

    public void swap() {
        Player player = Bukkit.getPlayer(playerName);
        if (player == null) return;
        ItemStack[] hotBar = new ItemStack[9];
        for (int i = 0; i < swapHotBar.length; i++) hotBar[i] = player.getInventory().getItem(i);
        for (int i = 0; i < swapHotBar.length; i++) player.getInventory().setItem(i, this.swapHotBar[i]);
        this.swapHotBar = hotBar;
    }

    @Override
    public String getId() {
        return playerName;
    }
}
