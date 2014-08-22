package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Player;

public class FPCTyr extends WarriorPC
{
	public FPCTyr(Player actor)
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
	
	public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		
		// skill 4th
		// buff
		_allowSkills.add(10757);	// dead eye stance
		// damage skills
		_allowSkills.add(10760);	// tornado shot
		_allowSkills.add(10761);	// bow strike
		_allowSkills.add(10762);	// quick shot
		_allowSkills.add(10763);	// pinpoint shot
		_allowSkills.add(10769);	// impact shot
		_allowSkills.add(10771);	// multiple arrow
		_allowSkills.add(10772);	// heavy arrow rain
		
//		_allowSkills.add(10787);	// summon hawk
		
		
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

