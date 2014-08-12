package l2s.gameserver.handler.items.impl;

import l2s.gameserver.model.Playable;
import l2s.gameserver.model.items.ItemInstance;

/**
 * @author Bonux
 */
public class SkillsReduceItemHandler extends SkillsItemHandler
{
	@Override
	public boolean useSkillsItem(Playable playable, ItemInstance item)
	{
		if(reduceItem(playable, item))
		{
			sendUseMessage(playable, item);
			return true;
		}
		return false;
	}
}
