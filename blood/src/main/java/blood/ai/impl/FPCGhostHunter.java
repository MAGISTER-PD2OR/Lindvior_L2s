package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Player;

public class FPCGhostHunter extends DaggerPC
{
	public FPCGhostHunter(Player actor)
	{
		super(actor);
	}

	public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		_allowSkills.add(30);	//Back Stab
		_allowSkills.add(263);	//Deadly Blow
		//_allowSkills.add(70);	//Drain Health
		//_allowSkills.add(105);	//Freezing Strike
		//_allowSkills.add(122);	//Hex
		_allowSkills.add(223);	//Sting
		//_allowSkills.add(115);	//Power Break
		_allowSkills.add(11);	//Trick
		_allowSkills.add(412);	//Sand bomb
		_allowSkills.add(410);	//Mortal Strike
		_allowSkills.add(321);	//Binding Blow
		//_allowSkills.add(111);	//Ultimate Evasion
		_allowSkills.add(821);	//Shadow Step
		
		//skill 3rd
		_allowSkills.add(344);	//Lethal Blow
		_allowSkills.add(358);	//Bluff
		_allowSkills.add(355);	//Focus Death
		_allowSkills.add(357);	//Focus Power
		_allowSkills.add(460);	//Symbol of the Assassin
		_allowSkills.add(770);	//Ghost Walking
		
		return SkillList;
	}
	
}

