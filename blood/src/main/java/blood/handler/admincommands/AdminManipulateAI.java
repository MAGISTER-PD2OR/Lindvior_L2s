package blood.handler.admincommands;

import gnu.trove.map.TIntObjectMap;
import l2s.gameserver.ai.PlayerAI;
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
import blood.base.FPCPveStyle;
import blood.base.FPCRole;
import blood.data.holder.FarmLocationHolder;
import blood.data.holder.NpcHelper;
//import l2s.gameserver.model.Effect;
//import l2s.gameserver.tables.PetDataTable;
//import l2s.gameserver.templates.item.ItemTemplate.Grade;

public class AdminManipulateAI implements IAdminCommandHandler
{
	private static final Logger _log = LoggerFactory.getLogger(AdminManipulateAI.class);
	
	private static enum Commands
	{
		admin_tryai_party, 
		admin_tryai,
		admin_stopai
		}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean useAdminCommand(Enum comm, String[] wordList, String fullString, Player activeChar)
	{
		Commands command = (Commands) comm;

		if(!activeChar.getPlayerAccess().CanEditNPC)
			return false;
		
		FPCInfo newInfo;

		switch(command)
		{
			case admin_tryai:
				newInfo = new FPCInfo(activeChar);
				newInfo.setPVEStyle(FPCPveStyle.SOLO);
				newInfo.setAI(FPCRole.NEXUS_EVENT.getAI(activeChar));
				newInfo.getAI().toggleDebug();
	//			newInfo.setParty();
			break;
			
			case admin_tryai_party:
				newInfo = new FPCInfo(activeChar);
				newInfo.setPVEStyle(FPCPveStyle.PARTY);
				newInfo.setAI(FPCRole.NEXUS_EVENT.getAI(activeChar));
				newInfo.getAI().toggleDebug();
				newInfo.setParty();
				break;
				
			case admin_stopai:
				activeChar.setAI(new PlayerAI(activeChar));
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
