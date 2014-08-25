package blood.ai.impl;

import l2s.gameserver.model.Creature;
import l2s.gameserver.model.Player;
import blood.ai.EventFPC;

public class LowLevelFarming extends EventFPC
{
	public LowLevelFarming(Player actor)
	{
		super(actor);
	}

	protected boolean defaultSubFightTask(Creature target)
	{
		lameFightTask(target);
		return true;
	}
	
	protected boolean lameFightTask(Creature target)
	{
		Player actor = getActor();
		
		double distance = actor.getDistance(target);
		
		return chooseTaskAndTargets(null, target, distance);
	}
	
}