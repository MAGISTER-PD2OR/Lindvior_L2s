package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Player;

public class FPCMaleSoulhound extends WarriorPC
{
	public FPCMaleSoulhound(Player actor)
	{
		super(actor);
	}


	@Override
	protected boolean thinkBuff()
	{
		if(thinkUseKamaelSoul(502, 10))
			return true;
		
		return super.thinkBuff();
	}
	
	public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		_allowSkills.add(1436);	//Soul of Pain
		_allowSkills.add(1439);	//Curse of Divinity
		_allowSkills.add(1440);	//Steal of Divinity
		_allowSkills.add(504);		//Triple Thurst
		_allowSkills.add(1437);	//Dark Flame
		_allowSkills.add(505);		//Shining Edge
		_allowSkills.add(1446);	//Shadow Bind
		_allowSkills.add(1435);	//Death Mark
		//_allowSkills.add(492);		//Spread Wing
		//_allowSkills.add(1438);	//Annihilation Circle
		//_allowSkills.add(1447);	//Void bind
		_allowSkills.add(1448);	//Blink
		_allowSkills.add(506);		//Checkmate
		_allowSkills.add(1511);	//Curse of Life Flow
		_allowSkills.add(1443);	//Dark Weapon
		_allowSkills.add(1444);	//Pride of Kamael
		_allowSkills.add(628);		//Warp
		_allowSkills.add(837);		//Pain Killer
		//_allowSkills.add(1529);	//Soul Web
		
		//skill 3rd
		_allowSkills.add(1516);	//Soul Strike
		_allowSkills.add(1512);	//Soul Vortex
		_allowSkills.add(1513);	//Soul Vortex Extinction
		_allowSkills.add(939);		//Soul Rage
		_allowSkills.add(1469);	//Leopold
		_allowSkills.add(358);		//Final Form
		
		return SkillList;
	}
	
	
}

