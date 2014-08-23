package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Player;

public class FPCSwordMuse extends TankerPC
{
	public FPCSwordMuse(Player actor)
	{
		super(actor);
		
	}
	
	public void prepareSkillsSetup()
	{
		//skill 2nd
		_allowSkills.add(407);	//Psycho Symphony
		_allowSkills.add(98);	//Sword Symphony
		
		//skill 3rd
		_allowSkills.add(455);	//Symbol of Noise		
		
		super.prepareSkillsSetup();
	}

	@Override
	public int getRateDAM()
	{
		return 20;
	}

	@Override
	public int getRateSTUN()
	{
		return 0;
	}

	@Override
	public int getRateBUFF()
	{
		return 0;
	}
}

