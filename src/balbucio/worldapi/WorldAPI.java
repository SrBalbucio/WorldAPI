package balbucio.worldapi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

public class WorldAPI {
	
	/**
	 * Copia a pasta de um mundo para ser carregado depois
	 * 
	 * @param source Pasta origem
	 * @param target Pasta de destino
	 */
	 public static void copyWorldFolder(File source, File target){
	        try {
	            ArrayList<String> ignore = new ArrayList<String>(Arrays.asList("uid.dat", "session.dat"));
	            if(!ignore.contains(source.getName())) {
	                if(source.isDirectory()) {
	                    if(!target.exists())
	                        target.mkdirs();
	                    String files[] = source.list();
	                    for (String file : files) {
	                        File srcFile = new File(source, file);
	                        File destFile = new File(target, file);
	                        copyWorldFolder(srcFile, destFile);
	                    }
	                } else {
	                    InputStream in = new FileInputStream(source);
	                    OutputStream out = new FileOutputStream(target);
	                    byte[] buffer = new byte[1024];
	                    int length;
	                    while ((length = in.read(buffer)) > 0)
	                        out.write(buffer, 0, length);
	                    in.close();
	                    out.close();
	                }
	            }
	        } catch (IOException e) {
	        	e.printStackTrace();
	        }
	    }
	    
	 /**
	  * Cria um backup do Mundo para uma pasta privada do plugin
	  * 
	  * @param pluginName Nome do seu Plugin
	  * @param world Mundo a ser copiado
	  * @return retorna o backup folder
	  */
	    public static File copyBackup(String pluginName, World world) {
	    	File folder = new File("plugins/"+pluginName+"/worldBackups/"+world.getName());
	    	copyWorldFolder(world.getWorldFolder(), folder);
	    	return folder;
	    }

	    /**
	     * Carrega um mundo
	     * 
	     * @param worldName Nome da pasta do mundo
	     * @return retorna o mundo
	     */
	    public static World loadWorld(String worldName){
	        WorldCreator creator = new WorldCreator(worldName);
	        creator.generateStructures(false);
	        creator.environment(World.Environment.NORMAL);
	        creator.type(WorldType.FLAT);
	        return creator.createWorld();
	    }

}
