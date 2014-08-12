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

	@Override public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		SkillList.add(30);	//Back Stab
		SkillList.add(263);	//Deadly Blow
		SkillList.add(12);	//Switch
		SkillList.add(412);	//Sand bomb
		SkillList.add(410);	//Mortal Strike
		SkillList.add(96);	//Bleed
		SkillList.add(321);	//Binding Blow
		//SkillList.add(111);	//Ultimate Evasion
		SkillList.add(821);	//Shadow Step
		
		//skill 3rd
		SkillList.add(344);	//Lethal Blow
		SkillList.add(358);	//Bluff
		SkillList.add(356);	//Focus Chance
		SkillList.add(357);	//Focus Power
		SkillList.add(351);	//Critical Wound
		SkillList.add(446);	//Dodge
		SkillList.add(460);	//Symbol of the Assassin
		SkillList.add(769);	//Wind Riding
		
		return SkillList;
	}
	
}

