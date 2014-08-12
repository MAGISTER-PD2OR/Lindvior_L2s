package l2s.gameserver.network.l2.s2c;

import l2s.gameserver.model.Player;

/**
 *
 * @author monithly
 * TODO this
 */
public class ExExchangeSubstitute extends L2GameServerPacket
{
	public ExExchangeSubstitute(Player pl, Player pl2)
	{
		//
	}
	
	@Override
	protected void writeImpl()
	{
		writeEx(0x11C);
		writeD(0x00);
		writeQ(3000000L);
		writeD(0x00);
	}
}
