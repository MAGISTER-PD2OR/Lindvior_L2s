package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Player;

public class FPCSagittarius extends RangerPC
{
	public FPCSagittarius(Player actor)
	{
		super(actor);
	}


	@Override
	protected boolean thinkBuff()
	{
		if(thinkBuff(new int[] {
			99, //	Rapid Shot
			//303, // Soul of Sagittarius
			131, // Hawkeye
			416, // Blessing of Sagittarius
			415, // Spirit of Sagittarius
		}))
			return true;
		
		return super.thinkBuff();
	}
	
	@Override public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		SkillList.add(19);	//Double Shot
		SkillList.add(101);	//Stun Shot
		//SkillList.add(303);	//Soul of Sagittarius
		SkillList.add(24);	//Burst Shot
		SkillList.add(99);	//Rapid Shot
		//SkillList.add(131);	//Hawkeye
		//SkillList.add(416);	//Blessing of Sagittarius 
		//SkillList.add(415);	//Spirit of Sagittarius
		SkillList.add(313);	//Snipe
		//SkillList.add(111);	//Ultimate Evasion
		
		//skill 3rd
		SkillList.add(343);	//Lethal Shot
		SkillList.add(354);	//Hamstring Shot
		SkillList.add(459);	//Symbol of the Sniper
		SkillList.add(924);	//Seven Arrow
		SkillList.add(771);	//Flame Hawk
		
		return SkillList;
	}
	
}

