package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Player;

public class FPCAdventurer extends DaggerPC
{
	public FPCAdventurer(Player actor)
	{
		super(actor);
	}

	@Override
	protected boolean thinkBuff()
	{
		if(thinkBuff(new int[] {
			356, 	//	Focus Chance
			445,	//Mirage
			351		//Critical Wound
			}))
			return true;
		
		return super.thinkBuff();
	}
	
	@Override public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		SkillList.add(30);	//Back Stab
		SkillList.add(263);	//Deadly Blow
		SkillList.add(12);	//Switch
		SkillList.add(409);	//Critical Blow
		SkillList.add(96);	//Bleed
		//SkillList.add(11);	//Trick
		SkillList.add(412);	//Sand bomb
		//SkillList.add(111);	//Ultimate Evasion
		SkillList.add(821);	//Shadow Step
		
		//skill 3rd
		SkillList.add(344);	//Lethal Blow
		SkillList.add(358);	//Bluff
		SkillList.add(460);	//Symbol of the Assassin
		SkillList.add(768);	//Exciting Adventure
		SkillList.add(991);	//Throwing Dagger
		SkillList.add(928);	//Dual Blow
		
		return SkillList;
	}
	
}

