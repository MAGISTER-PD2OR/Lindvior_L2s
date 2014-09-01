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

	public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		//_allowSkills.add(927);		//Burning Chop
		_allowSkills.add(1245);	//Steal Essence
		//_allowSkills.add(1092);	//Fear
		//_allowSkills.add(1097);	//Dreaming Spirit
		//_allowSkills.add(260);		//Hammer Crush
		//_allowSkills.add(1208);	//Seal of Binding
		//_allowSkills.add(1096);	//Seal of Chaos
		//_allowSkills.add(1099);	//Seal of Slow
		//_allowSkills.add(1104);	//Seal of Winter
		//_allowSkills.add(1210);	//Seal of Gloom
		_allowSkills.add(1108);	//Seal of Flame
		//_allowSkills.add(1246);	//Seal of Silence
		//_allowSkills.add(1248);	//Seal of Suspension
		//_allowSkills.add(306);		//Ritual of Life
						
		//skill 3rd
		_allowSkills.add(1367);	//Seal of Disease
		_allowSkills.add(1462);	//Seal of Blockage
		_allowSkills.add(1416);	//Pa'agrio's Fist
		_allowSkills.add(1427);	//Flames of Invincibility
		
		return SkillList;
	}
	
}

