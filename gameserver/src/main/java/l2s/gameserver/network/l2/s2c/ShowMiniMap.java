package l2s.gameserver.network.l2.s2c;

import l2s.gameserver.model.Player;

public class ShowMiniMap extends L2GameServerPacket
{
	private int _mapId;

	public ShowMiniMap(Player player, int mapId)
	{
		_mapId = mapId;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0xa3);
		writeD(_mapId);
		writeC(0x00);
	}
}