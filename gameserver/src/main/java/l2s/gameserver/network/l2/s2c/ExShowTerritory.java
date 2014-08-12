package l2s.gameserver.network.l2.s2c;

public class ExShowTerritory extends L2GameServerPacket
{
	@Override
	protected void writeImpl()
	{
		writeEx(0x90);
		// TODO ddd[dd]
	}
}