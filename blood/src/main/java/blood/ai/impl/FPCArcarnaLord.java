package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Player;

public class FPCArcarnaLord extends SummonerPC
{
	public FPCArcarnaLord(Player actor)
	{
		super(actor);
		
	}
	
	@Override
	protected boolean thinkBuff()
	{
		if(thinkBuff(new int[] {1279, 10, 1547 })) // summon cubic
			return true;
		
		return super.thinkBuff();
	}
	
	@Override
	protected boolean thinkSummon()
	{
		if(thinkSummon(1276)) //summon Kai the Cat
			return true;
		
		return false;
	}
	
	@Override public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		//SkillList.add(1184);	//Ice Bolt
		//SkillList.add(1279);	//Summon Binding Cubic
		//SkillList.add(10);		//Summon Storm Cubic
		SkillList.add(1558);	//Dimension Spiral
		SkillList.add(1386);	//Arcane Disruption
		
		//skill 3rd
		SkillList.add(1350);	//Warrior Bane
		SkillList.add(1351);	//Mage Bane
		SkillList.add(1346);	//Warrior Servitor
		SkillList.add(1349);	//Final Servitor	
		
		return SkillList;
	}
	

}
