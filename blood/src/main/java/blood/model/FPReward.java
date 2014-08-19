package blood.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import l2s.gameserver.model.Player;
import l2s.gameserver.model.items.ItemInstance;
import l2s.gameserver.templates.item.EtcItemTemplate.EtcItemType;
import l2s.gameserver.templates.item.ItemType;
import l2s.gameserver.utils.ItemFunctions;
import blood.Blood;
import blood.data.holder.FPItemHolder;

public class FPReward {
	private static final Logger 		_log = LoggerFactory.getLogger(FPReward.class);
	
	/**
	 * Field _instance.
	 */
	private static final FPReward _instance = new FPReward();
	
	/**
	 * Method getInstance.
	 * @return LevelBonusHolder
	 */
	public static FPReward getInstance(){
		return _instance;
	}

	public FPReward(){
		
	}
	
	public void giveReward(Player player){
		
//		_log.error("just tracing", new Exception("trace"));
		
		int playerLvl = player.getLevel();
		int playerClassId = player.getClassId().getId();
//		System.out.println("playerLvl:" + playerLvl + " playerClassId:" + playerClassId);
		for (int i = playerLvl; i > 0; i--) {
			List<FPRewardList> rewardByLevel = FPItemHolder.getInstance().getLevelBonus(i);
			if (rewardByLevel != null){
				for (FPRewardList rewardByClass: rewardByLevel ){
					if (rewardByClass.hasClass(playerClassId)){
						distributeList(rewardByClass, player);
						return;
					}
				}
				for (FPRewardList rewardByClass: rewardByLevel ){
					if (player.isMageClass() == rewardByClass.is_mage()){
						distributeList(rewardByClass, player);
						return;
					}
				}
				break;
			}
		}
	}
	
	public void distributeList(FPRewardList itemList, Player player){
		for(FPRewardData item: itemList.getReward())
		{
			long iventoryCount = player.getInventory().getCountOf(item._item_id);
			if (iventoryCount < item._item_count)
			{
				ItemFunctions.addItem(player, item._item_id, item._item_count - iventoryCount, false);
			}
			
			tryToUseItem(player, item._item_id);
		}
	}
	
	public void tryToUseItem(Player player, int itemId)
	{
		for(ItemInstance item: player.getInventory().getItemsByItemId(itemId))
		{
			tryToUseItem(player, item);
		}
	}
	
	public void tryToUseItem(Player player, ItemInstance item)
	{
		if(item.isWeapon() && !item.isEquipped())
		{
			player.useItem(item, false);
		}
		else if(item.isArmor() && !item.isEquipped())
		{
			System.out.println(player + " try to use armor:"+item.getBodyPart()+" "+item.getItemId());
			player.useItem(item, false);
		}
		
		else if(item.getItemType() == EtcItemType.SOULSHOT 
				|| item.getItemType() == EtcItemType.SPIRITSHOT
				|| item.getItemType() == EtcItemType.BLESSED_SPIRITSHOT)
		{
			System.out.println(player + " active soulshot "+item.getItemId());
			player.addAutoSoulShot(item.getItemId());
		}
	}
	
	
}