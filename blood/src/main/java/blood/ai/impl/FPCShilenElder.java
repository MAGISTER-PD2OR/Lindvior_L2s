package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Player;

public class FPCShilenElder extends HealerPC
{
	Player _actor;
	public FPCShilenElder(Player actor)
	{
		super(actor);
		_actor = actor;
		
	}
	
	@Override public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		SkillList.add(1539);	//Stigma of Shilen
		SkillList.add(1531);	//Bless the Blood 
		//SkillList.add(1201);	//Dryad Root
		//SkillList.add(1206);	//Wind Shackle
		SkillList.add(1219);	//Greater Group Heal
		SkillList.add(1217);	//Greater Heal
		//SkillList.add(1398);	//Mana Burn
		
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

