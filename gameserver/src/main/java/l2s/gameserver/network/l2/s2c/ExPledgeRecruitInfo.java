package l2s.gameserver.network.l2.s2c;

/**
 * @author Bonux
**/
public class ExPledgeRecruitInfo extends L2GameServerPacket
{
	protected void writeImpl()
	{
		writeEx(0x149);
		writeS("");
		writeS("");
		writeD(0);
		writeD(0);
		writeD(0);
	}
}