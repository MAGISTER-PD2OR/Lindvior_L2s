package blood.ai;

import gnu.trove.map.TIntObjectMap;

import java.util.HashSet;

import l2s.commons.util.Rnd;
import l2s.gameserver.ai.CtrlEvent;
import l2s.gameserver.ai.CtrlIntention;
import l2s.gameserver.geodata.GeoEngine;
import l2s.gameserver.model.Creature;
//import l2s.gameserver.model.Effect;
import l2s.gameserver.model.GameObject;
import l2s.gameserver.model.Party;
import l2s.gameserver.model.Player;
import l2s.gameserver.model.Servitor;
import l2s.gameserver.model.Skill;
import l2s.gameserver.model.base.RestartType;
import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.skills.EffectType;
import l2s.gameserver.tables.SkillTable;
import l2s.gameserver.templates.TeleportLocation;
import l2s.gameserver.templates.skill.EffectTemplate;
//import l2s.gameserver.skills.effects.EffectTemplate;
import l2s.gameserver.utils.Location;
import l2s.gameserver.utils.PositionUtils;
import l2s.gameserver.utils.TeleportUtils;
import blood.data.holder.FarmZoneHolder;
import blood.data.holder.NpcHelper;
import blood.model.FPReward;

public class EventFPC extends FPCDefaultAI
{

	protected Skill[] _selfSkills = Skill.EMPTY_ARRAY;
	
	protected long 
			_nextBuffRound 		= 0, 
			_nextSummonRound 	= 0, 
			_nextSumCubicRound 	= 0, 
			_nextEquipRound 	= 0;
	
	
	
	public EventFPC(Player actor)
	{
		super(actor);
		//_is_debug = false;
	}
	
	// block skill as default
	public boolean isAllowSkill(int skill_id)
	{
		return _allowSkills != null && _allowSkills.contains(skill_id);
	}
	
	protected boolean createNewTask()
	{
		return thinkEquip() || thinkSummon() || thinkBuff() || thinkCubic() || createFightTask();
	}
	
	protected boolean createFightTask()
	{
		return defaultFightTask();
	}
	
	protected boolean thinkEquip() 
	{
		if(_nextEquipRound > System.currentTimeMillis())
			return false;
		
		_nextEquipRound = System.currentTimeMillis();
		FPReward.getInstance().giveReward(getActor());
		return false;
	}

	private boolean thinkCubic() 
	{
		if(_nextSumCubicRound > System.currentTimeMillis())
			return false;
		
		if(_cubicSkills == null || _cubicSkills.length == 0)
			return false;
		
		Player player = getActor();
		
		// TODO add method to validate max cubic
//		if(player.getCubics().size() > 1)
//			return false;
		
		Skill skill = _cubicSkills[0];
		
		for(EffectTemplate et: skill.getEffectTemplates())
		{
			// add cubic
			if(et.getEffectType() == EffectType.Cubic)
			{
				if(player.getCubic(et.getParam().getInteger("cubicId")) == null)
				{
					chooseTaskAndTargets(skill, player, 0);
					return true;
				}
			}
		}
		
		_nextSumCubicRound = System.currentTimeMillis() + 5000;
		
		return false;
	}

	protected boolean thinkSummon() 
	{
		if(_nextSummonRound > System.currentTimeMillis())
			return false;		
		
		if(_sumSkills == null || _sumSkills.length == 0)
			return false;
		
		Player player = getActor();
		
		if(player.getServitors().length < getMaxSummon()  && player.getCurrentMp() > 300)
		{
			for(Skill skill: _sumSkills)
			{
				if(canUseSkill(skill, player, 0))
					return chooseTaskAndTargets(skill, player, 0);
			}
		}
		
		_nextSummonRound = System.currentTimeMillis() + 5000;
		
		return false;
	}
	
	protected int getMaxSummon()
	{
		return 1;
	}

	protected boolean thinkBuff()
	{
		// add interval check for performance
		if(_nextBuffRound > System.currentTimeMillis())
			return false;
		
		doNewbieBuff();
		
		Player player = getActor();
		
		if(_selfBuffSkills != null && _selfBuffSkills.size() > 0)
		{
			for(Skill skill: _selfBuffSkills)
			{
				if(canUseSkill(skill, player) && !player.getEffectList().containsEffects(skill))
					return chooseTaskAndTargets(skill, player, 0);
			}
		}
		
		// think servitor buff
		if(_servitorBuffSkills != null && _servitorBuffSkills.size() > 0 && player.getServitorsCount() > 0)
		{
			for(Servitor servitor: player.getServitors())
			{
				double distance = player.getDistance(servitor);
				
				for(Skill skill: _servitorBuffSkills)
				{
					if(canUseSkill(skill, servitor, distance) && !servitor.getEffectList().containsEffects(skill))
						return chooseTaskAndTargets(skill, servitor, distance);
				}
			}
		}
		
		// think friend buff
		if(_partyBuffSkills != null && _partyBuffSkills.size() > 0 && player.isInParty())
		{
			for(Player member: player.getParty().getPartyMembers())
			{
				if(member.equals(player))
					continue;
				
				double distance = player.getDistance(member);
				
				for(Skill skill: _selfBuffSkills)
				{
					if(canUseSkill(skill, member, distance) && !member.getEffectList().containsEffects(skill))
						return chooseTaskAndTargets(skill, member, distance);
				}
				
			}
		}
		
		_nextBuffRound = System.currentTimeMillis() + 5000;
		
		return false;
	}
	
	/*
	 * Buff method
	 */
	
	protected void doNewbieBuff()
	{
		for(Skill skill: getNewbieBuff())
			buffFromNoWhere(skill);
	}
	
	protected HashSet<Skill> getNewbieBuff()
	{
		HashSet<Skill> skills = new HashSet<Skill>();
		
		skills.add(getSkill(15642, 1));
		skills.add(getSkill(15643, 1));
		skills.add(getSkill(15644, 1));
		skills.add(getSkill(15645, 1));
		skills.add(getSkill(15646, 1));
		skills.add(getSkill(15647, 1));
		
		skills.add(getSkill(15651, 1));
		skills.add(getSkill(15652, 1));
		skills.add(getSkill(15653, 1));
		
		skills.add(getNpcSuperiorBuff());
		
		// TODO add montor exp buff for character < 85
		
		return skills;
	}
	
	protected Skill getNpcSuperiorBuff()
	{
		return getSkill(15648, 1);
//		return getSkill(15649, 1); warrior
//		return getSkill(15650, 1); wizzard
	}
	
	protected boolean buffFromNoWhere(Skill skill)
	{
		Player player = getActor();
		if(player.getEffectList().containsEffects(skill))
			return false;
		
		skill.getEffects(player, player, false);
		return true;
	}
	
	/*
	 * Method provide protect party
	 */
	
	protected void notifyFriends(Creature attacker, int damage)
	{
		Player actor = getActor();
		Party party = actor.getParty();
		
		if(party != null)
		{
			for(Player member: party.getPartyMembers())
			{
				if(!member.equals(actor))
				{
					member.getAI().notifyEvent(CtrlEvent.EVT_CLAN_ATTACKED, actor, attacker, damage);
				}
			}
		}
	}
	
	@Override
	protected void onEvtClanAttacked(Creature attacked, Creature attacker, int damage)
	{
		// TODO - put logic protect party here
		Player actor = getActor();
		if ((attacker == null) || actor.isDead())
		{
			return;
		}
		
		// Adding only hate damage if an attacker - a game character will be added to L2NpcInstance.onReduceCurrentHp
		_aggroList.addDamageHate(attacker, damage, damage);
		
		// Usually 1 hate added summon the owner to death summon mob attacked the host.
		if ((damage > 0) && (attacker.isServitor() || attacker.isPet()))
		{
			_aggroList.addDamageHate(attacker.getPlayer(), damage, damage);
		}
		
		// if not attack, set attacker to target
		if (getIntention() != CtrlIntention.AI_INTENTION_ATTACK)
		{
			if (!actor.isRunning())
			{
				startRunningTask(AI_TASK_ATTACK_DELAY);
			}
			setIntention(CtrlIntention.AI_INTENTION_ATTACK, attacker);
		}
	}
	
	@Override
	protected void onEvtAttacked(Creature attacker, int damage)
	{

		debug("get attacked by:"+attacker+" damage:"+damage);
		
		Player actor = getActor();
		if ((attacker == null) || actor.isDead())
		{
			return;
		}
		
		// Adding only hate damage if an attacker - a game character will be added to L2NpcInstance.onReduceCurrentHp
		_aggroList.addDamageHate(attacker, damage, damage);
		
		// Usually 1 hate added summon the owner to death summon mob attacked the host.
		if ((damage > 0) && (attacker.isServitor() || attacker.isPet()))
		{
			_aggroList.addDamageHate(attacker.getPlayer(), damage, damage);
		}
		
		if (getIntention() != CtrlIntention.AI_INTENTION_ATTACK)
		{
			if (!actor.isRunning())
			{
				startRunningTask(AI_TASK_ATTACK_DELAY);
			}
			setIntention(CtrlIntention.AI_INTENTION_ATTACK, attacker);
		}
		
		notifyFriends(attacker, damage);
	}
	
	/*
	 * About dead
	 */
	
	@Override
	protected void onEvtDead(Creature killer)
	{
		super.onEvtDead(killer);
		setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
	}
	
	protected boolean thinkFPCIdle() 
	{
		if(getFPCIntention() != FPCIntention.IDLE)
			return false;
		
		thinkEquip();
		thinkBuff();
		
		clearTasks();
		
		Location farmLocation = FarmZoneHolder.getInstance().getLocation(getActor());
		setBaseLocation(farmLocation);
		
		if(tryMoveLongAwayToLocation(farmLocation))
		{
			setFPCIntention(FPCIntention.FARMING);
		}
		
		return true;
	}
	
	protected boolean tryMoveLongAwayToLocation(Location loc)
	{
		if(loc == null)
			return false;
		
		Player player = getActor();
		
		Location myRestartLocation = TeleportUtils.getRestartLocation(player, RestartType.TO_VILLAGE);
		NpcInstance buffer = NpcHelper.getClosestBuffer(myRestartLocation);
		NpcInstance gk = NpcHelper.getClosestGatekeeper(myRestartLocation);
		
		int weight = 100;
		
		addTaskTele(myRestartLocation, weight--);
		addTaskSleep(3*1000, weight--);
		
		if(myRestartLocation.distance(buffer.getLoc()) < 4000)
		{
			addTaskMove(Location.findAroundPosition(buffer, 150), true, true, weight--);
			addTaskSleep(5*1000, weight--);
		}
		
		addTaskMove(Location.findAroundPosition(gk, 150), true, true, weight--);
		addTaskSleep(5*1000, weight--);
		
		Location middleRestartLocation = TeleportUtils.getRestartLocation(player, loc, RestartType.TO_VILLAGE);
		NpcInstance middleGK = NpcHelper.getClosestGatekeeper(middleRestartLocation);
		
		if(gk.getObjectId() != middleGK.getObjectId())
		{
			gk = middleGK;
			addTaskTele(Location.findAroundPosition(gk, 150), weight--);
			addTaskSleep(5*1000, weight--);
		}
		
		TIntObjectMap<TeleportLocation> teleMap = gk.getTemplate().getTeleportList(1);
		double minDistance = Double.MAX_VALUE;
		Location spawnLocation = null;
		for(TeleportLocation teleLoc: teleMap.valueCollection())
		{
			double distanceFromSpawnLoc = teleLoc.distance(loc);
			if(distanceFromSpawnLoc < minDistance && GeoEngine.canMoveToCoord(teleLoc.x, teleLoc.y, teleLoc.z, loc.x, loc.y, loc.z, player.getGeoIndex()))
			{
				minDistance = distanceFromSpawnLoc;
				spawnLocation = teleLoc;
			}
		}
		
		if(spawnLocation != null)
		{
			addTaskTele(spawnLocation, weight--);
			addTaskSleep(3*1000, weight--);
			addTaskMove(loc, true, true, weight--);
		}
		else
		{
			addTaskTele(loc, weight--);
		}
		
		return true;
	}
	
	/*
	 * About force warrior or kamael soul
	 */
	
	protected boolean thinkUseWarriorForce(int skillId)
	{
		return thinkUseWarriorForce(skillId, 4);
	}	
	
	protected boolean thinkUseWarriorForce(int skillId, int forceLevel)
	{
		Player player = getActor();
		
		if(player.getCurrentMp() > 300 && player.getCurrentHp() > 300)
		{
			if(player.getIncreasedForce() < forceLevel && canUseSkill(skillId, player))
			{
				addTaskBuff(player, player.getKnownSkill(skillId));
				return true;
			}
		}
		
		return false;
	}
	
	protected boolean thinkUseKamaelSoul(int skillId, int soulLevel)
	{
		Player player = getActor();
		if(player.getCurrentMp() > 300 && player.getCurrentHp() > 300)
		{
			if(player.getConsumedSouls() < soulLevel && canUseSkill(skillId, player))
			{
				addTaskBuff(player, player.getKnownSkill(skillId));
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public int getMaxAttackTimeout()
	{
		return 100000;
	}
	
	public static Location findFrontPosition(GameObject obj, int posX, int posY, int radiusmin, int radiusmax)
	{
		if(radiusmax < radiusmin && radiusmax > 100)
		{
			radiusmin = radiusmax - 100;
		}
		
		if(radiusmax == 0 || radiusmax < radiusmin)
			return new Location(obj);

		double collision = obj.getCollisionRadius();
		int randomRadius, randomAngle, tempz;
		int minangle = 0;
		int maxangle = 360;

		double angle = PositionUtils.calculateAngleFrom(obj.getX(), obj.getY(), posX, posY);
		minangle = (int) angle - 30;
		maxangle = (int) angle + 30;

		Location pos = new Location();
		for(int i = 0; i < 100; i++)
		{
			randomRadius = Rnd.get(radiusmin, radiusmax);
			randomAngle = Rnd.get(minangle, maxangle);
			pos.x = obj.getX() + (int) ((collision + randomRadius) * Math.cos(Math.toRadians(randomAngle)));
			pos.y = obj.getY() + (int) ((collision + randomRadius) * Math.sin(Math.toRadians(randomAngle)));
			pos.z = obj.getZ();
			tempz = GeoEngine.getHeight(pos.x, pos.y, pos.z, obj.getGeoIndex());
			if(Math.abs(pos.z - tempz) < 200 && GeoEngine.getNSWE(pos.x, pos.y, tempz, obj.getGeoIndex()) == GeoEngine.NSWE_ALL 
				&& GeoEngine.canMoveToCoord(obj.getX(), obj.getY(), obj.getZ(), pos.getX(), pos.getY(), pos.getZ(), obj.getGeoIndex()))
			{
				pos.z = tempz;
				pos.h = obj.getHeading();
				return pos;
			}
		}

		return new Location(obj);
	}
	
	protected void tryCastSkill(int skillId, Creature target)
	{
		tryCastSkill(skillId, target, getActor().getDistance(target));
	}

	protected void tryCastSkill(int skillId, Creature target, double distance)
	{
		Player actor = getActor();
		Skill skill = actor.getKnownSkill(skillId);
		if(skill != null && target != null)
		{
			chooseTaskAndTargets(skill, target, distance);
		}
			
	}
	
	/**
	 * utils method to get any skill
	 * @param id
	 * @param level
	 * @return
	 */
	
	protected Skill getSkill(int id, int level)
	{
		return SkillTable.getInstance().getInfo(id, level);
	}
	

}
