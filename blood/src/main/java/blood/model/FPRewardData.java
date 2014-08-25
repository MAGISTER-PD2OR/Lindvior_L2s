package blood.model;

import l2s.gameserver.model.Player;
import l2s.gameserver.model.items.ItemInstance;
import l2s.gameserver.templates.item.EtcItemTemplate.EtcItemType;
import l2s.gameserver.utils.ItemFunctions;

public class FPRewardData {
	
	private final int _item_id;
	private final int _item_count;
	private final int _replace_id;
	
	public FPRewardData(int item_id, int item_count, int replace_id){
		_item_id = item_id;
		_item_count = item_count;
		_replace_id = replace_id;
	}
	
	public int getId(){
		return _item_id;
	}
	
	public int getAmount(){
		return _item_count;
	}
	
	public int getReplaceId(){
		return _replace_id;
	}
	
	public void distribute(Player player)
	{
		long iventoryCount = player.getInventory().getCountOf(_item_id);
		if (iventoryCount < _item_count)
			ItemFunctions.addItem(player, _item_id, _item_count - iventoryCount, false);
		
		tryToUse(player);
		garbageClean(player);
	}
	
	public void tryToUse(Player player)
	{
		for(ItemInstance item: player.getInventory().getItemsByItemId(_item_id))
		{
			useItem(player, item);
		}
	}
	
	public void useItem(Player player, ItemInstance item)
	{
		if(isGear(item) && !item.isEquipped())
			player.useItem(item, false);
		else if(isShot(item) && player.getAutoSoulShot().contains(item.getItemId()))
			player.addAutoSoulShot(item.getItemId());
	}
	
	private boolean isGear(ItemInstance item)
	{
		return item.isWeapon() || item.isArmor() || item.isAccessory();
	}
	
	private boolean isShot(ItemInstance item)
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
	
	public void garbageClean(Player player)
	{
		if(_replace_id <= 0)
			return;
		
		for(ItemInstance item: player.getInventory().getItemsByItemId(_replace_id))
			if(isGear(item))
				player.getInventory().removeItem(item);
	}
}
