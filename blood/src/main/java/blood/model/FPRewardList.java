package blood.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import l2s.gameserver.model.Player;
import l2s.gameserver.model.items.ItemInstance;
import l2s.gameserver.templates.item.EtcItemTemplate.EtcItemType;
import l2s.gameserver.utils.ItemFunctions;
import blood.data.holder.FPItemHolder;

public class FPRewardList 
{
	public final static HashSet<Integer> _all_used_items 		= new HashSet<Integer>();
	public final static String PLAYER_VAR_SAVE 					= "_last_fpreward_list";
	
	public final HashSet<Integer> 			_class_ids 		= new HashSet<Integer>();
	public final HashSet<Integer> 			_remove_items 	= new HashSet<Integer>();
	public final HashMap<Integer, Integer> 	_reward_items 	= new HashMap<Integer, Integer>(); 
	
	public final boolean 					_is_mage;
	public final boolean 					_is_abstract;
	public final int						_id;
	public final int						_parent_id;
	public final int						_min_level;
	public final int						_max_level;
	public final int						_weight;
		
	public FPRewardList(int id, int min_level, int max_level, int parent_id, int weight, boolean is_abstract, boolean is_mage)
	{
		_id = id;
		_min_level = min_level;
		_max_level = max_level;
		_parent_id = parent_id;
		_weight = weight;
		_is_abstract = is_abstract;
		_is_mage = is_mage;
	}
	
	public int getId() {
		return _id;
	}
	
	public int getWeight() {
		return _weight;
	}
	
	public void addClass(int class_id){
		_class_ids.add(class_id);
	}
	
	public HashSet<Integer> getClasses()
	{
		FPRewardList parent = FPItemHolder.get(_parent_id);
		if(parent != null)
			_class_ids.addAll(parent.getClasses());
		return _class_ids;
	}
	
	public boolean hasClass(int class_id){
		return _class_ids.contains(class_id);
	}
	
	public void addItem(int item_id, int amount){
		_reward_items.put(item_id, amount);
		_all_used_items.add(item_id);
	}
	
	public boolean isValidLevel(int level)
	{
		return !_is_abstract && _min_level <= level && level <= _max_level;
	}
	
	public boolean isValidClass(int class_id)
	{
		return getClasses().contains(class_id);
	}
	
	public boolean isValidType(boolean is_mage)
	{
		return _is_mage == is_mage;
	}
	
	public boolean isValid(Player player)
	{
		return isValidClass(player.getClassId().getId()) || isValidType(player.isMageClass());
	}
	
	public boolean isValidSpec(Player player)
	{
		return isValidLevel(player.getLevel()) && isValidClass(player.getClassId().getId());
	}
	
	public boolean isValidCommon(Player player)
	{
		return isValidLevel(player.getLevel()) && isValidType(player.isMageClass());
	}
	
	public HashMap<Integer, Integer> getRewards()
	{
		HashMap<Integer, Integer> rewards = new HashMap<Integer, Integer>();
		
		FPRewardList parent = FPItemHolder.get(_parent_id);
		
		if(parent != null)
		{
			for(Map.Entry<Integer, Integer> parent_entry: parent.getRewards().entrySet())
			{
				if(!_remove_items.contains(parent_entry.getKey()))
					rewards.put(parent_entry.getKey(), parent_entry.getValue());
			}
		}
		
		for(Map.Entry<Integer, Integer> entry: _reward_items.entrySet())
		{
			rewards.put(entry.getKey(), entry.getValue());
		}
		
		return rewards;
	}

	public void addRemoveItem(int item_id) {
		_remove_items.add(item_id);
	}
	
	public void distributeAll(Player player){
		
		player.setVar(PLAYER_VAR_SAVE, _id);
		
		HashMap<Integer, Integer> rewards = getRewards();
		for(Map.Entry<Integer, Integer> entry: rewards.entrySet())
		{
			distributeItem(player, entry.getKey(), entry.getValue());
		}
		Set<Integer> allow_items = rewards.keySet();
		for(ItemInstance remove_item: player.getInventory().getItems())
		{
			int item_id = remove_item.getItemId();
			if(_all_used_items.contains(item_id) && !allow_items.contains(item_id))
				player.getInventory().removeItem(remove_item);
		}
	}
	
	public static void distributeItem(Player player, int item_id, int amount)
	{
		long iventoryCount = player.getInventory().getCountOf(item_id);
		if (iventoryCount < amount)
			ItemFunctions.addItem(player, item_id, amount - iventoryCount, false);
		
		tryToUse(player, item_id);
	}
	
	public static void tryToUse(Player player, int item_id)
	{
		for(ItemInstance item: player.getInventory().getItemsByItemId(item_id))
		{
			useItem(player, item);
		}
	}
	
	public static void useItem(Player player, ItemInstance item)
	{
		if(isGear(item) && !item.isEquipped())
			player.useItem(item, false);
		else if(isShot(item) && player.getAutoSoulShot().contains(item.getItemId()))
			player.addAutoSoulShot(item.getItemId());
	}
	
	public static boolean isGear(ItemInstance item)
	{
		return item.isWeapon() || item.isArmor() || item.isAccessory();
	}
	
	public static boolean isShot(ItemInstance item)
	{
		return item.getItemType() == EtcItemType.SOULSHOT 
				|| item.getItemType() == EtcItemType.SPIRITSHOT
				|| item.getItemType() == EtcItemType.BLESSED_SPIRITSHOT
				|| item.getItemId() == 6645
				|| item.getItemId() == 6646
				|| item.getItemId() == 6647
				|| item.getItemId() == 20332
				|| item.getItemId() == 20333
				|| item.getItemId() == 20334;
	}

	
}
