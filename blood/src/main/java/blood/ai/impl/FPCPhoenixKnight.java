package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Player;

public class FPCPhoenixKnight extends TankerPC
{
	public FPCPhoenixKnight(Player actor)
	{
		super(actor);
	}

	@Override
	protected boolean thinkBuff()
	{
		if(thinkBuff(new int[] {
			82, 		//Majesty
			982			//Combat Aura
		}))
			return true;
		
		return super.thinkBuff();
	}
	
	@Override public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		SkillList.add(28);	//Aggression
		SkillList.add(18);	//Aura of Hate
		//SkillList.add(82);	//Majesty
		SkillList.add(92);	//Shield Stun
		//SkillList.add(110);	//Ultimate Defense
		SkillList.add(403);	//Shackle
		//SkillList.add(404);	//Mass Shackling
		SkillList.add(400);	//Tribunal
		SkillList.add(406);	//Angelic Icon
		SkillList.add(984);	//Shield Strike
				
		//skill 3rd
		SkillList.add(353);	//Shield Slam
		SkillList.add(368);	//Vengeance
		SkillList.add(350);	//Physical Mirror
		SkillList.add(438);	//Soul of the Phoenix
		SkillList.add(454);	//Symbol of Defense
		SkillList.add(784);	//Spirit of Phoenix
		SkillList.add(785);	//Flame Icon
		
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

