package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Player;

public class FPCGhostSentinel extends RangerPC
{
	public FPCGhostSentinel(Player actor)
	{
		super(actor);
	}

	@Override
	protected boolean thinkBuff()
	{
		if(thinkBuff(new int[] {
			99, //	Rapid Shot
			//303, // Soul of Sagittarius
			415, // Spirit of Sagittarius
		}))
			return true;
		
		return super.thinkBuff();
	}
	
	@Override 
	public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		SkillList.add(19);	//Double Shot
		SkillList.add(101);	//Stun Shot
		//SkillList.add(303);	//Soul of Sagittarius
		SkillList.add(24);	//Burst Shot
		//SkillList.add(99);	//Rapid Shot
		//SkillList.add(415);	//Spirit of Sagittarius
		SkillList.add(414);	//Dead Eye
		SkillList.add(314);	//Fatal Counter
		SkillList.add(111);	//Ultimate Evasion
		
		//skill 3rd
		SkillList.add(343);	//Lethal Shot
		SkillList.add(354);	//Hamstring Shot
		SkillList.add(369);	//Evade Shot
		SkillList.add(459);	//Symbol of the Sniper
		SkillList.add(924);	//Seven Arrow
		SkillList.add(773);	//God Piercing
		
		return SkillList;
	}
	
}

