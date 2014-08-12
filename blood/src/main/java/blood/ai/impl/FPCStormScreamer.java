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

	@Override public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		SkillList.add(1239);	//Hurricane
		SkillList.add(1169);	//Curse Fear
		SkillList.add(1267);	//Shadow Flare 
		SkillList.add(1234);	//Vampiric Claw
		//SkillList.add(1069);	//Sleep
		//SkillList.add(1160);	//Slow
		SkillList.add(1222);	//Curse Chaos
		SkillList.add(1064);	//Silence
		SkillList.add(1074);	//Surrender to Wind
		SkillList.add(1048);	//Death Spike
		SkillList.add(1176);	//Tempest
		//SkillList.add(1159);	//Curse Death Link
		SkillList.add(1417);	//Aura Flash
		SkillList.add(1288);	//Aura Symphony
		SkillList.add(1294);	//Elemental Storm
		SkillList.add(1291);	//Demon Wind
				
		//skill 3rd
		SkillList.add(1343);	//Dark Vortex
		SkillList.add(1341);	//Wind Vortex
		SkillList.add(1357);	//Empowering Echo
		SkillList.add(1338);	//Arcane Chaos
		SkillList.add(1458);	//Throne of Wind
		SkillList.add(1456);	//Wind Vortex Slug
		SkillList.add(1420);	//Cyclone
		SkillList.add(1468);	//Star Fall
		
		return SkillList;
	}
	
}

