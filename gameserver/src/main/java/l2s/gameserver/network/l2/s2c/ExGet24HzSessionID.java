package l2s.gameserver.network.l2.s2c;

public class ExGet24HzSessionID extends L2GameServerPacket
{
	protected void writeImpl()
	{
		writeEx(0x109);
		//TODO: [Bonux]
	}
}