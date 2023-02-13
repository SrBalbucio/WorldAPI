package balbucio.worldapi;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.json.JSONObject;

public class LocationAPI {
	
	/**
	 * Crie um JSON de um location
	 * 
	 * @param loc
	 * @return
	 */
	public static JSONObject locationToJson(Location loc) {
		return new JSONObject().put("X", loc.getX()).put("Y", loc.getY()).put("Z", loc.getZ()).put("World", loc.getWorld());
	}
	
	/**
	 * Carrega um location de um JSON
	 * 
	 * @param data
	 * @return
	 */
	public static Location getLocationFromJSON(JSONObject data) {
		return new Location(Bukkit.getWorld(data.getString("World")), data.getDouble("x"), data.getDouble("Y"), data.getDouble("Z"));
	}

}
