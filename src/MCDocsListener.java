import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;

public class MCDocsListener extends PluginListener
{
	MCDocs mcd = new MCDocs();
    String folderName = mcd.getDataFolder();
    String header = null;
  public boolean onCommand(Player player, String[] split)
  {
  	MCDocs.McMotd = MCDocs.mcConf.getBoolean("use-motd");
	if(MCDocs.McMotd){
		if ((split[0].equalsIgnoreCase("/motd")) && (player.canUseCommand("/motd")))
		{
		try {
			FileInputStream fis = new FileInputStream(folderName + "motd.txt");
			Scanner scanner = new Scanner(fis, "UTF-8");
			scanner.useDelimiter("\r\n");
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
			if ((line.startsWith("#"))) {
				line.equals("");
				continue;
			}
				player.sendMessage("งf" + scanner.next());
			}
				scanner.close();
				fis.close();
			} catch (FileNotFoundException e) {
				MCDocs.log.log(Level.WARNING, "Motd.txt does not exist.");
			} catch (IOException e) {
				MCDocs.log.log(Level.WARNING, "Couldn't load motd.txt");
			}
		return true;
		}
	}
    if ((split[0].equalsIgnoreCase("/rules")) && (player.canUseCommand("/rules")))
    {
    try {
        FileInputStream fis = new FileInputStream(folderName + "rules.txt");
        Scanner scanner = new Scanner(fis, "UTF-8");
        scanner.useDelimiter("\r\n");
        while (scanner.hasNext()) {
          String line = scanner.nextLine();
          if ((line.startsWith("#"))) {
        	  line.equals("");
              continue;
          }
          player.sendMessage("งf" + scanner.next());
        }
        scanner.close();
        fis.close();
      	} catch (FileNotFoundException e) {
    	  MCDocs.log.log(Level.WARNING, "rules.txt does not exist.");
        } catch (IOException e) {
        	MCDocs.log.log(Level.WARNING, "Couldn't load rules.txt");
        }
      return true;
    }
    if ((split[0].equalsIgnoreCase("/docs")) && (player.canUseCommand("/docs")))
    {
			try {
			FileInputStream fis = new FileInputStream(folderName + split[1]+".txt");
			Scanner scanner = new Scanner(fis, "UTF-8");
			scanner.useDelimiter("\r\n");
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
					if ((line.startsWith("#"))) {
						line.equals("");
				continue;
			}
				player.sendMessage("งf" + scanner.next());
			}
			scanner.close();
			fis.close();
			} catch (FileNotFoundException e) {
				MCDocs.log.log(Level.WARNING, split[1]+".txt does not exist.");
			} catch (IOException e) {
				MCDocs.log.log(Level.WARNING, "Couldn't load "+split[1]+".txt");
			}
      return true;
    }
    if ((split[0].equalsIgnoreCase("/mcdocs")) && (player.canUseCommand("/mcdocs")))
    {
      player.sendMessage("งe[MCDocs]ยง8"+mcd.getName()+": Plugin version "+mcd.getVersion());
      return true;
    }
    if ((split[0].equalsIgnoreCase("/add-doc")) && (player.canUseCommand("/add-doc")))
    {
    	String location = folderName+""+split[1]+".txt";
    	if (!new File(location).exists()) {
    		FileWriter writer = null;
    		try {
    			writer = new FileWriter(location);
    			writer.write("#Type your "+split[1]+" command here!\r\n");
    			writer.write("#For a list of colors, go here: http://wiki.canarymod.net/Colors\r\n");
    			writer.write("#To use linebreaks, just press enter. For color, use this symbol: ง\r\n");
    			writer.write("This is a filler since this line does not work.\r\n");
				writer.write(split[2]);
    		} catch (Exception e) {
    			MCDocs.log.log(Level.SEVERE, "Exception while creating " + location);
    			try
    			{
    				if (writer != null)
    					writer.close();
    			}
    			catch (IOException e1) {
    				MCDocs.log.log(Level.SEVERE, "Exception while closing writer for " + location, e1);
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
    				MCDocs.log.log(Level.SEVERE, "Exception while closing writer for " + location, e);
    			}
    		}
			player.sendMessage("งe[MCDocs]ยง8"+split[1]+" created!");		
    	} else {
		player.sendMessage("งe[MCDocs]ยง8"+split[1]+" already exists!");
		}
    	return true;
	}
	if ((split[0].equalsIgnoreCase("/del-doc")) && (player.canUseCommand("/del-doc")))
	{
    	String location = folderName+""+split[1]+".txt";
		File f = new File(location);
			if (!f.exists()) {
				player.sendMessage("งe[MCDocs]ยง8"+split[1]+" doens't exist!");
			} else if (!f.canWrite()) {
				player.sendMessage("งe[MCDocs]ยง8"+split[1]+" is unable to be deleted!");
			} else if (f.isDirectory()) {
				player.sendMessage("งe[MCDocs]ยง8"+split[1]+" is a directory!");
			} else {
				f.delete();
				player.sendMessage("งe[MCDocs] ยง8"+split[1]+" removed!");
			}
		return true;
	}
    return false;
  }
  public void onLogin(Player player) {
	MCDocs.McMotd = MCDocs.mcConf.getBoolean("use-motd");
	if(MCDocs.McMotd){
		try {
	        FileInputStream fis = new FileInputStream(folderName + "motd.txt");
	        Scanner scanner = new Scanner(fis, "UTF-8");
	        scanner.useDelimiter("\r\n");
	        while (scanner.hasNext()) {
	          String line = scanner.nextLine();
	          if ((line.startsWith("#"))) {
	        	  line.equals("");
	              continue;
	          }
	          player.sendMessage("งf" + scanner.next());
	        }
	        scanner.close();
	        fis.close();
	    } catch (FileNotFoundException e) {
	  	  MCDocs.log.log(Level.WARNING, "Motd.txt does not exist.");
	    } catch (IOException e) {
	      MCDocs.log.log(Level.WARNING, "Couldn't load motd.txt");
	    }
	}
  }
}