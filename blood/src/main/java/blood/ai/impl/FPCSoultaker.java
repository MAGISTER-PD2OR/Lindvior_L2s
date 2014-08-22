package blood.ai.impl;

import l2s.gameserver.model.Player;

public class FPCSoultaker extends MysticPC
{
	public FPCSoultaker(Player actor)
	{
		super(actor);
		//skill 2nd
		_allowSkills.add(1334);	//Summon Cursed Man
		_allowSkills.add(1169);	//Curse Fear
		_allowSkills.add(1234);	//Vampiric Claw
		_allowSkills.add(1064);	//Silence
		//_allowSkills.add(1069);	//Sleep
		_allowSkills.add(1148);	//Death Spike
		_allowSkills.add(1170);	//Anchor
		_allowSkills.add(1163);	//Curse Gloom
		//_allowSkills.add(1159);	//Curse Death Link
		_allowSkills.add(1381);	//Mass Fear
		_allowSkills.add(1298);	//Mass Slow
		_allowSkills.add(1382);	//Mass Gloom
		
		//skill 3rd
		_allowSkills.add(1343);	//Dark Vortex
		_allowSkills.add(1344);	//Mass Warrior Bane
		_allowSkills.add(1336);	//Curse of Doom
		_allowSkills.add(1345);	//Mass Mage Bane
		_allowSkills.add(1337);	//Curse of Abyss 
		_allowSkills.add(1442);	//Day of Doom
		_allowSkills.add(1467);	//Meteor 
		_allowSkills.add(1495);	//Vampiric Mist		
		
	}
	
	@Override
	public int getRateDEBUFF()
	{
		return 80;
	}

}
