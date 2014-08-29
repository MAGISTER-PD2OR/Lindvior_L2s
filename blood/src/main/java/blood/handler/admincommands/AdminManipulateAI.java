package blood.handler.admincommands;

import l2s.gameserver.ai.PlayerAI;
import l2s.gameserver.handler.admincommands.IAdminCommandHandler;
import l2s.gameserver.model.Player;
import l2s.gameserver.model.Skill;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import blood.FPCInfo;
import blood.base.FPCPveStyle;
import blood.base.FPCRole;

public class AdminManipulateAI implements IAdminCommandHandler
{
	
	@SuppressWarnings("unused")
	private static final Logger _log = LoggerFactory.getLogger(AdminManipulateAI.class);
	
	private static enum Commands
	{
		admin_tryai_party, 
		admin_tryai,
		admin_stopai,
		admin_dump_skills
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
				
			case admin_dump_skills:
				for(Skill skill: activeChar.getAllSkillsArray())
				{
					if(skill.isPassive())
						continue;
					String niceName = skill.getName().toUpperCase().replace(" ", "_").replace("'", "");
					System.out.println("SKILL_"+niceName+" = "+skill.getId()+",");
				}
			
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
