package blood.data.holder;

import java.util.HashMap;
import java.util.HashSet;

import l2s.commons.data.xml.AbstractHolder;
import l2s.gameserver.model.base.ClassId;
import l2s.gameserver.model.base.ClassType;
import l2s.gameserver.model.base.ClassType2;
import l2s.gameserver.model.base.Race;
import l2s.gameserver.model.base.Sex;

public final class NamePatternHolder  extends AbstractHolder{
	
	/**
	 * Field _instance.
	 */
	private static final NamePatternHolder _instance = new NamePatternHolder();
	
	/**
	 * Field _lists.
	 */
	
	private final HashMap<String, HashSet<String>> _lists = new HashMap<String, HashSet<String>>(); 
	
	/**
	 * Method getInstance.
	 * @return LevelBonusHolder
	 */
	public static NamePatternHolder getInstance()
	{
		return _instance;
	}
	
	public HashSet<String> getCategory(String category)
	{
		return _lists.get(category); 
	}
	
	public void add(String category, HashSet<String> names)
	{
		_lists.put(category, names);
	}
	
	public static boolean checkName(String name, HashSet<String> patterns)
	{
		if(patterns == null || patterns.size() == 0)
			return false;
		
		String lowerName = name.toLowerCase();
		for(String pattern: patterns)
			if(lowerName.contains(pattern))
				return true;
		
		return false;
	}
	
	public static boolean checkName(String name, String category)
	{
		return checkName(name, getInstance().getCategory(category));
	}
	
	public static boolean checkName(String name, Sex sex)
	{
		return checkName(name, sex.toString());
	}
	
	public static boolean checkName(String name, Race race)
	{
		if(checkName(name, race.toString()))
			return true;
		
		for(ClassId classid: ClassId.VALUES)
			if(classid.isOfRace(race) && checkName(name, classid))
				return true;
		
		return false;
	}
	
	public static boolean checkName(String name, ClassType class_type)
	{
		return class_type != null && checkName(name, class_type.toString());
	}
	
	public static boolean checkName(String name, ClassType2 class_type)
	{
		return class_type != null && checkName(name, class_type.toString());
	}
	
	public static boolean checkName(String name, ClassId classid)
	{
		return checkName(name, classid.toString());
	}
	
	public static boolean checkChildrenClassName(String name, ClassId classid)
	{
		for(ClassId cid: ClassId.VALUES)
			if(classid.equalsOrChildOf(cid) && checkName(name, cid.toString()))
				return true;
		
		return false;
	}
	
	public static Race getRaceByName(String name)
	{
		// avoid darkelf/elf conflict name
		if(checkName(name, Race.DARKELF))
			return Race.DARKELF;
		
		for(Race race: Race.VALUES)
			if(checkName(name, race))
				return race;
		
		return null;
	}
	
	public static Sex getSexByName(String name)
	{
		for(Sex sex: Sex.VALUES)
			if(checkName(name, sex))
				return sex;
		
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return _lists.size();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		_lists.clear();
	}
}
