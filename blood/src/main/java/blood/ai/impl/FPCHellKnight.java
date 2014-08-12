package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Player;

public class FPCHellKnight extends TankerPC
{
	public FPCHellKnight(Player actor)
	{
		super(actor);
		
	}
	
	@Override
	protected boolean thinkBuff()
	{
		if(thinkBuff(new int[] {
			82, //	Majesty
			86 // Reflect Damage
		}))
			return true;
		
		return super.thinkBuff();
	}
	
	@Override
	protected boolean thinkSummon()
	{
		if(thinkSummon(283)) //summon Dark Panther
			return true;
		
		return super.thinkSummon();
	}
	
	@Override public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		SkillList.add(28);	//Aggression
		SkillList.add(18);	//Aura of Hate
		//SkillList.add(70);	//Drain Health
		//SkillList.add(283);	//Summon Dark Panther
		//SkillList.add(82);	//Majesty
		//SkillList.add(86);	//Reflect Damage
		//SkillList.add(127);	//Hamstring
		SkillList.add(65);	//Horror
		SkillList.add(92);	//Shield Stun
		//SkillList.add(110);	//Ultimate Defense
		SkillList.add(403);	//Shackle
		SkillList.add(404);	//Mass Shackling
		SkillList.add(401);	//Judgment
				
		//skill 3rd
		SkillList.add(353);	//Shield Slam
		SkillList.add(368);	//Vengeance
		SkillList.add(350);	//Physical Mirror
		SkillList.add(442);	//Touch of Death
		SkillList.add(454);	//Symbol of Defense
		SkillList.add(439);	//Shield of Revenge
		SkillList.add(763);	//Hell Scream
		
		return SkillList;
	}
	
	@Override
	protected List<Integer> getPetSkillList()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		
		
		return SkillList;
	}
	
	@Override
	protected ArrayList<Integer> getDrawTargetSkill()
	{
		ArrayList<Integer>	SkillList	= new ArrayList<Integer>();
		
		SkillList.add(28); //Aggression
		SkillList.add(403); //Shackle
		
		return SkillList;
	}
}

