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
	
	public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		_allowSkills.add(1206);	//Wind Shackle
		_allowSkills.add(1219);	//Greater Group Heal
		_allowSkills.add(1217);	//Greater Heal
		//_allowSkills.add(1069);	//Sleep
		_allowSkills.add(1018);	//Purify
		_allowSkills.add(1401);	//Major Heal
		_allowSkills.add(1398);	//Mana Burn
		_allowSkills.add(1394);	//Trance
		
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
		
		_allowSkills.add(1218); //greater heal
		_allowSkills.add(1219); //Greater Group Heal
		_allowSkills.add(1020); //vitalize
		_allowSkills.add(1401); //Major Heal
		return SkillList;
	}
}

