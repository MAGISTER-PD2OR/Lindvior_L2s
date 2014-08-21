package blood.handler.admincommands;

import gnu.trove.map.TIntObjectMap;
import l2s.gameserver.ai.PlayerAI;
import l2s.gameserver.dao.CharacterDAO;
import l2s.gameserver.geodata.GeoEngine;
import l2s.gameserver.handler.admincommands.IAdminCommandHandler;
//import l2s.gameserver.model.Effect;
import l2s.gameserver.model.GameObject;
import l2s.gameserver.model.GameObjectsStorage;
import l2s.gameserver.model.Player;
import l2s.gameserver.model.base.RestartType;
import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.model.items.ItemInstance;
import l2s.gameserver.templates.TeleportLocation;
//import l2s.gameserver.tables.PetDataTable;
//import l2s.gameserver.templates.item.ItemTemplate.Grade;
import l2s.gameserver.utils.ItemFunctions;
import l2s.gameserver.utils.Location;
import l2s.gameserver.utils.TeleportUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import blood.Blood;
import blood.FPCInfo;
import blood.ai.FPCDefaultAI;
import blood.base.FPCParty;
import blood.base.FPCRole;
import blood.base.FPCSpawnStatus;
import blood.data.holder.FarmZoneHolder;
import blood.model.FPReward;
import blood.table.FPCMerchantTable;
import blood.utils.ClassFunctions;
import blood.utils.NpcFunctions;

public class AdminFakePlayers implements IAdminCommandHandler
{
	private static final Logger _log = LoggerFactory.getLogger(AdminFakePlayers.class);
	
	private static enum Commands
	{
		admin_fp_debug,
		admin_fp_attack,
		admin_fp_check,
		admin_fp_party_check,
		admin_fp_quota,
		admin_fp_padding,
		admin_reload_merchant,
		admin_clear_inv,
		admin_add_ai,
		admin_check_ring,
		admin_fp_spawn,
		admin_fp_equip,
		admin_fp_loc,
		admin_set_level2,
		admin_fp_class, 
		admin_tryai,
		admin_find_buffer
		}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean useAdminCommand(Enum comm, String[] wordList, String fullString, Player activeChar)
	{
		Commands command = (Commands) comm;

		if(!activeChar.getPlayerAccess().CanEditNPC)
			return false;

		switch(command)
		{
			case admin_fp_attack:
				if(wordList.length < 2)
				{
					activeChar.sendMessage("USAGE: //fp_attack on");
					activeChar.sendMessage("USAGE: //fp_attack off");
					return false;
				}
				
				String mode = wordList[1];
				
				if(mode.equals("on")){
					Blood.AI_ATTACK_ALLOW = true;
				}
				else if(mode.equals("off")){
					Blood.AI_ATTACK_ALLOW = false;
				}else{
					Blood.AI_ATTACK_ALLOW = !Blood.AI_ATTACK_ALLOW;
				}
				
				_log.info("AI status: "+Blood.AI_ATTACK_ALLOW);
				
				break;
				
			case admin_fp_debug:
				
				String debugTargetName = null;
				Player debug_player = null;
				
				if(wordList.length < 2)
				{
					GameObject obj = activeChar.getTarget();
					debug_player = GameObjectsStorage.getPlayer(obj.getObjectId());
					
				}
				else
				{
					debugTargetName = wordList[1];
					debug_player = GameObjectsStorage.getPlayer(debugTargetName);
				}
				
				if(debug_player == null)
				{
					activeChar.sendMessage("USAGE: //fp_debug charName");
					return false;
				}
				
				
				if(debug_player.getAI() instanceof FPCDefaultAI)
				{
					FPCDefaultAI ai = (FPCDefaultAI) debug_player.getAI();
					ai.toggleDebug();
				}
				return true;
				
			case admin_fp_quota:
				
				if(wordList.length < 3)
				{
					activeChar.sendMessage("USAGE: //admin_fp_quota role num");
					activeChar.sendMessage("USAGE: ROLE LIST");
					for (FPCRole role : FPCRole.values()) 
			    	{
						activeChar.sendMessage("USAGE: " + role.getName());
			    	}
					return false;
				}
				else
				{
					for (FPCRole role : FPCRole.values()) 
			    	{
						if(role.getName().equals(wordList[1]))
						{
							role.setQuota(Integer.parseInt(wordList[2]));
							activeChar.sendMessage("INFO: Set role "+ role.getName() + " quota into " + wordList[2]);
						}
			    	}
				}
				
				break;

			case admin_fp_padding:
				
				if(wordList.length < 3)
				{
					activeChar.sendMessage("USAGE: //admin_fp_padding role num");
					activeChar.sendMessage("USAGE: ROLE LIST");
					for (FPCRole role : FPCRole.values()) 
			    	{
						activeChar.sendMessage("USAGE: " + role.getName());
			    	}
					return false;
				}
				else
				{
					for (FPCRole role : FPCRole.values()) 
			    	{
						if(role.getName() == wordList[1])
						{
							role.setQuotaAdjustRate(Integer.parseInt(wordList[2]));
							activeChar.sendMessage("INFO: Set role "+ role.getName() + " padding into " + wordList[2]);
						}
			    	}
				}
				
				break;
				
			case admin_fp_check:
				String report;
				for(FPCSpawnStatus status: FPCSpawnStatus.values())
		    	{
					report = "Status: " + status + " size: " + status.getSize();
					activeChar.sendMessage(report);
					
		    	}
		    	
		    	for(FPCRole role: FPCRole.values())
		    	{
		    		report = "Role: " + role + " size: " + role.getSize() + " quota: "+role.getQuota();
		    		activeChar.sendMessage(report);
		    	}
				break;
			case admin_fp_party_check:
				GameObject target = activeChar.getTarget();
				if(!target.isPlayer() || !target.getPlayer().isFakePlayer())
					return false;
				
				FPCParty party = FPCInfo.getInstance(target.getPlayer()).getParty();
				if(party == null)
					return false;
				
				party.debug();
				
				break;
			
			case admin_reload_merchant:
				FPCMerchantTable.GetMerchantItemListFromDB();
				break;
				
			case admin_clear_inv:
				ItemInstance[] itemArray = activeChar.getInventory().getItems();
				if(itemArray != null && itemArray.length > 0)
					for(ItemInstance item : itemArray)
					{
						ItemFunctions.removeItem(activeChar, item.getItemId(), item.getCount(), false);
					}
				activeChar.getInventory().store();
				activeChar.sendMessage("Clear Inventory.");
			break;
			case admin_tryai:
				FPCInfo newInfo = new FPCInfo(activeChar);
				newInfo.setAI(FPCRole.NEXUS_EVENT.getAI(activeChar));
//				newInfo.setPVEStyle(FPCPveStyle.PARTY);
//				newInfo.setParty();
			break;
			case admin_add_ai:
				activeChar.sendMessage("Current Player AI: " + activeChar.getAI());
				FPCDefaultAI ai = FPCRole.getAggresiveAI(activeChar, wordList[1]);
				if(ai != null) 
					activeChar.setAI(ai);
				else activeChar.setAI(new PlayerAI(activeChar));
			break;
			case admin_fp_spawn:
				if(wordList.length < 2)
				{
					activeChar.sendMessage("USAGE: //fp_spawn [char_name]");
					return false;
				}
				String char_name = wordList[1];
//				GameObjectsStorage.getPlayer(char_name);
//				Player player = GameObjectsStorage.getPlayer(char_name);
				int playerId = CharacterDAO.getInstance().getObjectIdByName(char_name);
				_log.info("spawn: "+playerId+ " name: "+char_name);
				FPCInfo newChar = new FPCInfo(playerId);
            	newChar.spawn();
//            	newChar.setRole(FPCRole.NEXUS_EVENT);
//				World.getPlayer(char_name);
			break;
			case admin_fp_equip:
				System.out.println("admin_fp_equip");
				FPReward.getInstance().giveReward(activeChar);
			break;
			case admin_fp_class:
				ClassFunctions.upClass(activeChar);
			break;
			case admin_fp_loc:
				FPCInfo.getInstance(activeChar).teleToNextFarmZone();
			break;
			case admin_find_buffer:
				Player player = activeChar;
				
				Location myRestartLocation = TeleportUtils.getRestartLocation(player, RestartType.TO_VILLAGE);
				NpcInstance buffer = NpcFunctions.getNearestBuffer(myRestartLocation);
				NpcInstance gk = NpcFunctions.getNearestGatekeeper(buffer);
				Location targetLocation = FarmZoneHolder.getInstance().getLocation(player);
//				Location middleRestartLocation = TeleportUtils.getRestartLocation(player, targetLocation, RestartType.TO_VILLAGE);
				NpcInstance middleGK = NpcFunctions.getNearestGatekeeper(targetLocation);				
				
				_log.info("Where am i?");
				
				_log.info("Teleportto restart loc:"+myRestartLocation);
				_log.info("Move to next buffer:"+buffer);
				_log.info("Move to next GK:"+gk);
				_log.info("Target location:"+targetLocation);
				
				if(gk.getObjectId() != middleGK.getObjectId())
				{
					_log.info("=>Tele to middle GK:"+middleGK);
					gk = middleGK;
				}
				
				_log.info("find spawn zone");
				TIntObjectMap<TeleportLocation> teleMap = gk.getTemplate().getTeleportList(1);
				double minDistance = Double.MAX_VALUE;
				Location spawnLocation = null;
				for(TeleportLocation teleLoc: teleMap.valueCollection())
				{
					double distanceFromSpawnLoc = teleLoc.distance(targetLocation);
					if(distanceFromSpawnLoc < minDistance && GeoEngine.canMoveToCoord(teleLoc.x, teleLoc.y, teleLoc.z, targetLocation.x, targetLocation.y, targetLocation.z, player.getGeoIndex()))
					{
						minDistance = distanceFromSpawnLoc;
						spawnLocation = teleLoc;
					}
				}
				
				if(spawnLocation != null)
				{
					_log.info("Teleport to farm zone entrance:"+spawnLocation);
					_log.info("Move to farm spot:"+targetLocation);
				}
				else
				{
					_log.info("Teleporto direct to farm spot:"+targetLocation);
				}
			break;
			default:
			break;
		}
		return true;
	}
	

	@SuppressWarnings("rawtypes")
	@Override
	public Enum[] getAdminCommandEnum()
	{
		return Commands.values();
	}

}
