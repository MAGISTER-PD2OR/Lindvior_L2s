package l2s.gameserver.network.l2.s2c;

/**
 * Format: (chd)
 */
public class ExCubeGameRequestReady extends L2GameServerPacket
{
	@Override
	protected void writeImpl()
	{
		writeEx(0x98);
		writeD(0x04);
	}
}