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
	
	@Override public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		SkillList.add(1436);	//Soul of Pain
		SkillList.add(1439);	//Curse of Divinity
		SkillList.add(1440);	//Steal of Divinity
		SkillList.add(504);		//Triple Thurst
		SkillList.add(1437);	//Dark Flame
		SkillList.add(505);		//Shining Edge
		SkillList.add(1446);	//Shadow Bind
		SkillList.add(1435);	//Death Mark
		//SkillList.add(492);		//Spread Wing
		//SkillList.add(1438);	//Annihilation Circle
		//SkillList.add(1447);	//Void bind
		SkillList.add(1448);	//Blink
		SkillList.add(506);		//Checkmate
		SkillList.add(1511);	//Curse of Life Flow
		SkillList.add(1443);	//Dark Weapon
		SkillList.add(1444);	//Pride of Kamael
		SkillList.add(628);		//Warp
		SkillList.add(837);		//Pain Killer
		//SkillList.add(1529);	//Soul Web
		
		//skill 3rd
		SkillList.add(1516);	//Soul Strike
		SkillList.add(1512);	//Soul Vortex
		SkillList.add(1513);	//Soul Vortex Extinction
		SkillList.add(939);		//Soul Rage
		SkillList.add(1469);	//Leopold
		SkillList.add(358);		//Final Form
		
		return SkillList;
	}
	
	
}

