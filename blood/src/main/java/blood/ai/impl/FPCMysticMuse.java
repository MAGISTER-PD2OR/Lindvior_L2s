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

	public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		_allowSkills.add(1235);	//Hydro Blast
		_allowSkills.add(1169);	//Curse Fear
		_allowSkills.add(1265);	//Solar Flare 
		_allowSkills.add(1231);	//Aura Flare
		//_allowSkills.add(1069);	//Sleep
		_allowSkills.add(1236);	//Frost Bolt
		_allowSkills.add(1237);	//Ice Dagger
		_allowSkills.add(1071);	//Surrender to Water
		_allowSkills.add(1056);	//Cancelation
		_allowSkills.add(1174);	//Frost Wall
		_allowSkills.add(1183);	//Freezing Shackle
		_allowSkills.add(1295);	//Aqua Splash
		_allowSkills.add(1417);	//Aura Flash
		_allowSkills.add(1288);	//Aura Symphony
		_allowSkills.add(1293);	//Elemental Symphony
				
		//skill 3rd
		_allowSkills.add(1342);	//Light Vortex
		_allowSkills.add(1340);	//Ice Vortex
		_allowSkills.add(1338);	//Arcane Chaos
		_allowSkills.add(1454);	//Diamond Dust
		_allowSkills.add(1453);	//Ice Vortex Crusher
		_allowSkills.add(1421);	//Raging Wave
		_allowSkills.add(1468);	//Star Fall
		
		return SkillList;
	}
	
}

