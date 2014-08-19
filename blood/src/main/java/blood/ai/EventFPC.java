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
	
	public void reArm()
	{
		reArm(5000);
	}
	
	public void reArm(int delay)
	{
		Player actor = getActor();
		long now = System.currentTimeMillis();
		
		if ((now - _last_weapon_check) > delay)
		{
			_last_weapon_check = now;
			
			ItemInstance wpn = actor.getSecondaryWeaponInstance();
			if (wpn != null)
			{
				_last_secondary_weapon = wpn;
			}
			else if(_last_secondary_weapon != null)
			{
				actor.getInventory().equipItem(_last_secondary_weapon);
			}
			
			wpn = actor.getActiveWeaponInstance();
			if(wpn != null)
			{
				_last_active_weapon = wpn;
			}
			else if (_last_active_weapon != null)
			{
				actor.getInventory().equipItem(_last_active_weapon);
			}
		}
	}
	
	@Override
	public void onEvtThink()
	{
//		Player actor = getActor();
		
		//debug("event state: "+EventManager.getInstance().getMainEventManager().getState());
		
//		if(!NexusEvents.isInEvent(actor))
//		{
//			switch(EventManager.getInstance().getMainEventManager().getState())
//			{
//				case REGISTERING:
//						registerToEvent();
//					break;
//				
//				default:
//					
//			}
//		}
		
		super.onEvtThink();
	}
	
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
				debug("Skill is not cubic summoning. " + skill + ", chooseTaskAndTargets");
				chooseTaskAndTargets(skill, actor, 0);
				return true;
			}
		}
		
		return false;
	}
	
	protected void makeNpcBuffs()
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
//			_log.info("tele to next farm zone");
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
		
		reArm();
		
		makeNpcBuffs();
		
//		if(NexusEvents.isInEvent(actor))
//		{
//			debug("active: in event");
			if(isStuck(10000 + Rnd.get(3000)))
			{
				debug("active: stuck -> random walk, but skipped");
				randomWalk();
				return;
			}
			
			long now = System.currentTimeMillis();
			
			// check equip each 5 mins
			if(now - _checkEquipTimestamp > 300000)
			{
				_checkEquipTimestamp = now;
				FPReward.getInstance().giveReward(actor);
			}
			
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
			
			if(thinkAggressive(3000, 1000)) return;
			
//			if(getCurrentEventType() == EventType.Domination)
//			{
//				if(tacticMove(300))
//				{
//					return;
//				}
//			}
			
//			if(thinkAggressive(3000)) return;
			
//			if(tacticMove()) return;
			
			randomWalk();
//		}
//		else 
//		{
//			if(Rnd.chance(0.5))
//			{
//				actor.standUp();
//				if(Rnd.chance(20))	randomWalk(1000);
//				else
//				{
//					List<GameObject> chars 	= World.getAroundObjects(actor, 2000, 500);
//					if(chars.size() == 0) return;
//					GameObject randomChar		= chars.get(Rnd.get(chars.size()));
//					if(!randomChar.isDoor())
//						actor.moveToLocation(randomChar.getLoc(), 50, true);
//				}
//			}
//		}
			
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
		
		reArm();
		
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
	
//	public static FastMap<Integer, List<EventSpawn>> _team_tactic_spawn = new FastMap<Integer, List<EventSpawn>>();
	
//	public static boolean checkSpawn(EventSpawn spawn, int teamId, EventType eventType)
//	{
//		//if(spawn.getSpawnType() == SpawnType.Regular && spawn.getSpawnTeam() != teamId)
//		//	return true;
//		
//		switch(spawn.getSpawnType())
//		{
//			case Regular:
//			case Flag:
//				if(spawn.getSpawnTeam() != teamId && getCurrentEventType() == EventType.TvT)
//					return true;
//				else
//					return false;
//				
//			case Base:
//			case Zone:
//				if(getCurrentEventType() == EventType.Domination)
//					return true;
//				else 
//					return false;
//			default:
//				return false;
//		}
//	}
	
//	public void clearSpawns()
//	{
//		_team_tactic_spawn.clear();
//	}
//	
//	public static EventType getCurrentEventType()
//	{
//		return EventManager.getInstance().getMainEventManager().getCurrent().getEventType();
//	}
//	
//	public List<EventSpawn> getSpawns()
//	{
//		return getSpawns(NexusEvents.getPlayer(getActor()).getTeamId());
//	}
	
//	public static List<EventSpawn> getSpawns(int teamId)
//	{
//		if(_team_tactic_spawn.get(teamId) == null)
//		{
//			_team_tactic_spawn.put(teamId, new ArrayList<EventSpawn>());
//			
//			List<EventSpawn> spawnList = EventManager.getInstance().getMainEventManager().getMap().getSpawns();
//			
//			for(EventSpawn spawn: spawnList)
//			{
//				if(checkSpawn(spawn, teamId, EventManager.getInstance().getMainEventManager().getCurrent().getEventType()))
//				{
//					// must improve algorithms to choose tactic point 
//					_team_tactic_spawn.get(teamId).add(spawn);
//				}
//			}
//		}
//		
//		return _team_tactic_spawn.get(teamId);
//	}
	
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
	
//	public PlayerEventInfo getEventActor()
//	{
//		return NexusEvents.getPlayer(getActor());
//	}
	
	public void registerToEvent()
	{
		// randomize to join event
		if(!Rnd.chance(1))
			return;
		/* FIXME */
		
//		Player actor = getActor();
//		PlayerEventInfo actorEvent = NexusEvents.getPlayer(actor);
//		if(EventManager.getInstance().getMainEventManager().registerPlayer(actorEvent))
//		{
//			clearSpawns();
//			checkEventBuffScheme();
//			debug("register to event");
//		}
//		
//		//cancel shop if available
//		if(actor.getPrivateStoreType() == Player.STORE_PRIVATE_SELL || actor.getPrivateStoreType() == Player.STORE_PRIVATE_BUY)
//		{
//			actor.setPrivateStoreType(Player.STORE_PRIVATE_NONE);
//			actor.standUp();
//			actor.broadcastCharInfo();
//		}
	}
	
	public void checkEventBuffScheme()
	{
		/* FIXME */
//		Player actor = getActor();
//		PlayerEventInfo actorEvent = NexusEvents.getPlayer(actor);
//		
//		if(EventBuffer.getInstance().getSchemes(actorEvent).isEmpty())
//		{
//			String scheme = "Main";
//			EventBuffer.getInstance().addScheme(actorEvent, scheme);
//			for(int buffId: getEventBuffList())
//			{
//				EventBuffer.getInstance().addBuff(buffId, actorEvent);
//			}
//		}
//		
//		if(EventBuffer.getInstance().getSchemes(actorEvent).size() < 2)
//		{
//			String scheme = "Summon";
//			EventBuffer.getInstance().addScheme(actorEvent, scheme);
//			for(int buffId: getEventPetBuffList())
//			{
//				EventBuffer.getInstance().addBuff(buffId, actorEvent);
//			}
//		}
//		
//		EventBuffer.getInstance().setPlayersCurrentScheme(actor.getObjectId(), "Main");
//		EventBuffer.getInstance().setPlayersCurrentPetScheme(actor.getObjectId(), "Summon");
	}
	

//	protected void trySummon(int skillId)
//	{
//		Player actor 	= getActor();
//		Summon servitor = actor.getPet();
//		
//		if(servitor == null && actor.getCurrentMp() > 300 && NexusEvents.isInEvent(actor))
//		{
//			Skill skill = actor.getKnownSkill(skillId);
//			if(skill != null)
//			{
//				
//				chooseTaskAndTargets(skill, actor, 0);
//			}
//		}
//	}

	protected void tryCastSkill(int skillId, Creature target)
	{
		Player actor = getActor();
		Skill skill = actor.getKnownSkill(skillId);
		if(skill != null && target != null)
		{
			chooseTaskAndTargets(skill, target, 0);
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
	
	public List<Integer> getEventPetBuffList()
	{
		List<Integer> buffs = new ArrayList<Integer>();
		buffs.add(275); 		//Dances - Dance of Fury
//		buffs.add(309); 		//Dances - Dance of Earth Guard
//		buffs.add(307); 		//Dances - Dance of Aqua Guard
//		buffs.add(365); 		//Dances - Siren's Dance
//		buffs.add(277); 		//Dances - Dance of Light
//		buffs.add(276); 		//Dances - Dance of concentration
		buffs.add(310); 		//Dances - Dance of the Vampire
		buffs.add(274); 		//Dances - Dance of Fire
//		buffs.add(273); 		//Dances - Dance of the Mystic
//		buffs.add(272); 		//Dances - Dance of Inspiration
		buffs.add(271); 		//Dances - Dance of the Warrior
//		buffs.add(530); 		//Dances - Dance of Alignment
//		buffs.add(1182); 		//Elder - Resist Aqua
//		buffs.add(1354); 		//Elder - Arcane Protection
		buffs.add(1087); 		//Elder - Agility
//		buffs.add(1353); 		//Elder - Divine Protection
		buffs.add(1352); 		//Elder - Elemental Protection
		buffs.add(1259); 		//Elder - Resist Shock
//		buffs.add(1073); 		//Elder - Kiss of Eva
//		buffs.add(1304); 		//Elder - Advanced Block
//		buffs.add(1355); 		//Elder - Prophecy of Water
//		buffs.add(1460); 		//Elder - Mana Gain
		buffs.add(1397); 		//Elder - Clarity
//		buffs.add(1393); 		//Elder - Unholy Resistance
		buffs.add(1044); 		//Prophet - Regeneration
		buffs.add(1045); 		//Prophet - Blessed Body
		buffs.add(1048); 		//Prophet - Blessed Soul
		buffs.add(1062); 		//Prophet - Berserker Spirit
		buffs.add(1068); 		//Prophet - Might
//		buffs.add(1085); 		//Prophet - Acumen
		buffs.add(1086); 		//Prophet - Haste
//		buffs.add(1191); 		//Prophet - Resist Fire
		buffs.add(1204); 		//Prophet - Wind Walk
//		buffs.add(1043); 		//Prophet - Holy Weapon
		buffs.add(1040); 		//Prophet - Shield
		buffs.add(1036); 		//Prophet - Magic Barrier
//		buffs.add(1356); 		//Prophet - Prophecy of Fire
		buffs.add(1035); 		//Prophet - Mental Shield
//		buffs.add(1392); 		//Prophet - Holy Resistance
		buffs.add(1240); 		//Prophet - Guidance
//		buffs.add(1243); 		//Prophet - Bless Shield
//		buffs.add(1033); 		//Prophet - Resist Poison
//		buffs.add(1032); 		//Prophet - Invigor
//		buffs.add(1303); 		//ShillenElder - Wild Magic
		buffs.add(1357); 		//ShillenElder - Prophecy of Wind
//		buffs.add(1189); 		//ShillenElder - Resist Wind
		buffs.add(1388); 		//ShillenElder - Greater Might
		buffs.add(1268); 		//ShillenElder - Vampiric Rage
//		buffs.add(1389); 		//ShillenElder - Greater Shield
//		buffs.add(1059); 		//ShillenElder - Empower
		buffs.add(1077); 		//ShillenElder - Focus
		buffs.add(1242); 		//ShillenElder - Death Whisper
//		buffs.add(1078); 		//ShillenElder - Concentration
		buffs.add(264); 		//Songs - Song of Earth
//		buffs.add(308); 		//Songs - Song of Storm Guard
//		buffs.add(306); 		//Songs - Song of Flame Guard
//		buffs.add(305); 		//Songs - Song of Vengeance
		buffs.add(304); 		//Songs - Song of Vitality
//		buffs.add(270); 		//Songs - Song of Invocation
		buffs.add(269); 		//Songs - Song of Hunter
		buffs.add(268); 		//Songs - Song of Wind
		buffs.add(267); 		//Songs - Song of Warding
		buffs.add(266); 		//Songs - Song of Water
		buffs.add(349); 		//Songs - Song of Renewal
//		buffs.add(363); 		//Songs - Song of Meditation
		buffs.add(364); 		//Songs - Song of Champion
//		buffs.add(529); 		//Songs - Song of Elemental
//		buffs.add(4702); 		//Summon - Blessing of Seraphim
//		buffs.add(4700); 		//Summon - Gift of Queen
		buffs.add(4699); 		//Summon - Blessing of Queen
		buffs.add(4703); 		//Summon - Gift of Seraphim
//		buffs.add(1007); 		//WarCryer - Chant of Battle
		buffs.add(1413); 		//WarCryer - Magnus Chant
//		buffs.add(1009); 		//WarCryer - Chant of Shielding
//		buffs.add(1006); 		//WarCryer - Chant of Fire
//		buffs.add(1284); 		//WarCryer - Chant of Revenge
//		buffs.add(1391); 		//WarCryer - Earth Chant
//		buffs.add(1002); 		//WarCryer - Flame Chant
		buffs.add(1363); 		//WarCryer - Chant of Victory
//		buffs.add(1362); 		//WarCryer - Chant of Spirit
//		buffs.add(1308); 		//WarCryer - Chant of Predator
//		buffs.add(1309); 		//WarCryer - Chant of Eagle
//		buffs.add(1310); 		//WarCryer - Chant of Vampire
//		buffs.add(1390); 		//WarCryer - War Chant

		return buffs;
	}
	
	public List<Integer> getEventBuffList()
	{
		List<Integer> buffs = new ArrayList<Integer>();
//		buffs.add(275); 		//Dances - Dance of Fury
//		buffs.add(309); 		//Dances - Dance of Earth Guard
//		buffs.add(307); 		//Dances - Dance of Aqua Guard
//		buffs.add(365); 		//Dances - Siren's Dance
//		buffs.add(277); 		//Dances - Dance of Light
//		buffs.add(276); 		//Dances - Dance of concentration
//		buffs.add(310); 		//Dances - Dance of the Vampire
//		buffs.add(274); 		//Dances - Dance of Fire
//		buffs.add(273); 		//Dances - Dance of the Mystic
//		buffs.add(272); 		//Dances - Dance of Inspiration
//		buffs.add(271); 		//Dances - Dance of the Warrior
//		buffs.add(530); 		//Dances - Dance of Alignment
//		buffs.add(1182); 		//Elder - Resist Aqua
//		buffs.add(1354); 		//Elder - Arcane Protection
//		buffs.add(1087); 		//Elder - Agility
//		buffs.add(1353); 		//Elder - Divine Protection
//		buffs.add(1352); 		//Elder - Elemental Protection
//		buffs.add(1259); 		//Elder - Resist Shock
//		buffs.add(1073); 		//Elder - Kiss of Eva
//		buffs.add(1304); 		//Elder - Advanced Block
//		buffs.add(1355); 		//Elder - Prophecy of Water
//		buffs.add(1460); 		//Elder - Mana Gain
//		buffs.add(1397); 		//Elder - Clarity
//		buffs.add(1393); 		//Elder - Unholy Resistance
//		buffs.add(1044); 		//Prophet - Regeneration
//		buffs.add(1045); 		//Prophet - Blessed Body
//		buffs.add(1048); 		//Prophet - Blessed Soul
//		buffs.add(1062); 		//Prophet - Berserker Spirit
//		buffs.add(1068); 		//Prophet - Might
//		buffs.add(1085); 		//Prophet - Acumen
//		buffs.add(1086); 		//Prophet - Haste
//		buffs.add(1191); 		//Prophet - Resist Fire
//		buffs.add(1204); 		//Prophet - Wind Walk
//		buffs.add(1043); 		//Prophet - Holy Weapon
//		buffs.add(1040); 		//Prophet - Shield
//		buffs.add(1036); 		//Prophet - Magic Barrier
//		buffs.add(1356); 		//Prophet - Prophecy of Fire
//		buffs.add(1035); 		//Prophet - Mental Shield
//		buffs.add(1392); 		//Prophet - Holy Resistance
//		buffs.add(1240); 		//Prophet - Guidance
//		buffs.add(1243); 		//Prophet - Bless Shield
//		buffs.add(1033); 		//Prophet - Resist Poison
//		buffs.add(1032); 		//Prophet - Invigor
//		buffs.add(1303); 		//ShillenElder - Wild Magic
//		buffs.add(1357); 		//ShillenElder - Prophecy of Wind
//		buffs.add(1189); 		//ShillenElder - Resist Wind
//		buffs.add(1388); 		//ShillenElder - Greater Might
//		buffs.add(1268); 		//ShillenElder - Vampiric Rage
//		buffs.add(1389); 		//ShillenElder - Greater Shield
//		buffs.add(1059); 		//ShillenElder - Empower
//		buffs.add(1077); 		//ShillenElder - Focus
//		buffs.add(1242); 		//ShillenElder - Death Whisper
//		buffs.add(1078); 		//ShillenElder - Concentration
//		buffs.add(264); 		//Songs - Song of Earth
//		buffs.add(308); 		//Songs - Song of Storm Guard
//		buffs.add(306); 		//Songs - Song of Flame Guard
//		buffs.add(305); 		//Songs - Song of Vengeance
//		buffs.add(304); 		//Songs - Song of Vitality
//		buffs.add(270); 		//Songs - Song of Invocation
//		buffs.add(269); 		//Songs - Song of Hunter
//		buffs.add(268); 		//Songs - Song of Wind
//		buffs.add(267); 		//Songs - Song of Warding
//		buffs.add(266); 		//Songs - Song of Water
//		buffs.add(349); 		//Songs - Song of Renewal
//		buffs.add(363); 		//Songs - Song of Meditation
//		buffs.add(364); 		//Songs - Song of Champion
//		buffs.add(529); 		//Songs - Song of Elemental
//		buffs.add(4702); 		//Summon - Blessing of Seraphim
//		buffs.add(4700); 		//Summon - Gift of Queen
//		buffs.add(4699); 		//Summon - Blessing of Queen
//		buffs.add(4703); 		//Summon - Gift of Seraphim
//		buffs.add(1007); 		//WarCryer - Chant of Battle
//		buffs.add(1413); 		//WarCryer - Magnus Chant
//		buffs.add(1009); 		//WarCryer - Chant of Shielding
//		buffs.add(1006); 		//WarCryer - Chant of Fire
//		buffs.add(1284); 		//WarCryer - Chant of Revenge
//		buffs.add(1391); 		//WarCryer - Earth Chant
//		buffs.add(1002); 		//WarCryer - Flame Chant
//		buffs.add(1363); 		//WarCryer - Chant of Victory
//		buffs.add(1362); 		//WarCryer - Chant of Spirit
//		buffs.add(1308); 		//WarCryer - Chant of Predator
//		buffs.add(1309); 		//WarCryer - Chant of Eagle
//		buffs.add(1310); 		//WarCryer - Chant of Vampire
//		buffs.add(1390); 		//WarCryer - War Chant

		return buffs;
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

}
