package blood.ai.impl;

import l2s.gameserver.model.Player;
import l2s.gameserver.model.Skill;
import l2s.gameserver.model.base.ClassLevel;
import blood.ai.EventFPC;

public class WarriorPC extends EventFPC
{
	public WarriorPC(Player actor)
	{
		super(actor);
	}
	
	public void prepareSkillsSetup() {
		_allowSelfBuffSkills.add(FPCDreadnought.SKILL_WARCRY);
		_allowSelfBuffSkills.add(FPCDuelist.SKILL_DEFKECT_ARROW);
		_allowSelfBuffSkills.add(FPCDuelist.SKILL_MAJESTY);
	}
	
	protected boolean isAllowClass()
	{
		return !getActor().getClassId().isOfLevel(ClassLevel.AWAKED);
	}
	
	protected Skill getNpcSuperiorBuff()
	{
//		return getSkill(15648, 1); //tank
		return getSkill(15649, 1); //warrior
//		return getSkill(15650, 1); //wizzard
	}

	@Override
	protected boolean createFightTask()
	{
		return defaultFightTask();
	}

	@Override
	public int getRatePHYS()
	{
		return 50;
	}

	@Override
	public int getRateDOT()
	{
		return 0;
	}

	@Override
	public int getRateDEBUFF()
	{
		return 20;
	}

	@Override
	public int getRateDAM()
	{
		return 90;
	}

	@Override
	public int getRateSTUN()
	{
		return 50;
	}

	@Override
	public int getRateBUFF()
	{
		return 80;
	}

	@Override
	public int getRateHEAL()
	{
		return 0;
	}
	
}