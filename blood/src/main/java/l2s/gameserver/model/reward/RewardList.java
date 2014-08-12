package l2s.gameserver.model.reward;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.reward.RewardItem;
import l2s.gameserver.model.Player;

/**
 * @reworked VISTALL
 */
@SuppressWarnings("serial")
public class RewardList extends ArrayList<RewardGroup>
{
	public static final int MAX_CHANCE = 1000000;
	private final RewardType _type;
	private final boolean _autoLoot;

	public RewardList(RewardType rewardType, boolean a)
	{
		super(5);
		_type = rewardType;
		_autoLoot = a;
	}

	public List<RewardItem> roll(Player player)
	{
		return roll(player, 1.0, false, false);
	}

	public List<RewardItem> roll(Player player, double mod)
	{
		return roll(player, mod, false, false);
	}

	public List<RewardItem> roll(Player player, double mod, boolean isRaid)
	{
		return roll(player, mod, isRaid, false);
	}

	public List<RewardItem> roll(Player player, double mod, boolean isRaid, boolean isSiegeGuard)
	{
		List<RewardItem> temp = new ArrayList<RewardItem>(size());
		for(RewardGroup g : this)
		{
			List<RewardItem> tdl = g.roll(_type, player, mod, isRaid, isSiegeGuard);
			if(!tdl.isEmpty())
				for(RewardItem itd : tdl)
				{
					if(!checkRestrictedItems(itd))
						temp.add(itd);
				}
					
		}
		return temp;
	}


	public boolean checkRestrictedItems(RewardItem item)
	{
		final int[] ITEM_IDS = 
		{
				19448, // 	Blessed Scroll: Enchant Armor (R-grade)
				22648, // 	Blessed Scroll: Enchant Armor (R-grade)
				23428, // 	Blessed Scroll: Enchant Armor (R-grade)
				23597, //	Blessed Scroll: Enchant Armor (R-grade) Event
				34787, //	Blessed Scroll: Enchant Armor (R-grade) Event
				36284, //	Blessed Scroll: Enchant Armor (R-grade) Event
				19447, // 	Blessed Scroll: Enchant Weapon (R-grade)
				22647, // 	Blessed Scroll: Enchant Weapon (R-grade)
				23422, // 	Blessed Scroll: Enchant Weapon (R-grade)
				23596, //	Blessed Scroll: Enchant Weapon (R-grade) Event
				34789, //	Blessed Scroll: Enchant Weapon (R-grade) Event
				36283 //	Blessed Scroll: Enchant Weapon (R-grade) Event
				
		};
		
		for(int id : ITEM_IDS)
		{
			if(item.itemId == id)
				return true;
		}
		
		return false;
	}
	
	
	public boolean validate()
	{
		for(RewardGroup g : this)
		{
			int chanceSum = 0; // сумма шансов группы
			for(RewardData d : g.getItems())
				chanceSum += d.getChance();
			if(chanceSum <= MAX_CHANCE) // всё в порядке?
				return true;
			double mod = MAX_CHANCE / chanceSum;
			for(RewardData d : g.getItems())
			{
				double chance = d.getChance() * mod; // коррекция шанса группы
				d.setChance(chance);
				g.setChance(MAX_CHANCE);
			}
		}
		return false;
	}

	public boolean isAutoLoot()
	{
		return _autoLoot;
	}

	public RewardType getType()
	{
		return _type;
	}
}