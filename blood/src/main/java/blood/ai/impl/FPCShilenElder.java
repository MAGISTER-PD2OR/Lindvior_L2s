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
	
	public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		_allowSkills.add(1539);	//Stigma of Shilen
		_allowSkills.add(1531);	//Bless the Blood 
		//_allowSkills.add(1201);	//Dryad Root
		//_allowSkills.add(1206);	//Wind Shackle
		_allowSkills.add(1219);	//Greater Group Heal
		_allowSkills.add(1217);	//Greater Heal
		//_allowSkills.add(1398);	//Mana Burn
		
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

