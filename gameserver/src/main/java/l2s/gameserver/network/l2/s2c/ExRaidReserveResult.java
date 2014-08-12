package l2s.gameserver.network.l2.s2c;

public class ExRaidReserveResult extends L2GameServerPacket
{
	@Override
	protected void writeImpl()
	{
		writeEx(0xB7);
		// TODO dx[dddd]
	}
}