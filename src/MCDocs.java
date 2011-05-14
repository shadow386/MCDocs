import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MCDocs extends Plugin
{
  static final MCDocsListener listener = new MCDocsListener();
  private String name = "MCDocs";
  private String version = "3.3";
  private String dataFolder = "plugins/MCDocs/";
  public static Logger log;
  public String motd = dataFolder+"motd.txt";
  public String rules = dataFolder+"rules.txt";
  public static PropertiesFile mcConf;
  public static boolean McMotd = true;

  public void addCustomCommands()
  {
    etc.getInstance().addCommand("/motd", "- shows the server motd");
    etc.getInstance().addCommand("/rules", "- shows the server rules");
    etc.getInstance().addCommand("/docs", "- shows the server documents");
    etc.getInstance().addCommand("/mcdocs", "- displays MCDocs version");
    etc.getInstance().addCommand("/adddoc", "- adds a new document");
  }
  public void removeCustomCommands() {
	etc.getInstance().removeCommand("/adddoc");  
	etc.getInstance().removeCommand("/motd");
    etc.getInstance().removeCommand("/rules");
    etc.getInstance().removeCommand("/docs");
    etc.getInstance().removeCommand("/mcdocs");
  }

  public void enable()
  {
	new File(dataFolder).mkdir();
	etc.getInstance().removeCommand("/motd");
    addCustomCommands();
    MCDocs.log = Logger.getLogger("Minecraft");
    MCDocs.log.log(Level.INFO, this.name + " plugin v" + this.version + " enabled");
  }

  public void disable() {
    removeCustomCommands();
    etc.getInstance().addCommand("/motd", "- shows the server motd");
    log.info(this.name + " plugin v" + this.version + " disabled");
  }

  public void initialize()
  {
    etc.getLoader().addListener(PluginLoader.Hook.LOGIN, listener, this, PluginListener.Priority.MEDIUM);
    etc.getLoader().addListener(PluginLoader.Hook.COMMAND, listener, this, PluginListener.Priority.MEDIUM);
    MCDocs.mcConf = new PropertiesFile(dataFolder+"mcdocs.properties");
    if (!MCDocs.mcConf.keyExists("use-motd")) MCDocs.mcConf.setBoolean("use-motd", true);
    
    if (!new File(motd).exists()) {
      FileWriter writer = null;
      try {
        writer = new FileWriter(motd);
        writer.write("#For a list of colors, go here: http://wiki.canarymod.net/Colors\r\n");
        writer.write("#To use linebreaks, just press enter. For color, use this symbol: §\r\n");
        writer.write("This is a filler since this line does not work.");
        writer.write("Welcome to my server! Please type /help for commands.");
      } catch (Exception e) {
    	MCDocs.log.log(Level.SEVERE, "Exception while creating " + motd);
        try
        {
          if (writer != null)
            writer.close();
        }
        catch (IOException e1) {
        MCDocs.log.log(Level.SEVERE, "Exception while closing writer for " + motd, e1);
        }
      }
      finally
      {
        try
        {
          if (writer != null)
            writer.close();
        }
        catch (IOException e) {
        MCDocs.log.log(Level.SEVERE, "Exception while closing writer for " + motd, e);
        }
      }
    }
    if (!new File(rules).exists()) {
        FileWriter writer = null;
        try {
          writer = new FileWriter(rules);
          writer.write("#For a list of colors, go here: http://wiki.canarymod.net/Colors\r\n");
          writer.write("#To use linebreaks, just press enter. For color, use this symbol: §\r\n");
          writer.write("This is a filler since this line does not work.");
          writer.write("Rule 1. Write your rules.");
        } catch (Exception e) {
      	MCDocs.log.log(Level.SEVERE, "Exception while creating " + rules);
          try
          {
            if (writer != null)
              writer.close();
          }
          catch (IOException e1) {
          MCDocs.log.log(Level.SEVERE, "Exception while closing writer for " + rules, e1);
          }
        }
        finally
        {
          try
          {
            if (writer != null)
              writer.close();
          }
          catch (IOException e) {
          MCDocs.log.log(Level.SEVERE, "Exception while closing writer for " + rules, e);
          }
        }
      }
  }
  public String getDataFolder() {
	  return dataFolder;
  }
  public String getVersion() {
	  return version;
  }
  public String getName() {
	  return name;
  }
}