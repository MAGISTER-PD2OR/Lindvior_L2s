package blood.ai.impl;

import l2s.gameserver.model.Player;

public class FPCArchmage extends MysticPC
{
	public static final int SKILL_BLAZING_SKIN = 1232;
	public static final int SKILL_SEED_OF_FIRE = 1285;
	
	public FPCArchmage(Player actor)
	{
		super(actor);
	}
	
	@Override
	public void prepareSkillsSetup()
	{
		//skill 2nd
		//_allowSkills.add(1171);	//Blazing Circle
		_allowSkills.add(1169);	//Curse Fear
		_allowSkills.add(1230);	//Prominence 
		_allowSkills.add(1231);	//Aura Flare
		//_allowSkills.add(1069);	//Sleep
		//_allowSkills.add(1160);	//Slow
		_allowSkills.add(1083);	//Surrender to Fire
		_allowSkills.add(1056);	//Cancelation
		_allowSkills.add(1417);	//Aura Flash
		_allowSkills.add(1296);	//Rain of Fire
		_allowSkills.add(1288);	//Aura Symphony
		_allowSkills.add(1284);	//Aura Symphon
		_allowSkills.add(1232);	//Aura Symphon
		_allowSkills.add(1289);	//Inferno
				
		//skill 3rd
		_allowSkills.add(1339);	//Fire Vortex
		_allowSkills.add(1452);	//Count of Fire
		_allowSkills.add(1451);	//Fire Vortex Burster
		_allowSkills.add(1419);	//Vocalno
		_allowSkills.add(1467);	//Meteor
	}
	
}

