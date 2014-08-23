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
	
	public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		_allowSkills.add(1218);	//Greater Battle Heal 
		_allowSkills.add(1219);	//Greater Group Heal
		_allowSkills.add(1217);	//Greater Heal
		//_allowSkills.add(1069);	//Sleep
		_allowSkills.add(1018);	//Purify
		_allowSkills.add(1258);	//Restore Life
		_allowSkills.add(1311);	//Body Avatar
		//_allowSkills.add(1396);	//Magic Backfire
		_allowSkills.add(1401);	//Major Heal
		//_allowSkills.add(1398);	//Mana Burn
		_allowSkills.add(1394);	//Trance
		_allowSkills.add(1402);	//Major Group Heal
		//_allowSkills.add(1399);	//Mana Storm
		//_allowSkills.add(1418);	//Celestial Shield
		_allowSkills.add(1271);	//Benediction
		
		return SkillList;
	}
	
}

