package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Player;

public class FPCEvaTemplar extends TankerPC
{
	public FPCEvaTemplar(Player actor)
	{
		super(actor);
		
	}

	@Override
	protected boolean thinkBuff()
	{
		if(thinkBuff(new int[] {10, 67, 449})) // summon cubic
			return true;
		
		return super.thinkBuff();
	}
	
	public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		_allowSkills.add(28);	//Aggression
		_allowSkills.add(18);	//Aura of Hate
		_allowSkills.add(10);	//Summon Storm Cubic
		_allowSkills.add(67);	//Summon Life Cubic
		_allowSkills.add(449);	//Summon Attractive Cubic
		_allowSkills.add(92);	//Shield Stun
		//_allowSkills.add(110);	//Ultimate Defense
		_allowSkills.add(402);	//Arrest
		_allowSkills.add(400);	//Tribunal
				
		//skill 3rd
		_allowSkills.add(335);	//Fortitude
		_allowSkills.add(352);	//Shield Bash
		_allowSkills.add(368);	//Vengeance
		_allowSkills.add(351);	//Magical Mirror
		_allowSkills.add(779);	//Summon Smart Cubic
		_allowSkills.add(454);	//Symbol of Defense
		
		return SkillList;
	}
	
	@Override
	protected ArrayList<Integer> getDrawTargetSkill()
	{
		ArrayList<Integer>	SkillList	= new ArrayList<Integer>();
		
		_allowSkills.add(28); //Aggression
		_allowSkills.add(402); //Arrest
		
		return SkillList;
	}
}

