package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Player;

public class FPCYul extends RangerPC
{
	public FPCYul(Player actor)
	{
		super(actor);
	}

	@Override
	protected boolean thinkBuff()
	{
		if(thinkBuff(new int[] {
			10757
		}))
			return true;
		
		return super.thinkBuff();
	}
	
	@Override public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		
		// skill 4th
		// buff
		SkillList.add(10757);	// dead eye stance
		// damage skills
		SkillList.add(10760);	// tornado shot
		SkillList.add(10761);	// bow strike
		SkillList.add(10762);	// quick shot
		SkillList.add(10763);	// pinpoint shot
		SkillList.add(10769);	// impact shot
		SkillList.add(10771);	// multiple arrow
		SkillList.add(10772);	// heavy arrow rain
		
//		SkillList.add(10787);	// summon hawk
		
		
		return SkillList;
	}
	
	@Override
	public int getRatePHYS()
	{
		return 50;
	}

	@Override
	public int getRateDOT()
	{
		return 0;
	}

	@Override
	public int getRateDEBUFF()
	{
		return 0;
	}

	@Override
	public int getRateDAM()
	{
		return 50;
	}

	@Override
	public int getRateSTUN()
	{
		return 0;
	}

	@Override
	public int getRateBUFF()
	{
		return 50;
	}

	@Override
	public int getRateHEAL()
	{
		return 0;
	}
	
}

