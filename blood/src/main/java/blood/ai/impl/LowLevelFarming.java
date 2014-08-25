package blood.ai.impl;

import l2s.gameserver.model.Creature;
import l2s.gameserver.model.Player;
import l2s.gameserver.model.Skill;
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
	
	protected Skill getNpcSuperiorBuff()
	{
//		return getSkill(15648, 1); //tank
		return getSkill(15649, 1); //warrior
//		return getSkill(15650, 1); //wizzard
	}
	
	protected boolean lameFightTask(Creature target)
	{
		Player actor = getActor();
		
		double distance = actor.getDistance(target);
		
		return chooseTaskAndTargets(null, target, distance);
	}
	
}