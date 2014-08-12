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

	@Override public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		SkillList.add(30);	//Back Stab
		SkillList.add(263);	//Deadly Blow
		//SkillList.add(70);	//Drain Health
		//SkillList.add(105);	//Freezing Strike
		//SkillList.add(122);	//Hex
		SkillList.add(223);	//Sting
		//SkillList.add(115);	//Power Break
		SkillList.add(11);	//Trick
		SkillList.add(412);	//Sand bomb
		SkillList.add(410);	//Mortal Strike
		SkillList.add(321);	//Binding Blow
		//SkillList.add(111);	//Ultimate Evasion
		SkillList.add(821);	//Shadow Step
		
		//skill 3rd
		SkillList.add(344);	//Lethal Blow
		SkillList.add(358);	//Bluff
		SkillList.add(355);	//Focus Death
		SkillList.add(357);	//Focus Power
		SkillList.add(460);	//Symbol of the Assassin
		SkillList.add(770);	//Ghost Walking
		
		return SkillList;
	}
	
}

