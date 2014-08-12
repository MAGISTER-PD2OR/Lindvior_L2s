package l2s.gameserver.network.l2.s2c;

public class ExRaidCharSelected extends L2GameServerPacket
{
	@Override
	protected void writeImpl()
	{
		writeEx(0xB6);
		// just a trigger
	}
}