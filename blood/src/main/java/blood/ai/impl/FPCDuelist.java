package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Player;

public class FPCDuelist extends WarriorPC
{
	public FPCDuelist(Player actor)
	{
		super(actor);
	}

	@Override
	protected boolean thinkBuff()
	{
		if(thinkUseWarriorForce(8))
			return true;
		
		if(thinkBuff(new int[] {78, 287})) // Majesty, Deflect Arrow
			return true;
		
		return super.thinkBuff();
	}
	
	@Override public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		SkillList.add(190);	//Fatal Strike
		SkillList.add(8);	//Sonic Focus
		SkillList.add(1);	//Tripple Slash
		//SkillList.add(260);	//Hammer Crush
		//SkillList.add(6);	//Sonic Blaster
		//SkillList.add(9);	//Sonic Buster
		//SkillList.add(78);	//War Cry
		SkillList.add(5);	//Double Sonic Slash
		SkillList.add(7);	//Sonic Storm
		//SkillList.add(287);	//Lion Heart
		SkillList.add(261);	//Triple Sonic Slash
		//SkillList.add(424);	//War Frenzy
		SkillList.add(451);	//Sonic Move
		SkillList.add(297);	//Duelist Spirit
		
		//skill 3rd
		SkillList.add(340);	//Riposte Stance
		SkillList.add(345);	//Sonic Rage
		SkillList.add(440);	//Braveheart
		SkillList.add(360);	//Eye of Slayer
		SkillList.add(442);	//Sonic Barrier
		SkillList.add(458);	//Symbol of Energy
		SkillList.add(917);	//Final Secret
		SkillList.add(775);	//Weapon Blockade
		SkillList.add(758);	//Fighter Will
		SkillList.add(919);	//Maximum Sonic Focus
		
		return SkillList;
	}
	
}

