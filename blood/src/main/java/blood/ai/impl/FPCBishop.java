package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Player;

public class FPCBishop extends HealerPC
{
	Player _actor;
	public FPCBishop(Player actor)
	{
		super(actor);
		_actor = actor;
		
	}
	
	@Override public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		SkillList.add(1218);	//Greater Battle Heal 
		SkillList.add(1219);	//Greater Group Heal
		SkillList.add(1217);	//Greater Heal
		//SkillList.add(1069);	//Sleep
		SkillList.add(1018);	//Purify
		SkillList.add(1258);	//Restore Life
		SkillList.add(1311);	//Body Avatar
		//SkillList.add(1396);	//Magic Backfire
		SkillList.add(1401);	//Major Heal
		//SkillList.add(1398);	//Mana Burn
		SkillList.add(1394);	//Trance
		SkillList.add(1402);	//Major Group Heal
		//SkillList.add(1399);	//Mana Storm
		//SkillList.add(1418);	//Celestial Shield
		SkillList.add(1271);	//Benediction
		
		return SkillList;
	}
	
	@Override
	protected ArrayList<Integer> getCriticalHealSkill()
	{
		
		ArrayList<Integer>	SkillList	= new ArrayList<Integer>();
		
		SkillList.add(1218); //Greater Battle Heal
		SkillList.add(1418); //Celestial Shield
		
		return SkillList;
		
	}
	
	@Override
	protected ArrayList<Integer> getNormalHealSkill()
	{
		ArrayList<Integer>	SkillList	= new ArrayList<Integer>();
		
		SkillList.add(1218); //Greater Heal
		SkillList.add(1219); //Greater Group Heal
		SkillList.add(1401); //Major Heal
		SkillList.add(1402); //Major Group Heal
		SkillList.add(1020); //vitalize
		SkillList.add(1258); //Restore Life
		
		return SkillList;
	}
}

