package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Player;

public class FPCDominator extends MysticPC
{
	public FPCDominator(Player actor)
	{
		super(actor);
	}

	@Override
	public int getRatePHYS()
	{
		return _damSkills.length == 0 ? 25 : 0;
	}

	@Override
	public int getRateDEBUFF()
	{
		return 80;
	}

	@Override
	public int getRateDAM()
	{
		return 100;
	}

	@Override
	public int getRateSTUN()
	{
		return 10;
	}

	@Override public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		//SkillList.add(927);		//Burning Chop
		SkillList.add(1245);	//Steal Essence
		//SkillList.add(1092);	//Fear
		//SkillList.add(1097);	//Dreaming Spirit
		//SkillList.add(260);		//Hammer Crush
		//SkillList.add(1208);	//Seal of Binding
		//SkillList.add(1096);	//Seal of Chaos
		//SkillList.add(1099);	//Seal of Slow
		//SkillList.add(1104);	//Seal of Winter
		//SkillList.add(1210);	//Seal of Gloom
		SkillList.add(1108);	//Seal of Flame
		//SkillList.add(1246);	//Seal of Silence
		//SkillList.add(1248);	//Seal of Suspension
		//SkillList.add(306);		//Ritual of Life
						
		//skill 3rd
		SkillList.add(1367);	//Seal of Disease
		SkillList.add(1462);	//Seal of Blockage
		SkillList.add(1416);	//Pa'agrio's Fist
		SkillList.add(1427);	//Flames of Invincibility
		
		return SkillList;
	}
	
}

