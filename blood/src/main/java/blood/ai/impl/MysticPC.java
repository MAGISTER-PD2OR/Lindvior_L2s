package blood.ai.impl;

import l2s.gameserver.model.Player;
import l2s.gameserver.model.Skill;
import blood.ai.EventFPC;

public class MysticPC extends EventFPC
{
	public MysticPC(Player actor)
	{
		super(actor);
	}
	
	public void prepareSkillsSetup() {
		
	}
	
	protected Skill getNpcSuperiorBuff()
	{
//		return getSkill(15648, 1); //tank
//		return getSkill(15649, 1); //warrior
		return getSkill(15650, 1); //wizzard
	}

	@Override
	protected boolean createFightTask()
	{
		return defaultFightTask();
	}

	@Override
	public int getRatePHYS()
	{
		return _damSkills.length == 0 ? 25 : 0;
	}

	@Override
	public int getRateDOT()
	{
		return 25;
	}

	@Override
	public int getRateDEBUFF()
	{
		return 20;
	}

	@Override
	public int getRateDAM()
	{
		return 100;
	}

	@Override
	public int getRateSTUN()
	{
		return 10;
	}

	@Override
	public int getRateBUFF()
	{
		return 10;
	}

	@Override
	public int getRateHEAL()
	{
		return 0;
	}
	
}