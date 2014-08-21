package blood.ai;

import java.util.ArrayList;
import java.util.List;

import l2s.commons.collections.CollectionUtils;
import l2s.commons.util.Rnd;
import l2s.gameserver.ai.CtrlEvent;
import l2s.gameserver.ai.CtrlIntention;
import l2s.gameserver.geodata.GeoEngine;
import l2s.gameserver.model.Creature;
//import l2s.gameserver.model.Effect;
import l2s.gameserver.model.GameObject;
import l2s.gameserver.model.Party;
import l2s.gameserver.model.Player;
import l2s.gameserver.model.Skill;
import l2s.gameserver.model.Skill.SkillType;
import l2s.gameserver.model.World;
import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.model.items.ItemInstance;
import l2s.gameserver.skills.EffectType;
import l2s.gameserver.tables.SkillTable;
import l2s.gameserver.templates.skill.EffectTemplate;
//import l2s.gameserver.skills.effects.EffectTemplate;
import l2s.gameserver.utils.Location;
import l2s.gameserver.utils.PositionUtils;

import org.apache.commons.lang3.ArrayUtils;

import blood.FPCInfo;
import blood.model.FPReward;
import blood.utils.ClassFunctions;

public class EventFPC extends FPCDefaultAI
{

	protected Skill[] _selfSkills = Skill.EMPTY_ARRAY, 
	_cubicSkills = Skill.EMPTY_ARRAY,
	_sumSkills = Skill.EMPTY_ARRAY;
	
	public EventFPC(Player actor)
	{
		super(actor);
		// TODO Auto-generated constructor stub
		//_is_debug = false;
	}
	
	public List<Integer> getAllowSkill()
	{
		List<Integer> result = new ArrayList<Integer>();
		//result.add();
		return result;
	}
	
	//@SuppressWarnings("incomplete-switch")
	public void addSkill(Skill skill)
	{
		List<Integer> _list = getAllowSkill();
		
//		_log.info("addSkill: " + skill+ " isAllow:"+_list.contains(skill.getId()));
		if(!_list.contains(skill.getId()))
			return;
		
		switch(skill.getSkillType())
		{
			case PDAM:
			case MANADAM:
			case MDAM:
			case DRAIN:
			case DRAIN_SOUL:
			{
				boolean added = false;
				
				if(skill.hasEffects())
					for(EffectTemplate eff : skill.getEffectTemplates())
						switch(eff.getEffectType())
						{
							case Stun:
								_stunSkills = ArrayUtils.add(_stunSkills, skill);
								added = true;
								break;
							case DamOverTime:
							case DamOverTimeLethal:
							case ManaDamOverTime:
							case LDManaDamOverTime:
								_dotSkills = ArrayUtils.add(_dotSkills, skill);
								added = true;
								break;
						default:
							break;
						}

				if(!added)
					_damSkills = ArrayUtils.add(_damSkills, skill);

				break;
			}
			case DOT:
			case MDOT:
			case POISON:
			case BLEED:
				_dotSkills = ArrayUtils.add(_dotSkills, skill);
				break;
			case DEBUFF:
			case SLEEP:
			case ROOT:
			case PARALYZE:
			case MUTE:
			case TELEPORT_NPC:
			case AGGRESSION:
				_debuffSkills = ArrayUtils.add(_debuffSkills, skill);
				break;
			case BUFF:
				_buffSkills = ArrayUtils.add(_buffSkills, skill);
				break;
			case STUN:
				_stunSkills = ArrayUtils.add(_stunSkills, skill);
				break;
			case HEAL:
			case HEAL_PERCENT:
			case HOT:
				_healSkills = ArrayUtils.add(_healSkills, skill);
				break;
			case SUMMON:
				_sumSkills = ArrayUtils.add(_sumSkills, skill);
				break;
			default:
				break;
		}
	}
	
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
		
//		switch(getCurrentEventType())
//		{
//			case TvT:
//			case Domination:
//				List<Player> chars = World.getAroundPlayers(actor, 500, 500);
//				//CollectionUtils.eqSort(chars, _nearestTargetComparator);
//				for (Player cha : chars)
//				{
//					if(NexusEvents.getPlayer(actor).getTeamId() == NexusEvents.getPlayer(cha).getTeamId())
//					{
//						cha.getAI().notifyEvent(CtrlEvent.EVT_CLAN_ATTACKED, actor, attacker, damage);
//					}
//				}
//				break;
//				
//			default:
//		}
	}
	
	@Override
	protected void onEvtDead(Creature killer)
	{
		super.onEvtDead(killer);
		setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
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
	
	protected ItemInstance 	_last_active_weapon;
	protected ItemInstance 	_last_secondary_weapon;
	protected long 			_last_weapon_check;
	
	protected boolean _thinkActiveCheckCondition()
	{
		
		return true;
	}
	
	protected boolean thinkUseWarriorForce(int skillId)
	{
		return thinkUseWarriorForce(skillId, 4);
	}
		
	
	protected boolean thinkUseWarriorForce(int skillId, int forceLevel)
	{
		Player actor = getActor();
		
		if(actor.getCurrentMp() > 300 && actor.getCurrentHp() > 300)
		{
			if(actor.getIncreasedForce() < forceLevel)
			{
				selfBuff(skillId);
				return true;
			}
		}
		
		return false;
	}
	
	protected boolean thinkUseKamaelSoul(int skillId, int soulLevel)
	{
		Player actor = getActor();
		if(actor.getCurrentMp() > 300 && actor.getCurrentHp() > 300)
		{
			if(actor.getConsumedSouls() < soulLevel)
			{
				selfBuff(skillId);
				return true;
			}
		}
		
		return false;
	}
	
	protected boolean thinkBuff(int skillId)
	{
		return thinkBuff(getActor().getKnownSkill(skillId));
	}
	
	protected boolean thinkBuff(Skill skill)
	{
		if(skill == null)
			return false;
		
		Player actor = getActor();
		for(EffectTemplate et: skill.getEffectTemplates())
		{
			// add cubic
			if(et.getEffectType() == EffectType.Cubic)
			{
				if(actor.getCubic(et.getParam().getInteger("cubicId")) == null)
				{
					chooseTaskAndTargets(skill, actor, 0);
					return true;
				}
			}
			// real buff
			else if(actor.getEffectList().getEffectsCount(skill) == 0)
			{
				chooseTaskAndTargets(skill, actor, 0);
				return true;
			}
		}
		
		return false;
	}
	
	protected void makeNpcBuffs()
	{
		basicNpcBuffs();
	}
	
	protected void basicNpcBuffs()
	{
		npcBuff( 15642, 1 ); // Путешественник - Поэма Рога
		npcBuff( 15643, 1 ); // Путешественник - Поэма Барабана
		npcBuff( 15644, 1 ); // Путешественник - Поэма Органа
		npcBuff( 15645, 1 ); // Путешественник - Поэма Гитары
		npcBuff( 15646, 1 ); // Путешественник - Поэма Арфы
		npcBuff( 15647, 1 );  // Путешественник - Поэма Лютни
		npcBuff( 15651, 1 );  // Путешественник - Соната Битвы
		npcBuff( 15652, 1 );  // Путешественник - Соната Движения
		npcBuff( 15653, 1 );  // Путешественник - Соната Расслабления
	}
	
	protected void npcBuff(int skillId, int skillLevel)
	{
		Skill skill = SkillTable.getInstance().getInfo(skillId, skillLevel);
		if(skill == null)
			return;	
		
		Player actor = getActor();
		
		if(actor.getLevel() > 90)
			return;
		
		if(actor.getEffectList().getEffectsCount(skill) > 0)
			return; 
		
		skill.getEffects(actor, actor, false);
	}
	
	protected boolean thinkBuff(int[] skills)
	{
		if(skills == null || skills.length <= 0)
			return false;
		
		for(int skillId: skills)
		{
			if(thinkBuff(skillId))
				return true;
		}
		
		return false;
	}
	
	protected boolean thinkBuff()
	{
//		if(_buffSkills == null || _buffSkills.length <= 0)
//			return false;
//		
//		Player actor = getActor();
//		
//		for(Skill skill: _buffSkills)
//		{
//			if(thinkBuff(skill))
//				return true;
//		}
		debug("thinkBuff");
		return false;
	}
	
	protected boolean thinkSummon(int skillId)
	{
		return thinkSummon(getActor().getKnownSkill(skillId));
	}
	
	protected boolean thinkSummon(Skill skill)
	{
		debug("thinkSummon:" + skill);
		if(skill == null || skill.getSkillType() != SkillType.SUMMON)
			return false;
		
		Player actor 	= getActor();
		
		if(actor.getServitors().length == 0  && actor.getCurrentMp() > 300) /* FIXME */
		{
			chooseTaskAndTargets(skill, actor, 0);
			return true;
		}
		
		return false;
	}
	
	protected boolean thinkSummon()
	{
//		debug("thinkSummon");
		return false;
	}
	
	protected boolean thinkAggressive()
	{
		return thinkAggressive(2000, 2000);
	}
	
	protected boolean thinkAggressive(int range)
	{
		return thinkAggressive(range, 2000);
	}
	
	protected boolean thinkAggressive(int range, int delay)
	{
//		debug("thinkAggressive, range:"+range);
		Player actor = getActor();
		long now = System.currentTimeMillis();
//		if ((now - _checkAggroTimestamp) > delay && !actor.isInPeaceZone() && Blood.AI_ATTACK_ALLOW)
		if ((now - _checkAggroTimestamp) > delay && !actor.isInPeaceZone())
		{
			_checkAggroTimestamp = now;
//			debug("active: check aggro");
			
			boolean aggressive = Rnd.chance(100);
			if (!_aggroList.isEmpty() || aggressive)
			{
				List<NpcInstance> chars = World.getAroundNpc(actor, range, 500);
				CollectionUtils.eqSort(chars, _nearestTargetComparator);
				debug("around size: "+ chars.size());
				for (NpcInstance cha : chars)
				{
					if (aggressive || (_aggroList.get(cha) != null))
					{
						debug("checkAggression: "+cha);
						if (checkAggression(cha))
						{
							debug("checkAggression: OK on "+cha);
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public int getMaxAttackTimeout()
	{
		return 100000;
	}
	
	protected boolean thinkActiveByClass()
	{
		return false;
	}
	
	@Override
	protected void thinkActive()
	{
		debug("think active");
		Player actor = getActor();
		if (actor.isActionsDisabled())
		{
//			debug("action disabled");
			return;
		}
		
		if(actor.isInPeaceZone())
		{
			FPCInfo.getInstance(actor).teleToNextFarmZone();
			return;
		}
		
		if (_def_think)
		{
			if (doTask())
			{
				debug("active: do task on think");
				clearTasks();
			}
			return;
		}
		
		if(actor.isSitting())
		{
			actor.standUp();
		}
		
		makeNpcBuffs();
		
		if(isStuck(10000 + Rnd.get(3000)))
		{
			debug("active: stuck -> random walk, but skipped");
			randomWalk();
			return;
		}
		
		long now = System.currentTimeMillis();
		
		gearUp(now);
		
		if(now - _checkSummonTimestamp > 5000)
		{
			if(thinkSummon()) return;
			_checkSummonTimestamp = now;
		}
		
		if(now - _checkBuffTimestamp > 5000)
		{
			if(thinkBuff()) return;
			_checkBuffTimestamp = now;
		}
		
		if(thinkActiveByClass())
			return;
		
		if(!actor.isInParty())
		{
			if(thinkAggressive(2000, 1000)) return;
			randomWalk();
		}
		else if(actor.getParty().isLeader(actor))
		{
			if(thinkAggressive(2000, 1000)) return;
			tryMoveToLoc(get_fpcParty().getCenterLoc(), 200);
		}
		else
		{
			Player leader = actor.getParty().getPartyLeader();
			
			Creature target = leader.getAI().getAttackTarget();
			
			if(target != null && checkAggression(target))
			{	
				return;
			}
			
			tryMoveToLoc(get_fpcParty().getCenterLoc(), 200);
		}
	}
	
	@Override
	protected void thinkAttack()
	{
		Player actor = getActor();
		if (actor.isDead())
		{
			return;
		}
		
		if(actor.isSitting())
		{
			actor.standUp();
		}
		
		makeNpcBuffs();
		long now = System.currentTimeMillis();
		gearUp(now);
		if(now - _checkSummonTimestamp > 5000)
		{
			if(thinkSummon()) return;
			_checkSummonTimestamp = now;
		}
		
		if(now - _checkBuffTimestamp > 5000)
		{
			if(thinkBuff()) return;
			_checkBuffTimestamp = now;
		}
		
		/*
		if(isStuck(7000 + Rnd.get(3000)))
		{
			clearTasks();
			randomWalk();
			return;
		}
		*/
		
		if (doTask() && !actor.isAttackingNow() && !actor.isCastingNow())
		{
			createNewTask();
		}
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
	
	public boolean tacticMove()
	{
		return tacticMove(0);
	}
	
	public boolean tacticMove(int min_distance)
	{
		debug("tacticMove");
		return false; /* FIXME */
//		Player actor = getActor();
//		
//		if(actor.isDead())
//			return false;
//
//		if(actor.isMoving)
//			return false;
//
//		if(!NexusEvents.isInEvent(actor))
//			return false;
//		
//		if(getSpawns().size() == 0)
//			return false;
//		
//		EventSpawn tacticSpawn = getSpawns().get(Rnd.get(getSpawns().size()));
//		
//		if(tacticSpawn == null)
//			return false;
//		
//		double distance = actor.getDistance(tacticSpawn.getLoc().getX(), tacticSpawn.getLoc().getY());
//		
//		if(min_distance > 0 && min_distance > distance)
//		{
//			debug("so close, not event move");
//			return false;
//		}
//		
//		debug("distance: " + distance);
//		debug("my pos: "+ actor.getX() + " " + actor.getY());
//		debug("tactic:" + tacticSpawn.getSpawnType()+ " loc:" + tacticSpawn.getLoc().getX() + " "+ tacticSpawn.getLoc().getY());
//		
//		/*
//		if(distance > 100000)
//		{
//			debug("so far, cancel task");
//			return false;
//		}
//		*/
//		
//		//GeoEngine
//		
//		Location new_loc; 
//		/* FIXME */
////		if(tacticSpawn.getSpawnType() == SpawnType.Zone)
////		{
////			new_loc = Location.findAroundPosition(tacticSpawn.getLoc().getX(), tacticSpawn.getLoc().getY(), tacticSpawn.getLoc().getZ(), 100, 300, actor.getGeoIndex());
////		}
////		else
////		{
//			new_loc = findFrontPosition(actor, tacticSpawn.getLoc().getX(), tacticSpawn.getLoc().getY(), 1000, (int)distance);
////		}
//		
//		debug("new loc: "+new_loc.getX() + " " + new_loc.getY());
//		
//		if(GeoEngine.canMoveToCoord(actor.getX(), actor.getY(), actor.getZ(), new_loc.getX() , new_loc.getY(), new_loc.getZ(), actor.getGeoIndex()))
//		{
//			addTaskMove(new_loc, true);
//			return true;
//		}
//		else
//		{
//			debug("can not move, so force move");
//			addTaskMove(new_loc, true);
//			return true;
//		}
		
		//return false;
	}
	
	/**
	 * Nexus function
	 */
	
	protected void tryCastSkill(int skillId, Creature target)
	{
		tryCastSkill(skillId, target, getActor().getDistance(target));
	}

	protected void tryCastSkill(int skillId, Creature target, double distance)
	{
		Player actor = getActor();
		Skill skill = actor.getKnownSkill(skillId);
		debug(actor + " try cast skill:"+skillId+" on distance: "+ distance + " on: "+target);
		if(skill != null && target != null)
		{
			chooseTaskAndTargets(skill, target, distance);
		}
			
	}
	
	protected void selfBuff(int skillId)
	{
		Player actor = getActor();
		Skill skill = actor.getKnownSkill(skillId);
		if(skill != null)
		{
			chooseTaskAndTargets(skill, actor, 0);
		}
			
	}
	
	public Skill getUniqueSkill(int[] skills)
	{
		Skill skill = null;
		Player actor = getActor();
		for(int skill_id: skills){
			if(skill == null) skill = actor.getKnownSkill(skill_id);
		}
		
		return skill;
	}
	
	/*
	 * Gear UP
	 */
	protected long _gearUpTimeStamp = 0;
	
	public void gearUp()
	{
		gearUp(System.currentTimeMillis());
	}
	
	public void gearUp(Long now)
	{
		gearUp(now, 60*5*1000);
	}
	
	public void gearUp(Long now, int limit)
	{
		if(now < (_gearUpTimeStamp + limit))
			return;
		
		_gearUpTimeStamp = now;
		FPReward.getInstance().giveReward(getActor());
	}
	

}
