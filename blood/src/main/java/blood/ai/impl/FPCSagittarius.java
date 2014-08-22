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
	
	public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		_allowSkills.add(19);	//Double Shot
		_allowSkills.add(101);	//Stun Shot
		//_allowSkills.add(303);	//Soul of Sagittarius
		_allowSkills.add(24);	//Burst Shot
		_allowSkills.add(99);	//Rapid Shot
		//_allowSkills.add(131);	//Hawkeye
		//_allowSkills.add(416);	//Blessing of Sagittarius 
		//_allowSkills.add(415);	//Spirit of Sagittarius
		_allowSkills.add(313);	//Snipe
		//_allowSkills.add(111);	//Ultimate Evasion
		
		//skill 3rd
		_allowSkills.add(343);	//Lethal Shot
		_allowSkills.add(354);	//Hamstring Shot
		_allowSkills.add(459);	//Symbol of the Sniper
		_allowSkills.add(924);	//Seven Arrow
		_allowSkills.add(771);	//Flame Hawk
		
		return SkillList;
	}
	
}

