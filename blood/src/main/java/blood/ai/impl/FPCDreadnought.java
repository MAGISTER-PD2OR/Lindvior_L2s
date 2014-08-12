package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Creature;
import l2s.gameserver.model.Player;

public class FPCDreadnought extends WarriorPC
{
	public FPCDreadnought(Player actor)
	{
		super(actor);
	}

	protected boolean thinkBuff()
	{
		if(thinkBuff(new int[] {78})) // Warcry
			return true;
		
		return super.thinkBuff();
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
		
		if(hpLevel < 70)
			//cast Battle Roar
			selfBuff(121);
		
		if(hpLevel < 30)
			//Final Fenzy
			selfBuff(290); 
		
		if(hpLevel < 10)
			//cast Revival
			selfBuff(181);
		
	}
	
	@Override public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		SkillList.add(920);	//Power Crush
		SkillList.add(36);	//Whirlwind
		//SkillList.add(121);	//Battle Roar
		SkillList.add(48);	//Thunder storm
		//SkillList.add(286);	//Provoke
		SkillList.add(116);	//Howl
		//SkillList.add(130);	//Thrill Fight
		SkillList.add(287);	//Lionheart
		//SkillList.add(181);	//Revival
		SkillList.add(452);	//Shock Stomp
		SkillList.add(421);	//Fell Swoop
				
		//skill 3rd
		SkillList.add(921);	//Cursed Pierce
		SkillList.add(361);	//Shock Blast
		SkillList.add(347);	//Earthquake
		SkillList.add(440);	//Brave Heart
		SkillList.add(360);	//Eye of Slayer
		SkillList.add(457);	//Symbol of Honor
		SkillList.add(774);	//Dread Pool
		
		
		return SkillList;
	}
	

}

