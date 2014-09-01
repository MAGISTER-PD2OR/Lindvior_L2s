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

	public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		//_allowSkills.add(927);		//Burning Chop
		_allowSkills.add(1245);	//Steal Essence
		//_allowSkills.add(1092);	//Fear
		//_allowSkills.add(1097);	//Dreaming Spirit
		//_allowSkills.add(260);		//Hammer Crush
		_allowSkills.add(1244);	//Freezing Flame
						
		//skill 3rd
		
		return SkillList;
	}
	
}

