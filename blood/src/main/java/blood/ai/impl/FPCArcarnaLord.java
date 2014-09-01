package blood.ai.impl;

import l2s.gameserver.model.Player;

public class FPCArcarnaLord extends SummonerPC
{
	public FPCArcarnaLord(Player actor)
	{
		super(actor);
		//skill 2nd
		//_allowSkills.add(1184);	//Ice Bolt
		_allowSkills.add(1279);	//Summon Binding Cubic
		//_allowSkills.add(10);		//Summon Storm Cubic
		_allowSkills.add(1558);	//Dimension Spiral
		_allowSkills.add(1386);	//Arcane Disruption
		
		//skill 3rd
		_allowSkills.add(1350);	//Warrior Bane
		_allowSkills.add(1351);	//Mage Bane
		_allowSkills.add(1346);	//Warrior Servitor
		_allowSkills.add(1349);	//Final Servitor	
		
		_allowSkills.add(1276);	//Kai	
	}
}
