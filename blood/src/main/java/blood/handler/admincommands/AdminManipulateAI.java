package blood.handler.admincommands;

import gnu.trove.map.TIntObjectMap;
import l2s.gameserver.geodata.GeoEngine;
import l2s.gameserver.handler.admincommands.IAdminCommandHandler;
import l2s.gameserver.model.Player;
import l2s.gameserver.model.base.RestartType;
import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.templates.TeleportLocation;
import l2s.gameserver.utils.Location;
import l2s.gameserver.utils.TeleportUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import blood.FPCInfo;
import blood.ai.FPCDefaultAI;
import blood.base.FPCRole;
import blood.data.holder.FarmZoneHolder;
import blood.data.holder.NpcHelper;
//import l2s.gameserver.model.Effect;
//import l2s.gameserver.tables.PetDataTable;
//import l2s.gameserver.templates.item.ItemTemplate.Grade;

public class AdminManipulateAI implements IAdminCommandHandler
{
	private static final Logger _log = LoggerFactory.getLogger(AdminManipulateAI.class);
	
	private static enum Commands
	{
		admin_find_path, admin_tryai
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
			case admin_tryai:
				FPCInfo newInfo = new FPCInfo(activeChar);
				newInfo.setAI(FPCRole.NEXUS_EVENT.getAI(activeChar));
	//			newInfo.setPVEStyle(FPCPveStyle.PARTY);
	//			newInfo.setParty();
			break;
			case admin_find_path:
				FPCDefaultAI ai = FPCInfo.getInstance(activeChar).getAI();
				Player player = activeChar;
				
				ai.toggleDebug();
				// sleepy at start
				ai.addTaskSleep(10*1000);
				
				Location myRestartLocation = TeleportUtils.getRestartLocation(player, RestartType.TO_VILLAGE);
				NpcInstance buffer = NpcHelper.getClosestBuffer(myRestartLocation);
				NpcInstance gk = NpcHelper.getClosestGatekeeper(myRestartLocation);
				
				_log.info("Where am i?");
				
				ai.addTaskTele(myRestartLocation);
				ai.addTaskSleep(10*1000);
				
				_log.info("Teleportto restart loc:"+myRestartLocation);
				if(myRestartLocation.distance(buffer.getLoc()) < 4000)
					_log.info("Move to next buffer:"+buffer);
				else
					_log.info("Can't move to next buffer:"+buffer+" distance:"+myRestartLocation.distance(buffer.getLoc()));
				
				_log.info("Move to next GK:"+gk);
				ai.addTaskMove(gk.getLoc(), true);
				
				Location targetLocation = FarmZoneHolder.getInstance().getLocation(player);
				
				_log.info("Target location:"+targetLocation);
				if(targetLocation == null)
					return false;
				
				Location middleRestartLocation = TeleportUtils.getRestartLocation(player, targetLocation, RestartType.TO_VILLAGE);
				NpcInstance middleGK = NpcHelper.getClosestGatekeeper(middleRestartLocation);
				
				if(gk.getObjectId() != middleGK.getObjectId())
				{
					_log.info("=>Tele to middle GK:"+middleGK);
					gk = middleGK;
					ai.addTaskTele(gk.getLoc());
					ai.addTaskSleep(10*1000);
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
					ai.addTaskTele(spawnLocation);
					ai.addTaskSleep(10*1000);
					ai.addTaskMove(targetLocation, true);
				}
				else
				{
					_log.info("Teleporto direct to farm spot:"+targetLocation);
					ai.addTaskTele(targetLocation);
					ai.addTaskSleep(10*1000);
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
