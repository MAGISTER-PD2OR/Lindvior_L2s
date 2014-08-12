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
	
	@Override public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		SkillList.add(504);		//Triple Thurst
		SkillList.add(1481);	//Obvilion
		SkillList.add(1483);	//Thin Skin
		SkillList.add(505);		//Shining Edge
		SkillList.add(492);		//Spread Wing
		SkillList.add(1482);	//Weak Constitution
		SkillList.add(1484);	//Enervation 
		SkillList.add(1485);	//Spite
		SkillList.add(1486);	//Mental Impoverish
		
		//skill 3rd
		SkillList.add(939);		//Soul Rage
		SkillList.add(1515);	//Lightning Barrier
		SkillList.add(358);		//Final Form
		
		return SkillList;
	}
	
	
}

