package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Player;

public class FPCShillienTemplar extends TankerPC
{
	public FPCShillienTemplar(Player actor)
	{
		super(actor);
		
	}
	
	public void prepareSkillsSetup()
	{
		//skill 2nd
		_allowSkills.add(28);	//Aggression
		_allowSkills.add(18);	//Aura of Hate
		//_allowSkills.add(33);	//Summon Phantom Cubic
		//_allowSkills.add(22);	//Summon Vampiric Cubic
		//_allowSkills.add(278);	//Summon Viper Cubic
		_allowSkills.add(279);	//Lightning Strike
		_allowSkills.add(289);	//Life Leech
		_allowSkills.add(223);	//Sting
		_allowSkills.add(115);	//Power Break
		_allowSkills.add(122);	//Hex
		//_allowSkills.add(110);	//Ultimate Defense
		_allowSkills.add(402);	//Arrest
		_allowSkills.add(401);	//Judgment
				
		//skill 3rd
		_allowSkills.add(335);	//Fortitude
		_allowSkills.add(352);	//Shield Bash
		_allowSkills.add(368);	//Vengeance
		_allowSkills.add(351);	//Magical Mirror
		_allowSkills.add(342);	//Touch of Death
		_allowSkills.add(454);	//Symbol of Defense
		_allowSkills.add(789);	//Spirit of Shilen
		
		super.prepareSkillsSetup();
	}
	

}

