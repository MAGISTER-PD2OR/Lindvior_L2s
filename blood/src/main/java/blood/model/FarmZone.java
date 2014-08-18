package blood.model;

import java.util.ArrayList;
import java.util.List;

import l2s.commons.util.Rnd;
import l2s.gameserver.model.Player;
import l2s.gameserver.model.base.ClassId;
import l2s.gameserver.utils.Location;

public class FarmZone {
	
	public List<Location> _locations = new ArrayList<Location>();
	public int _min_level = 1;
	public int _max_level = 99;
	public boolean _is_party = false;
	public List<Integer> _class_ids = new ArrayList<Integer>();
	
	public FarmZone(int min_level, int max_level){
		_min_level = min_level;
		_max_level = max_level;
	}
	
	public void addLocation(Location loc){
		_locations.add(loc);
	}
	
	public void addClass(int class_id){
		_class_ids.add(class_id);
	}
	
	public Location getRndLocation(){
		return _locations.get(Rnd.get(_locations.size()));
	}
	
	public boolean checkLevel(int level){
		System.out.println("check level:"+level+" min:"+_min_level+" max:"+_max_level);
		return _min_level <= level && level <= _max_level;
	}
	
	public boolean checkClass(int classId)
	{
		System.out.println("checklass size:"+_class_ids.size()+" class:"+classId);
		return _class_ids.size() == 0 || _class_ids.contains(classId);
	}
	
	public boolean isValid(Player player){
		return checkLevel(player.getLevel()) && checkClass(player.getClassId().getId());
	}

}
