package blood.model;

import java.util.List;

import l2s.gameserver.model.Player;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import blood.data.holder.FPItemHolder;

public class FPReward {
	
	
	@SuppressWarnings("unused")
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
		int playerLvl = player.getLevel();
		int playerClassId = player.getClassId().getId();
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
			item.distribute(player);
	}
	
}