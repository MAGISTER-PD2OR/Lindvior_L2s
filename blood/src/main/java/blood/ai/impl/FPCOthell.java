package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Player;

public class FPCOthell extends WarriorPC
{
	public final int SKILL_ANGEL_OF_DEATH 	= 10531;
	public final int SKILL_FINAL_UE			= 10520;
	public final int SKILL_MAXIMUM_BLOW		= 10560; // human
	public final int SKILL_ELUSIVE_MIRAGE	= 10558; // human
	public final int SKILL_MORTAL_STRIKE	= 410; // ELF, DE
	public final int SKILL_SHADOW_DODGE		= 10606; // ELF
	public final int SKILL_MELEE_REFLECT	= 10653; // DE
	public final int SKILL_CRITICAL_ADVEN	= 10562;
	public final int SKILL_SHADOW_DASH		= 10525;
	public final int SKILL_SHADOW_HIDE		= 10517;
	public final int SKILL_EVASION_COUNTER	= 10524;
	
	public final int SKILL_DAGGER_EXPLOSION	= 10512;
	public final int SKILL_BLOOD_STAB		= 10508;
	public final int SKILL_HEART_BREAKER	= 10509;
	public final int SKILL_REVERSE			= 10511;
	public final int SKILL_CHAIN_BLOW		= 10510;
	public final int SKILL_RAZOR_RAIN		= 10513; // 
	public final int SKILL_CLONE_ATTACT		= 10532;
	public final int SKILL_MUG 				= 10700;
	public final int SKILL_PLUNDER			= 10702;

	public final int SKILL_CRITICAL_WOUND	= 531;
	public final int SKILL_THROW_DAGGER		= 10539;
	public final int SKILL_POWER_BLUFF		= 10554;
	public final int SKILL_POISON_ZONE		= 10522;
	public final int SKILL_THROW_SAND		= 10540;
	public final int SKILL_SHADOW_CHASE		= 10516;
	public final int SKILL_DARK_PARALYSIS	= 10514;
	public final int SKILL_KICK				= 10549;
	public final int SKILL_UPPERCUT			= 10546; // lv 89






	public FPCOthell(Player actor)
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

