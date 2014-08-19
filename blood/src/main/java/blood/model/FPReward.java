package blood.model;

import java.util.List;

import l2s.gameserver.model.Player;
import l2s.gameserver.model.items.ItemInstance;
import l2s.gameserver.utils.ItemFunctions;
import blood.data.holder.FPItemHolder;

public class FPReward {
	
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
		
		int playerLvl = player.getLevel();
		int playerClassId = player.getClassId().getId();
		System.out.println("playerLvl:" + playerLvl + " playerClassId:" + playerClassId);
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
					}
				}
				break;
			}
		}
	}
	
	public void distributeList(FPRewardList itemList, Player player){
		for(FPRewardData item: itemList.getReward()){
			long iventoryCount = player.getInventory().getCountOf(item._item_id);
			if (iventoryCount < item._item_count)
			{
				ItemInstance newItem = ItemFunctions.createItem(item._item_id);
				if(newItem.isStackable()){
					newItem.setCount((long) item._item_count - iventoryCount);
					player.getInventory().addItem(newItem);
				}else{
					System.out.println("distributeList body part:" +newItem.getBodyPart());
					newItem.setCount(item._item_count - iventoryCount);
					player.getInventory().addItem(newItem);
					player.useItem(newItem, false);
				}
			}
			else
			{
				// already have that item, check wear
				for(ItemInstance existItemInstance: player.getInventory().getItemsByItemId(item._item_id))
				{
					if((existItemInstance.isArmor() || existItemInstance.isWeapon()) && !existItemInstance.isEquipped())
					{
						player.useItem(existItemInstance, false);
					}
				}
				
			}
		}
	}
	
	
}