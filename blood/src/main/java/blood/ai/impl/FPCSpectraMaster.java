package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Player;

public class FPCSpectraMaster extends SummonerPC
{
	public FPCSpectraMaster(Player actor)
	{
		super(actor);
		
	}
	
	@Override
	protected boolean thinkBuff()
	{
		if(thinkBuff(new int[] {33, 1281})) // summon cubic
			return true;
		
		return super.thinkBuff();
	}
	
	@Override
	protected boolean thinkSummon()
	{
		if(thinkSummon(1278)) //summon Soulless
			return true;
		
		return super.thinkSummon();
	}

	@Override public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		//SkillList.add(33);		//Summon Phantom Cubic
		//SkillList.add(1281);	//Summon Spark Cubic
		SkillList.add(1206);	//Wind Shackle
		SkillList.add(1530);	//Death Spike
		
		//skill 3rd
		SkillList.add(1348);	//Assassin Servitor
		SkillList.add(1351);	//Mage Bane
		SkillList.add(1408);	//Summon Spectral Servitor
		SkillList.add(1349);	//Final Servitor	
		
		return SkillList;
	}
	

}
