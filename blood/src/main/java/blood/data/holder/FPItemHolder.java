package blood.data.holder;

import java.util.HashMap;

import l2s.commons.data.xml.AbstractHolder;
import l2s.commons.math.random.RndSelector;
import l2s.gameserver.model.Player;
import blood.model.FPRewardList;

public final class FPItemHolder extends AbstractHolder
{
	/**
	 * Field _instance.
	 */
	private static final FPItemHolder _instance = new FPItemHolder();
	/**
	 * Field _bonusList.
	 */
	private static final HashMap<Integer, FPRewardList> _rewards = new HashMap<Integer, FPRewardList>();
	
	/**
	 * Method getInstance.
	 * @return LevelBonusHolder
	 */
	public static FPItemHolder getInstance()
	{
		return _instance;
	}
	
	/**
	 * Method addLevelBonus.
	 * @param lvl int
	 * @param bonus double
	 */
	public void add(int id, FPRewardList rewardList)
	{
		_rewards.put(id, rewardList);
	}
	
	/**
	 * Method getLevelBonus.
	 * @param lvl int
	 * @return List<FPRewardList>
	 */
	public static FPRewardList get(int id)
	{
		return _rewards.get(id);
	}
	
	public static FPRewardList getRewardList(Player player, boolean useOldList)
	{
		if(useOldList)
		{
			FPRewardList oldList = _rewards.get(player.getVarInt(FPRewardList.PLAYER_VAR_SAVE));
			if(oldList != null)
				return oldList;
		}
		
		RndSelector<FPRewardList> rndFactor = new RndSelector<FPRewardList>(); 
		int count = 0;
		for(FPRewardList reward_list: _rewards.values())
			if(reward_list.isValidSpec(player)){
				rndFactor.add(reward_list, reward_list.getWeight());
				count++;
			}
				
		if(count == 0)
			for(FPRewardList reward_list: _rewards.values())
				if(reward_list.isValidCommon(player))
					return reward_list;
		
		return rndFactor.select();
	}
	
	public static void equip(Player player, boolean useOldList)
	{
		FPRewardList rewardList = getRewardList(player, useOldList);
		
		if(rewardList != null)
			rewardList.distributeAll(player);
	}
	
	/**
	 * Method size.
	 * @return int
	 */
	@Override
	public int size()
	{
		return _rewards.size();
	}
	
	/**
	 * Method clear.
	 */
	@Override
	public void clear()
	{
		_rewards.clear();
	}
}
