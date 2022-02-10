package com.orang3i;

import com.orang3i.iridium.IridiumColorAPI;

import com.orang3i.voting.VotingLogic;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public final class RestartVoteMain extends JavaPlugin {

  private static RestartVoteMain instance;

  public static int MAXR;

  @Override
  public void onEnable() {

    Logger.log(Logger.LogLevel.OUTLINE, "********************************************************************************");
    Logger.log(Logger.LogLevel.SUCCESS, IridiumColorAPI.process("<GRADIENT:9281fb>Thank you for using VoteRestart!</GRADIENT:eb93fc>"));
    Logger.log(Logger.LogLevel.OUTLINE, "********************************************************************************");

    instance = this;

    saveDefaultConfig();

    new VotingLogic(this);



  }

  @Override

  public void onDisable() {

  }

  public static RestartVoteMain getInstance() {

    return instance;
  }

}
