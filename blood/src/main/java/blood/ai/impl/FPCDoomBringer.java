package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.commons.util.Rnd;
import l2s.gameserver.model.Creature;
import l2s.gameserver.model.Player;
import l2s.gameserver.model.Skill;
import l2s.gameserver.tables.SkillTable;

public class FPCDoomBringer extends WarriorPC
{
	public FPCDoomBringer(Player actor)
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
		
		if(hpLevel < 40)
		{
			try
			{
				//cast Sword Shield
				actor.doCast(SkillTable.getInstance().getInfo(483, 1), actor, false);
			}
			catch(Exception e){}
		}
	}
	
	@Override
	protected void onEvtClanAttacked(Creature attacked, Creature attacker, int damage)
	{
		Player actor = getActor();
		//check if target is in 1000 range
		if(!attacked.isInRange(actor.getLoc(), 1000)) 
			return; 
		
		//check target critical level, base on HP level
		int hpLevel 					= (int) attacked.getCurrentHpPercents();
		ArrayList<Skill>	SkillList;
		//take action
		if(hpLevel < 80)
		{
			SkillList = getDrawTargetSkill();
			
			actor.doCast(SkillList.get(Rnd.get(SkillList.size())), attacked, false);
		}
	}
	
	@Override public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		//skill 2nd
		SkillList.add(477);	//Dark Smash
		SkillList.add(494);	//Shoulder Charge
		SkillList.add(485);	//Disarm
		//SkillList.add(503);	//Scorn
		SkillList.add(496);	//Slashing Blade
		SkillList.add(493);	//Storm Assault
		SkillList.add(492);	//Spead Wing
		//SkillList.add(483);	//Sword Shield
		SkillList.add(501);	//Violent Temper
		SkillList.add(495);	//Blade Rush
		SkillList.add(497);	//Crushing Pain
		SkillList.add(498);	//Contagion
		
		
		//skill 3rd
		SkillList.add(939);	//Soul Rage
		SkillList.add(526);	//Enuma Elish
		SkillList.add(793);	//Rush Impact
		SkillList.add(358);	//Final Form
		SkillList.add(794);	//Mass Disarm
		SkillList.add(917);	//Final Secret
		SkillList.add(948);	//Eye for Eye
		
		return SkillList;
	}
	
	protected ArrayList<Skill> getDrawTargetSkill()
	{
		ArrayList<Skill>	SkillList	= new ArrayList<Skill>();
		
		SkillList.add(SkillTable.getInstance().getInfo(503, 1)); //Scorn
		
		return SkillList;
	}
	
}

