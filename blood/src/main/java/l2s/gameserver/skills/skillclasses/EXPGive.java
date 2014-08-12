package l2s.gameserver.skills.skillclasses;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import l2s.gameserver.model.Creature;
import l2s.gameserver.model.Player;
import l2s.gameserver.model.Skill;
import l2s.gameserver.model.World;
import l2s.gameserver.model.pledge.Clan;
import l2s.gameserver.network.l2.components.SystemMsg;
import l2s.gameserver.network.l2.s2c.ExMentorList;
import l2s.gameserver.network.l2.s2c.PledgeShowMemberListDelete;
import l2s.gameserver.network.l2.s2c.PledgeShowMemberListDeleteAll;
import l2s.gameserver.network.l2.s2c.SystemMessage2;
import l2s.gameserver.templates.StatsSet;
import l2s.gameserver.utils.ItemFunctions;
import l2s.gameserver.utils.Mentoring;

/**
 * @author cruel
 */
public class EXPGive extends Skill
{
	private final long _power;

	public EXPGive(StatsSet set)
	{
		super(set);

		_power = 5078544041L;
	}

	@Override
	public boolean checkCondition(final Creature activeChar, final Creature target, boolean forceUse, boolean dontMove, boolean first)
	{
		if(!activeChar.isPlayer())
			return false;

		return super.checkCondition(activeChar, target, forceUse, dontMove, first);
	}

	@Override
	public void useSkill(Creature activeChar, List<Creature> targets)
	{
		if(!activeChar.isPlayer())
			return;
		
		for(Creature target : targets)
		{
			if(target != null && target.isPlayer())
			{
				Player player = target.getPlayer();

				if(player.getLevel() < 86)
				{
					//remove all the mentee relation
					int mentorId = player.getMenteeList().getMentor();
					//System.out.println("player.getMenteeList() " + player.getMenteeList().toString());
					//System.out.println("player.getMenteeList() " + player.getMenteeList().size());
					//System.out.println("getMentor "  + mentorId);
					
					if(mentorId !=0)
					{
						Player mentorPlayer = World.getPlayer(mentorId);
						if(mentorPlayer != null)
						{
							Mentoring.removeMentoring(mentorPlayer, player, true);
							//do not apply mentor system penalty in this case
						}
					}
					
					//remove all academy relation
					Clan clan = player.getClan();
					if(clan != null)
					{
						int subUnitType = player.getPledgeType();
						if(subUnitType == Clan.SUBUNIT_ACADEMY)
						{
							clan.removeClanMember(subUnitType, player.getObjectId());
		
							clan.broadcastToOnlineMembers(new SystemMessage2(SystemMsg.S1_HAS_WITHDRAWN_FROM_THE_CLAN).addString(player.getName()), new PledgeShowMemberListDelete(player.getName()));
		
							player.setLvlJoinedAcademy(0);
		
							player.setClan(null);
							if(!player.isNoble())
								player.setTitle(StringUtils.EMPTY);
		
							player.setLeaveClanCurTime();
							player.broadcastCharInfo();
		
							player.sendPacket(SystemMsg.YOU_HAVE_RECENTLY_BEEN_DISMISSED_FROM_A_CLAN, PledgeShowMemberListDeleteAll.STATIC);
						}
					}
					
					if(player.getLevel() < 77)
					{

						//teleport to GM room, force change class
						player.teleToLocation(-114552, -249432, -3012);
						
					}
					
				}
				
				player.addExpAndSp(_power, 0);

				getEffects(activeChar, target, false);
			}
		}

		if(isSSPossible())
			activeChar.unChargeShots(isMagic());

		super.useSkill(activeChar, targets);
	}
}
