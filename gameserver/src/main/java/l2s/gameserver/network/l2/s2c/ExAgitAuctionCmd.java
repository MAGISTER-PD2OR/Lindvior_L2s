package l2s.gameserver.network.l2.s2c;

/**
 *
 * @author monithly
 */
public class ExAgitAuctionCmd extends L2GameServerPacket
{
	@Override
	protected void writeImpl()
	{
		writeEx(0xD2);
	}
}