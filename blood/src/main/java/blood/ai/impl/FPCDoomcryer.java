package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Player;

public class FPCDoomcryer extends MysticPC
{
	public FPCDoomcryer(Player actor)
	{
		super(actor);
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
		SkillList.add(1244);	//Freezing Flame
						
		//skill 3rd
		
		return SkillList;
	}
	
}

