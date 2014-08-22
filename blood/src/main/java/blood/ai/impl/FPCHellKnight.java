package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Player;

public class FPCHellKnight extends TankerPC
{
	public FPCHellKnight(Player actor)
	{
		super(actor);
		
		//skill 2nd
		_allowSkills.add(28);	//Aggression
		_allowSkills.add(18);	//Aura of Hate
		//_allowSkills.add(70);	//Drain Health
		_allowSkills.add(283);	//Summon Dark Panther
		_allowSkills.add(82);	//Majesty
		_allowSkills.add(86);	//Reflect Damage
		//_allowSkills.add(127);	//Hamstring
		_allowSkills.add(65);	//Horror
		_allowSkills.add(92);	//Shield Stun
		//_allowSkills.add(110);	//Ultimate Defense
		_allowSkills.add(403);	//Shackle
		_allowSkills.add(404);	//Mass Shackling
		_allowSkills.add(401);	//Judgment
				
		//skill 3rd
		_allowSkills.add(353);	//Shield Slam
		_allowSkills.add(368);	//Vengeance
		_allowSkills.add(350);	//Physical Mirror
		_allowSkills.add(442);	//Touch of Death
		_allowSkills.add(454);	//Symbol of Defense
		_allowSkills.add(439);	//Shield of Revenge
		_allowSkills.add(763);	//Hell Scream
	}
	
	@Override
	protected ArrayList<Integer> getDrawTargetSkill()
	{
		ArrayList<Integer>	SkillList	= new ArrayList<Integer>();
		
		_allowSkills.add(28); //Aggression
		_allowSkills.add(403); //Shackle
		
		return SkillList;
	}
}

