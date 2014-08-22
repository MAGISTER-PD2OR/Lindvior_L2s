package blood.ai.impl;

import l2s.gameserver.model.Player;

public class FPCAdventurer extends DaggerPC
{
	public FPCAdventurer(Player actor)
	{
		super(actor);
		//skill 2nd
		_allowSkills.add(30);	//Back Stab
		_allowSkills.add(263);	//Deadly Blow
		_allowSkills.add(12);	//Switch
		_allowSkills.add(409);	//Critical Blow
		_allowSkills.add(96);	//Bleed
		//_allowSkills.add(11);	//Trick
		_allowSkills.add(412);	//Sand bomb
		//_allowSkills.add(111);	//Ultimate Evasion
		_allowSkills.add(821);	//Shadow Step
		
		//skill 3rd
		_allowSkills.add(344);	//Lethal Blow
		_allowSkills.add(358);	//Bluff
		_allowSkills.add(460);	//Symbol of the Assassin
		_allowSkills.add(768);	//Exciting Adventure
		_allowSkills.add(991);	//Throwing Dagger
		_allowSkills.add(928);	//Dual Blow
		
		_allowSkills.add(356);	//Focus Chance
		_allowSkills.add(445);	//Mirage
		_allowSkills.add(351);	//Critical Wound	
	}
}

