package blood.data.holder;

import java.util.HashMap;
import java.util.List;

import l2s.commons.data.xml.AbstractHolder;
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
	private final HashMap<Integer, List<FPRewardList>> _bonusList = new HashMap<Integer, List<FPRewardList>>();
	
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
	public void addLevelBonus(int lvl, List<FPRewardList> bonus)
	{
		_bonusList.put(lvl, bonus);
	}
	
	/**
	 * Method getLevelBonus.
	 * @param lvl int
	 * @return List<FPRewardList>
	 */
	public List<FPRewardList> getLevelBonus(int lvl)
	{
		return _bonusList.get(lvl);
	}
	
	/**
	 * Method size.
	 * @return int
	 */
	@Override
	public int size()
	{
		return _bonusList.size();
	}
	
	/**
	 * Method clear.
	 */
	@Override
	public void clear()
	{
		_bonusList.clear();
	}
}
