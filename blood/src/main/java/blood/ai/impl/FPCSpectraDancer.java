package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Player;

public class FPCSpectraDancer extends TankerPC
{
	public FPCSpectraDancer(Player actor)
	{
		super(actor);
		
	}
	
	protected boolean thinkBuff()
	{
		if(thinkBuff(new int[] {
			//428, // Inner Rhythm
			271, //	Dance of Warrior
			//310, // Dance of the Vampire
			275, // Dance of Fury
			//274, // Dance of Fire
			//272, // Dance of Inspiration
			273, // Dance of the Mystic
			276  // Dance of Contrentration
		}))
			return true;
		
		return super.thinkBuff();
	}
	
	@Override public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		SkillList.add(2);	//Confusion
		SkillList.add(105);	//Freezing Strike
		SkillList.add(122);	//Hex
		SkillList.add(223);	//Sting
		SkillList.add(408);	//Demonic Blade Dance
		SkillList.add(84);	//Poison Blade Dance
		
		//skill 3rd
		SkillList.add(367);	//Dance of Medusa
		SkillList.add(455);	//Symbol of Noise		
		
		return SkillList;
	}
	
	@Override
	protected ArrayList<Integer> getDrawTargetSkill()
	{
		ArrayList<Integer>	SkillList	= new ArrayList<Integer>();
		
		SkillList.add(28); //Aggression
		SkillList.add(402); //Arrest
		
		return SkillList;
	}


	@Override
	public int getRateDAM()
	{
		return 0;
	}

	@Override
	public int getRateSTUN()
	{
		return 0;
	}

	@Override
	public int getRateBUFF()
	{
		return 0;
	}
}

