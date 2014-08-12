package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Creature;
import l2s.gameserver.model.Player;

public class FPCGrandKhauatari extends WarriorPC
{
	public FPCGrandKhauatari(Player actor)
	{
		super(actor);
	}

	@Override
	protected boolean thinkBuff()
	{
		if(thinkUseWarriorForce(50, 4))
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
		
		if(hpLevel < 30)
		{

			//cast Zealot
			selfBuff(420);
		}
		
	}
	
	@Override public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		SkillList.add(280);	//Burning Fist
		//SkillList.add(50);	//Focused Force
		SkillList.add(54);	//Force Blaster
		SkillList.add(284);	//Hurricane Assault
		SkillList.add(95);	//Cripple
		SkillList.add(282);	//Puma Spirit Totem
		SkillList.add(281);	//Soul Breaker
		//SkillList.add(17);	//Force Burst
		//SkillList.add(35);	//Force Storm
		//SkillList.add(420);	//Zealot
		SkillList.add(81);	//Punch of Doom
		SkillList.add(425);	//Hawk Spirit Totem
		
		//skill 3rd
		SkillList.add(346);	//Ranging Force
		SkillList.add(443);	//Force Barrier
		SkillList.add(458);	//Symbol of Energy
		SkillList.add(917);	//Final Secret
		SkillList.add(758);	//Fighter Will
		SkillList.add(776);	//Force Of Destruction
		SkillList.add(918);	//Maximum Force Focus
		
		return SkillList;
	}
	
}

