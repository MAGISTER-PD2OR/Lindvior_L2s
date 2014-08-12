package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Player;

public class FPCFortuneSeeker extends WarriorPC
{
	public FPCFortuneSeeker(Player actor)
	{
		super(actor);
	}

	@Override public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		SkillList.add(30);	//Back stab
		SkillList.add(263);	//Deadly Blow
		SkillList.add(998);	//Blazing Boost
		
		//skill 3rd
		SkillList.add(348);	//Spoil Crush
		SkillList.add(362);	//Armor Crush
		SkillList.add(347);	//Earthquake
		SkillList.add(440);	//Brave Heart
		SkillList.add(357);	//Spoil Bomb
		SkillList.add(456);	//Symbol of Resistance
		SkillList.add(917);	//Final Secret
		SkillList.add(947);	//Lucky Strike
		
		return SkillList;
	}
	

}
