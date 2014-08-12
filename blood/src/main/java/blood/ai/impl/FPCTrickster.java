package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Player;

public class FPCTrickster extends RangerPC
{
	public FPCTrickster(Player actor)
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
		SkillList.add(490);	//Fast Shot
		SkillList.add(507);	//Twin Shot
		SkillList.add(509);	//Bleeding Shot
		SkillList.add(508);	//Rising Shot
		SkillList.add(622);	//Ultimate Escape
		SkillList.add(525);	//Decoy
		SkillList.add(522);	//Real Target
		SkillList.add(521);	//Sharp Shooting
		SkillList.add(510);	//Soul Cleanse
		
		//skill 3rd
		SkillList.add(792);	//Betrayal Mark
		SkillList.add(790);	//Wild Shot
		SkillList.add(358);	//Final Form
		
		return SkillList;
	}
	
}

