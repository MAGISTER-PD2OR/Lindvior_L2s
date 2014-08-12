package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Player;

public class FPCArchmage extends MysticPC
{
	public FPCArchmage(Player actor)
	{
		super(actor);
	}

	@Override
	protected boolean thinkBuff()
	{
		if(thinkBuff(new int[] {
			1232, //Blazing Skin
			1285 //Seed of Fire
		}))
			return true;
		
		return super.thinkBuff();
	}
	
	@Override public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		//SkillList.add(1171);	//Blazing Circle
		SkillList.add(1169);	//Curse Fear
		SkillList.add(1230);	//Prominence 
		SkillList.add(1231);	//Aura Flare
		//SkillList.add(1069);	//Sleep
		//SkillList.add(1160);	//Slow
		SkillList.add(1083);	//Surrender to Fire
		SkillList.add(1056);	//Cancelation
		SkillList.add(1417);	//Aura Flash
		SkillList.add(1296);	//Rain of Fire
		SkillList.add(1288);	//Aura Symphony
		SkillList.add(1284);	//Aura Symphon
		SkillList.add(1232);	//Aura Symphon
		SkillList.add(1289);	//Inferno
				
		//skill 3rd
		SkillList.add(1339);	//Fire Vortex
		SkillList.add(1452);	//Count of Fire
		SkillList.add(1451);	//Fire Vortex Burster
		SkillList.add(1419);	//Vocalno
		SkillList.add(1467);	//Meteor
		
		return SkillList;
	}
	
}

