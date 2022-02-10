package com.orang3i.voting;

import com.orang3i.commands.CommandBase;
import com.orang3i.RestartVoteMain;
import com.orang3i.iridium.IridiumColorAPI;
import dev.norska.uar.UltimateAutoRestart;
import dev.norska.uar.api.UARAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import static org.bukkit.Bukkit.getServer;

public class VotingLogic {

  public static int VOTEONE;
  public static double VOTETWO = 1;
  public static double VOTEE;


  public VotingLogic(RestartVoteMain plugin) {

    new CommandBase("restartvote", true) {

      @Override

      public boolean onCommand(CommandSender sender, String[] args) {

        double plo = getServer().getOnlinePlayers().size();
        FileConfiguration config = plugin.getConfig();

        int restartmode = config.getInt("settings.restartMode");
        double perc = config.getInt("settings.percentageOfVotesNeededToRestart");
        int vote_valuex = config.getInt("settings.numberOfVotesNeededToRestart");
        int delay = config.getInt("settings.delayTime");
        String rs1v = config.getString("mode_1.value");
        boolean rs1vb = config.getBoolean("mode_1.enabled");
        String rs1d = config.getString("mode_1_delay_reached.value");
        boolean rs1db = config.getBoolean("mode_1_delay_reached.enabled");
        String rs2v = config.getString("mode_2.value");
        boolean rs2vb = config.getBoolean("mode_2.enabled");
        String rs2d = config.getString("mode_2_delay_reached.value");
        boolean rs2db = config.getBoolean("mode_2_delay_reached.enabled");

        int vote_value = vote_valuex - 1;

        if (restartmode == 1) {

          if (VOTEONE < vote_value) {
            ++VOTEONE;

            if(rs1vb){
              Bukkit.broadcastMessage(IridiumColorAPI.process(rs1v));
            }
            else {

              Bukkit.broadcastMessage(IridiumColorAPI.process(plugin.getConfig().getString("prefix") + " " + "<SOLID:FFFFFF>someone has voted to restart" + " " + "now there are" + " " + " " + VOTEONE + " " + "votes" + " " + vote_valuex + " " + "required to restart"));
            }

          } else {

            for (Player player : Bukkit.getOnlinePlayers()) {

              double delaye = delay / 60;
if(rs1db){
  Bukkit.broadcastMessage(IridiumColorAPI.process(rs1v));
}else {
  player.sendMessage(IridiumColorAPI.process(plugin.getConfig().getString("prefix") + " " + "<SOLID:FFFFFF>number of votes to restart reached restarting the server....."));
}
              VOTEONE = 0;

              Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                @Override
                public void run() {
                    Bukkit.spigot().restart();
                }
              }, 40L);
            }

          }
        }

        if (restartmode == 2) {

          VOTEE = (VOTETWO / plo) * 100;

          if (VOTEE < perc) {
            ++VOTETWO;
            for (Player player : Bukkit.getOnlinePlayers()) {

              double perce = Math.round(VOTEE);
if(rs2vb){
  Bukkit.broadcastMessage(IridiumColorAPI.process(rs2v));
}else {
  player.sendMessage(IridiumColorAPI.process(plugin.getConfig().getString("prefix") + " " + "<SOLID:FFFFFF>someone has voted to restart" + " " + "now there are" + " " + perce + "%" + " " + "votes" + " " + perc + "%" + " " + "required to restart"));
}
            }
          }

        }
        if (VOTEE >= perc) {

          for (Player player : Bukkit.getOnlinePlayers()) {

            double delaye = delay / 60;

if (rs2db) {
  player.sendMessage(IridiumColorAPI.process(rs2d));
} else {
  player.sendMessage(IridiumColorAPI.process(plugin.getConfig().getString("prefix") + " " + "<SOLID:FFFFFF>percentage  of votes to restart reached delaying restart by" + " " + delaye + " " + "minutes"));
  ;
}

          }
          Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

            @Override
            public void run() {
                Bukkit.spigot().restart();
            }
          }, 40L);

        }
        return true;
      }
      @Override

      public String getUsage() {

        return "/prefix <user> <prefix>";
      }

    }.enableDelay(999999999).setPermission("voterestart.vote");
  }
}