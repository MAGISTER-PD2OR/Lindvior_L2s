package l2s.gameserver.network.l2.s2c;

public class ExShowPetitionHtml extends L2GameServerPacket
{
	@Override
	protected void writeImpl()
	{
		writeEx(0xB2);
		// TODO dx[dcS]
	}
}