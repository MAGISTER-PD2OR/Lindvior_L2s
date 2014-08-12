package l2s.gameserver.network.l2.s2c;

public class ExTutorialList extends L2GameServerPacket
{
	@Override
	protected void writeImpl()
	{
		writeEx(0x6C);
		writeS("");
		writeD(0x00);
		writeD(0x00);
		writeD(0x00);
	}
}
