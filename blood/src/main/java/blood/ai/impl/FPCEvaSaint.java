package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Player;

public class FPCEvaSaint extends HealerPC
{
	Player _actor;
	public FPCEvaSaint(Player actor)
	{
		super(actor);
		_actor = actor;
		
	}
	
	@Override public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		SkillList.add(1206);	//Wind Shackle
		SkillList.add(1219);	//Greater Group Heal
		SkillList.add(1217);	//Greater Heal
		//SkillList.add(1069);	//Sleep
		SkillList.add(1018);	//Purify
		SkillList.add(1401);	//Major Heal
		SkillList.add(1398);	//Mana Burn
		SkillList.add(1394);	//Trance
		
		return SkillList;
	}
	
	@Override
	protected ArrayList<Integer> getCriticalHealSkill()
	{
		return getNormalHealSkill();
	}
	
	@Override
	protected ArrayList<Integer> getNormalHealSkill()
	{
		ArrayList<Integer>	SkillList	= new ArrayList<Integer>();
		
		SkillList.add(1218); //greater heal
		SkillList.add(1219); //Greater Group Heal
		SkillList.add(1020); //vitalize
		SkillList.add(1401); //Major Heal
		return SkillList;
	}
}

