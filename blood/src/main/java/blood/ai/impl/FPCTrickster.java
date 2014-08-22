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
	
	public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		_allowSkills.add(490);	//Fast Shot
		_allowSkills.add(507);	//Twin Shot
		_allowSkills.add(509);	//Bleeding Shot
		_allowSkills.add(508);	//Rising Shot
		_allowSkills.add(622);	//Ultimate Escape
		_allowSkills.add(525);	//Decoy
		_allowSkills.add(522);	//Real Target
		_allowSkills.add(521);	//Sharp Shooting
		_allowSkills.add(510);	//Soul Cleanse
		
		//skill 3rd
		_allowSkills.add(792);	//Betrayal Mark
		_allowSkills.add(790);	//Wild Shot
		_allowSkills.add(358);	//Final Form
		
		return SkillList;
	}
	
}

