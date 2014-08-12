package blood.base;

import java.util.ArrayList;
import java.util.List;

import javolution.util.FastMap;
import l2s.gameserver.data.xml.holder.ArmorSetsHolder;
import l2s.gameserver.model.ArmorSet;
import l2s.gameserver.templates.item.ArmorTemplate.ArmorType;
import l2s.gameserver.templates.item.ItemGrade;
import l2s.commons.util.Rnd;

public class FPCArmorGrade
{
	public static class FPCArmorType 
	{
		private List<ArmorSet> _items = new ArrayList<ArmorSet>(); 

		public void addItem(ArmorSet armorSet)
		{
			_items.add(armorSet);
		}
		
		public void addItem(int item_id)
		{
			addItem(ArmorSetsHolder.getInstance().getArmorSets(item_id).get(0));
		}
		
		public ArmorSet getRandom()
		{
			return _items.get(Rnd.get(_items.size()));
		}
		
		public List<ArmorSet> getAll()
		{
			return _items;
		}
	}
	
	private FastMap<ArmorType, FPCArmorType> _map = new FastMap<ArmorType, FPCArmorType>(); 
	private List<Integer> _ring = new ArrayList<Integer>();
	private static FastMap<ItemGrade, FPCArmorGrade> _instances = new FastMap<ItemGrade, FPCArmorGrade>();  
	
	public static FPCArmorGrade getInstance(ItemGrade grade)
	{
		if(_instances.get(grade) == null)
		{
			_instances.put(grade, new FPCArmorGrade());
		}
		
		return _instances.get(grade);
	}
	
	private FPCArmorGrade()
	{
		
	}
	
	public FPCArmorType getType(ArmorType type)
	{
		if(_map.get(type) == null)
		{
			_map.put(type, new FPCArmorType());
		}
		return _map.get(type);
	}
	
	public FPCArmorType getType(String type)
	{
		for(ArmorType _type: ArmorType.VALUES)
		{
			if(_type.toString().equalsIgnoreCase(type))
				return getType(_type);
		}
		
		return null;
	}
	
	public void setRing(int item_id)
	{
		_ring.add(item_id);
	}
	
	public List<Integer> getRing()
	{
		return _ring;
	}
}
