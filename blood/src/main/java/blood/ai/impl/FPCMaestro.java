package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Player;

public class FPCMaestro extends WarriorPC
{
	public FPCMaestro(Player actor)
	{
		super(actor);
		
	}
	
	@Override
	protected boolean thinkBuff()
	{
		if(thinkBuff(new int[] {
			1561, //Battle cry
			826, //Spike
			440, //Braveheart
			778 //Golden Armor
		}))
			return true;
		
		return super.thinkBuff();
	}
	
	
	@Override
	protected boolean thinkSummon()
	{
		if(thinkSummon(25)) //summon Merchanic Golem
			return true;
		
		return super.thinkSummon();
	}

	
	@Override public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		SkillList.add(190);	//Fatal Strike
		//SkillList.add(36);	//Whirlwind
		SkillList.add(260);	//Hammer Crush
		SkillList.add(320);	//Wrath
		SkillList.add(994);	//Rush
		//SkillList.add(25);	//Summon mechanic golem
		//SkillList.add(301);	//Summon big boom
		
		//skill 3rd
		SkillList.add(362);	//Armor Crush
		SkillList.add(347);	//Earthquake
		SkillList.add(457);	//Symbol of Honnor
		SkillList.add(917);	//Final Secret
		SkillList.add(995);	//Rush Impact
		
		return SkillList;
	}
	

}
