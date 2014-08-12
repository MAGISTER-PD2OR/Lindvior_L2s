package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Player;

public class FPCMysticMuse extends MysticPC
{
	public FPCMysticMuse(Player actor)
	{
		super(actor);
	}

	@Override public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		SkillList.add(1235);	//Hydro Blast
		SkillList.add(1169);	//Curse Fear
		SkillList.add(1265);	//Solar Flare 
		SkillList.add(1231);	//Aura Flare
		//SkillList.add(1069);	//Sleep
		SkillList.add(1236);	//Frost Bolt
		SkillList.add(1237);	//Ice Dagger
		SkillList.add(1071);	//Surrender to Water
		SkillList.add(1056);	//Cancelation
		SkillList.add(1174);	//Frost Wall
		SkillList.add(1183);	//Freezing Shackle
		SkillList.add(1295);	//Aqua Splash
		SkillList.add(1417);	//Aura Flash
		SkillList.add(1288);	//Aura Symphony
		SkillList.add(1293);	//Elemental Symphony
				
		//skill 3rd
		SkillList.add(1342);	//Light Vortex
		SkillList.add(1340);	//Ice Vortex
		SkillList.add(1338);	//Arcane Chaos
		SkillList.add(1454);	//Diamond Dust
		SkillList.add(1453);	//Ice Vortex Crusher
		SkillList.add(1421);	//Raging Wave
		SkillList.add(1468);	//Star Fall
		
		return SkillList;
	}
	
}

