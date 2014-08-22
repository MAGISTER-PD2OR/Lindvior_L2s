package blood.ai.impl;

import l2s.gameserver.model.Player;

public class FPCSagittarius extends RangerPC
{
	public FPCSagittarius(Player actor)
	{
		super(actor);
	}

	
	public void prepareSkillsSetup()
	{
		//skill 2nd
		_allowSkills.add(19);	//Double Shot
		_allowSkills.add(101);	//Stun Shot
		//_allowSkills.add(303);	//Soul of Sagittarius
		_allowSkills.add(24);	//Burst Shot
		_allowSkills.add(99);	//Rapid Shot
		//_allowSkills.add(131);	//Hawkeye
		//_allowSkills.add(416);	//Blessing of Sagittarius 
		//_allowSkills.add(415);	//Spirit of Sagittarius
		_allowSkills.add(313);	//Snipe
		//_allowSkills.add(111);	//Ultimate Evasion
		
		//skill 3rd
		_allowSkills.add(343);	//Lethal Shot
		_allowSkills.add(354);	//Hamstring Shot
		_allowSkills.add(459);	//Symbol of the Sniper
		_allowSkills.add(924);	//Seven Arrow
		_allowSkills.add(771);	//Flame Hawk
		
		super.prepareSkillsSetup();
	}
	
}

