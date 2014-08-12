package l2s.gameserver.network.l2.s2c;

public class ExPeriodicItemList extends L2GameServerPacket
{
	@Override
	protected void writeImpl()
	{
		writeEx(0x88);
		writeD(0); // count of dd
	}
}