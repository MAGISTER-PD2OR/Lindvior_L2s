package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Creature;
import l2s.gameserver.model.Player;

public class FPCTitan extends WarriorPC
{
	public FPCTitan(Player actor)
	{
		super(actor);
	}

	@Override
	protected void onEvtAttacked(Creature attacker, int damage)
	{
		super.onEvtAttacked(attacker, damage);
		Player actor = getActor();
		if(actor.isDead() || attacker == null || actor.getDistance(attacker) > 700)
			return;

		if(actor.isMoving)
			return;
		
		int hpLevel	= (int) actor.getCurrentHpPercents();
		
		if(hpLevel < 30)
		{
//			try
//			{
//				//cast Guts
//				if(!actor.getEffectList().containEffectFromSkills(new int[] { 139 }))
//					selfBuff(139);
//				
//				//cast Frenzy
//				if(!actor.getEffectList().containEffectFromSkills(new int[] { 176 }))
//					selfBuff(176);
//				
//				//cast Zealot
//				if(!actor.getEffectList().containEffectFromSkills(new int[] { 420 }))
//					selfBuff(420);
//			}
//			catch(Exception e){}
			
			if(hpLevel < 10)
			{
				try
				{
					//cast Battle Roar
					selfBuff(121);
				}
				catch(Exception e){}
			}
		}
		
	}
	
	@Override
	protected boolean thinkBuff()
	{
		if(thinkBuff(new int[] {
			423 //Dark Form
		}))
			return true;
		
		return super.thinkBuff();
	}
	
	@Override public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		SkillList.add(190);	//Fatal Strike
		//SkillList.add(36);	//Whirlwind
		SkillList.add(121);	//Battle Roar
		SkillList.add(260);	//Hammer Crush
		SkillList.add(287);	//Lionheart
		SkillList.add(94);	//Rage
		SkillList.add(315);	//Crush of Doom
		SkillList.add(320);	//Wrath
		SkillList.add(994);	//Rush
		
		//skill 3rd
		SkillList.add(362);	//Armor Crush
		SkillList.add(347);	//Earthquake
		SkillList.add(440);	//Brave Heart
		SkillList.add(356);	//Over the Body
		SkillList.add(456);	//Symbol of Resistance
		SkillList.add(917);	//Final Secret
		SkillList.add(758);	//Fighter Will
		
		
		return SkillList;
	}
	

}
