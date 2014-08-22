package blood.ai.impl;

import l2s.gameserver.model.Player;

public class FPCTyr extends WarriorPC
{
	public FPCTyr(Player actor)
	{
		super(actor);
	}
	
	public void prepareSkillsSetup() {
		_allowSelfBuffSkills.add(10757);
	}
}

