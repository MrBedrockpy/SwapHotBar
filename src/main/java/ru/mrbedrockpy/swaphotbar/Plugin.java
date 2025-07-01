package ru.mrbedrockpy.swaphotbar;

import lombok.Getter;
import ru.mrbedrockpy.bedrocklib.BedrockPlugin;
import ru.mrbedrockpy.bedrocklib.db.DataBase;
import ru.mrbedrockpy.swaphotbar.manager.HotBarManager;

import java.io.File;

@Getter
public final class Plugin extends BedrockPlugin {

    private DataBase<Plugin> dataBase;

    private HotBarManager hotBarManager;

    @Override
    protected void registerConfigs() {
        this.dataBase = new DataBase<>(this, new File(this.getDataFolder(), "data.bd"), new SerializeConfig(this));
    }

    @Override
    protected void registerManagers() {
        this.hotBarManager = new HotBarManager(this);
    }

    @Override
    protected void saveManagers() {
        this.hotBarManager.save();
    }

    @Override
    protected void saveConfigs() {
        this.dataBase.save();
    }
}
