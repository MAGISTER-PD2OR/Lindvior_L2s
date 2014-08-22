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
	
	public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		//_allowSkills.add(1171);	//Blazing Circle
		_allowSkills.add(1169);	//Curse Fear
		_allowSkills.add(1230);	//Prominence 
		_allowSkills.add(1231);	//Aura Flare
		//_allowSkills.add(1069);	//Sleep
		//_allowSkills.add(1160);	//Slow
		_allowSkills.add(1083);	//Surrender to Fire
		_allowSkills.add(1056);	//Cancelation
		_allowSkills.add(1417);	//Aura Flash
		_allowSkills.add(1296);	//Rain of Fire
		_allowSkills.add(1288);	//Aura Symphony
		_allowSkills.add(1284);	//Aura Symphon
		_allowSkills.add(1232);	//Aura Symphon
		_allowSkills.add(1289);	//Inferno
				
		//skill 3rd
		_allowSkills.add(1339);	//Fire Vortex
		_allowSkills.add(1452);	//Count of Fire
		_allowSkills.add(1451);	//Fire Vortex Burster
		_allowSkills.add(1419);	//Vocalno
		_allowSkills.add(1467);	//Meteor
		
		return SkillList;
	}
	
}

