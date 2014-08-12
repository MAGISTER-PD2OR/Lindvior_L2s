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
	
	@Override public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		SkillList.add(28);	//Aggression
		SkillList.add(18);	//Aura of Hate
		SkillList.add(10);	//Summon Storm Cubic
		SkillList.add(67);	//Summon Life Cubic
		SkillList.add(449);	//Summon Attractive Cubic
		SkillList.add(92);	//Shield Stun
		//SkillList.add(110);	//Ultimate Defense
		SkillList.add(402);	//Arrest
		SkillList.add(400);	//Tribunal
				
		//skill 3rd
		SkillList.add(335);	//Fortitude
		SkillList.add(352);	//Shield Bash
		SkillList.add(368);	//Vengeance
		SkillList.add(351);	//Magical Mirror
		SkillList.add(779);	//Summon Smart Cubic
		SkillList.add(454);	//Symbol of Defense
		
		return SkillList;
	}
	
	@Override
	protected ArrayList<Integer> getDrawTargetSkill()
	{
		ArrayList<Integer>	SkillList	= new ArrayList<Integer>();
		
		SkillList.add(28); //Aggression
		SkillList.add(402); //Arrest
		
		return SkillList;
	}
}

