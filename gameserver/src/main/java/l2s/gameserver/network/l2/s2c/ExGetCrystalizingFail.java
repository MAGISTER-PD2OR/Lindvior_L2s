package l2s.gameserver.network.l2.s2c;

public class ExGetCrystalizingFail extends L2GameServerPacket
{
	@Override
	protected void writeImpl()
	{
		writeEx(0xE2);
	}
}
