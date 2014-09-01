package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Player;

public class FPCJudicator extends WarriorPC
{
	public FPCJudicator(Player actor)
	{
		super(actor);
	}

	@Override
	protected boolean thinkBuff()
	{
		if(thinkUseKamaelSoul(502,10))
			return true;
		
		return super.thinkBuff();
	}	
	
	public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		_allowSkills.add(504);		//Triple Thurst
		_allowSkills.add(1481);	//Obvilion
		_allowSkills.add(1483);	//Thin Skin
		_allowSkills.add(505);		//Shining Edge
		_allowSkills.add(492);		//Spread Wing
		_allowSkills.add(1482);	//Weak Constitution
		_allowSkills.add(1484);	//Enervation 
		_allowSkills.add(1485);	//Spite
		_allowSkills.add(1486);	//Mental Impoverish
		
		//skill 3rd
		_allowSkills.add(939);		//Soul Rage
		_allowSkills.add(1515);	//Lightning Barrier
		_allowSkills.add(358);		//Final Form
		
		return SkillList;
	}
	
	
}

