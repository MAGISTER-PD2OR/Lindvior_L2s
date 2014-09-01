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

	public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		_allowSkills.add(30);	//Back stab
		_allowSkills.add(263);	//Deadly Blow
		_allowSkills.add(998);	//Blazing Boost
		
		//skill 3rd
		_allowSkills.add(348);	//Spoil Crush
		_allowSkills.add(362);	//Armor Crush
		_allowSkills.add(347);	//Earthquake
		_allowSkills.add(440);	//Brave Heart
		_allowSkills.add(357);	//Spoil Bomb
		_allowSkills.add(456);	//Symbol of Resistance
		_allowSkills.add(917);	//Final Secret
		_allowSkills.add(947);	//Lucky Strike
		
		return SkillList;
	}
	

}
