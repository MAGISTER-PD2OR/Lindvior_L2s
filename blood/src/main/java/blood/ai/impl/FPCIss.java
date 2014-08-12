package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Player;

public class FPCIss extends WarriorPC
{
	public FPCIss(Player actor)
	{
		super(actor);
	}

	@Override
	protected boolean thinkBuff()
	{
		if(thinkBuff(new int[] {
			11517, // Horn melody
			11518, // Drum molody
			11519, // organ melody
			11520, // ghita melody
			11521, // harp melody
			11522, // lute melody
			11523, // knight harmony
			11529, // prevail sonata
			11530, // daring sonata
			11532, // refreshing sonata
		}))
			return true;
		
		return super.thinkBuff();
	}
	
	@Override public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		
		// skill 4th
		// buff
		SkillList.add(11517);	//assault rush
		SkillList.add(11518);	//assault rush
		SkillList.add(11519);	//assault rush
		SkillList.add(11520);	//assault rush
		SkillList.add(11521);	//assault rush
		SkillList.add(11522);	//assault rush
		SkillList.add(11523);	//assault rush
		SkillList.add(11529);
		SkillList.add(11530);
		SkillList.add(11532);
		// damage skills
		SkillList.add(11508);	//assault rush
		SkillList.add(11509);	//crippling attack
		SkillList.add(11510);	//shadow blade
		SkillList.add(11511);	//death strike
//		SkillList.add(11513);	//mass crip attack
//		SkillList.add(11514);	//mass shadow blade
		
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

