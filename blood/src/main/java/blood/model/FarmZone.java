package blood.model;

import java.util.ArrayList;
import java.util.List;

import l2s.commons.util.Rnd;
import l2s.gameserver.utils.Location;

public class FarmZone {
	
	public List<Location> _locations = new ArrayList<Location>();
	public int _min_level = 1;
	public int _max_level = 99;
	
	public FarmZone(int min_level, int max_level){
		_min_level = min_level;
		_max_level = max_level;
	}
	
	public void addLocation(Location loc){
		_locations.add(loc);
	}
	
	public Location getRndLocation(){
		return _locations.get(Rnd.get(_locations.size()));
	}
	
	public boolean isValid(int level){
		return _min_level <= level && level <= _max_level;
	}

}
