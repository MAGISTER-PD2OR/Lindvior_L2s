package blood.ai.impl;

import l2s.gameserver.model.Player;

public class FPCPhoenixKnight extends TankerPC
{
	public FPCPhoenixKnight(Player actor)
	{
		super(actor);
	}
	
	public void prepareSkillsSetup()
	{
		//skill 2nd
		_allowSkills.add(28);	//Aggression
		_allowSkills.add(18);	//Aura of Hate
		//_allowSkills.add(82);	//Majesty
		_allowSkills.add(92);	//Shield Stun
		//_allowSkills.add(110);	//Ultimate Defense
		_allowSkills.add(403);	//Shackle
		//_allowSkills.add(404);	//Mass Shackling
		_allowSkills.add(400);	//Tribunal
		_allowSkills.add(406);	//Angelic Icon
		_allowSkills.add(984);	//Shield Strike
				
		//skill 3rd
		_allowSkills.add(353);	//Shield Slam
		_allowSkills.add(368);	//Vengeance
		_allowSkills.add(350);	//Physical Mirror
		_allowSkills.add(438);	//Soul of the Phoenix
		_allowSkills.add(454);	//Symbol of Defense
		_allowSkills.add(784);	//Spirit of Phoenix
		_allowSkills.add(785);	//Flame Icon
		
		super.prepareSkillsSetup();
	}
	
}

