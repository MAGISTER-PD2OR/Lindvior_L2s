package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Player;

public class FPCStormScreamer extends MysticPC
{
	public FPCStormScreamer(Player actor)
	{
		super(actor);
		
	}

	public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		_allowSkills.add(1239);	//Hurricane
		_allowSkills.add(1169);	//Curse Fear
		_allowSkills.add(1267);	//Shadow Flare 
		_allowSkills.add(1234);	//Vampiric Claw
		//_allowSkills.add(1069);	//Sleep
		//_allowSkills.add(1160);	//Slow
		_allowSkills.add(1222);	//Curse Chaos
		_allowSkills.add(1064);	//Silence
		_allowSkills.add(1074);	//Surrender to Wind
		_allowSkills.add(1048);	//Death Spike
		_allowSkills.add(1176);	//Tempest
		//_allowSkills.add(1159);	//Curse Death Link
		_allowSkills.add(1417);	//Aura Flash
		_allowSkills.add(1288);	//Aura Symphony
		_allowSkills.add(1294);	//Elemental Storm
		_allowSkills.add(1291);	//Demon Wind
				
		//skill 3rd
		_allowSkills.add(1343);	//Dark Vortex
		_allowSkills.add(1341);	//Wind Vortex
		_allowSkills.add(1357);	//Empowering Echo
		_allowSkills.add(1338);	//Arcane Chaos
		_allowSkills.add(1458);	//Throne of Wind
		_allowSkills.add(1456);	//Wind Vortex Slug
		_allowSkills.add(1420);	//Cyclone
		_allowSkills.add(1468);	//Star Fall
		
		return SkillList;
	}
	
}

