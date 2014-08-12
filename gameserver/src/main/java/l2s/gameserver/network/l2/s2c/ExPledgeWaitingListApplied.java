package l2s.gameserver.network.l2.s2c;

/**
 * @author Bonux
**/
public class ExPledgeWaitingListApplied extends L2GameServerPacket
{
	protected void writeImpl()
	{
		writeEx(0x14D);
		writeD(0);
		writeS("");
		writeS("");
		writeD(0);
		writeD(0);
		writeD(0);
		writeS("");
		writeS("");
	}
}