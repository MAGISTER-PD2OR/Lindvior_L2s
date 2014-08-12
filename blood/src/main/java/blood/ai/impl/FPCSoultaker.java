package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Player;

public class FPCSoultaker extends MysticPC
{
	public FPCSoultaker(Player actor)
	{
		super(actor);
		
	}
	
	@Override
	protected boolean thinkSummon()
	{
		if(thinkSummon(1334)) //summon Cursed Man
			return true;
		
		return super.thinkSummon();
	}

	@Override
	public int getRateDEBUFF()
	{
		return 80;
	}
	
	@Override public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		//SkillList.add(1334);	//Summon Cursed Man
		SkillList.add(1169);	//Curse Fear
		SkillList.add(1234);	//Vampiric Claw
		SkillList.add(1064);	//Silence
		//SkillList.add(1069);	//Sleep
		SkillList.add(1148);	//Death Spike
		SkillList.add(1170);	//Anchor
		SkillList.add(1163);	//Curse Gloom
		//SkillList.add(1159);	//Curse Death Link
		SkillList.add(1381);	//Mass Fear
		SkillList.add(1298);	//Mass Slow
		SkillList.add(1382);	//Mass Gloom
		
		//skill 3rd
		SkillList.add(1343);	//Dark Vortex
		SkillList.add(1344);	//Mass Warrior Bane
		SkillList.add(1336);	//Curse of Doom
		SkillList.add(1345);	//Mass Mage Bane
		SkillList.add(1337);	//Curse of Abyss 
		SkillList.add(1442);	//Day of Doom
		SkillList.add(1467);	//Meteor 
		SkillList.add(1495);	//Vampiric Mist		
		
		return SkillList;
	}
	

}
