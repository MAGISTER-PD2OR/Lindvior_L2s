package blood.ai.impl;

import l2s.gameserver.model.Player;

public class FPCSpectraMaster extends SummonerPC
{
	public FPCSpectraMaster(Player actor)
	{
		super(actor);
		//skill 2nd
		_allowSkills.add(33);		//Summon Phantom Cubic
		//_allowSkills.add(1281);	//Summon Spark Cubic
		_allowSkills.add(1206);	//Wind Shackle
		_allowSkills.add(1530);	//Death Spike
		
		//skill 3rd
		_allowSkills.add(1348);	//Assassin Servitor
		_allowSkills.add(1351);	//Mage Bane
		_allowSkills.add(1408);	//Summon Spectral Servitor
		_allowSkills.add(1349);	//Final Servitor		
	}
}
