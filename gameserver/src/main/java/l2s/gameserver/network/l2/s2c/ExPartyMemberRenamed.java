package l2s.gameserver.network.l2.s2c;

public class ExPartyMemberRenamed extends L2GameServerPacket
{
	@Override
	protected void writeImpl()
	{
		writeEx(0xA7);
		// TODO ddd
	}
}