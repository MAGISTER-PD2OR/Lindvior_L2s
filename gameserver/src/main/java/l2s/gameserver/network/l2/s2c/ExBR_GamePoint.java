package l2s.gameserver.network.l2.s2c;

import l2s.gameserver.model.Player;

public class ExBR_GamePoint extends L2GameServerPacket
{
	private int _objectId;
	private long _points;

	public ExBR_GamePoint(Player player)
	{
		_objectId = player.getObjectId();
		_points = player.getPremiumPoints();
	}

	@Override
	protected void writeImpl()
	{
		writeEx(0xD6);
		writeD(_objectId);
		writeQ(_points);
		writeD(0x00); //??
	}
}