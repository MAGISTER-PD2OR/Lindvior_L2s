package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Player;

public class FPCWindRider extends DaggerPC
{
	public FPCWindRider(Player actor)
	{
		super(actor);
	}

	public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		_allowSkills.add(30);	//Back Stab
		_allowSkills.add(263);	//Deadly Blow
		_allowSkills.add(12);	//Switch
		_allowSkills.add(412);	//Sand bomb
		_allowSkills.add(410);	//Mortal Strike
		_allowSkills.add(96);	//Bleed
		_allowSkills.add(321);	//Binding Blow
		//_allowSkills.add(111);	//Ultimate Evasion
		_allowSkills.add(821);	//Shadow Step
		
		//skill 3rd
		_allowSkills.add(344);	//Lethal Blow
		_allowSkills.add(358);	//Bluff
		_allowSkills.add(356);	//Focus Chance
		_allowSkills.add(357);	//Focus Power
		_allowSkills.add(351);	//Critical Wound
		_allowSkills.add(446);	//Dodge
		_allowSkills.add(460);	//Symbol of the Assassin
		_allowSkills.add(769);	//Wind Riding
		
		return SkillList;
	}
	
}

