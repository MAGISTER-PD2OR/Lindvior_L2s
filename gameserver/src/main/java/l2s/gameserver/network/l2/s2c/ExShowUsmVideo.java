package l2s.gameserver.network.l2.s2c;

public class ExShowUsmVideo extends L2GameServerPacket
{
	private int _usmVideoId;

	public ExShowUsmVideo(int usmVideoId)
	{
		_usmVideoId = usmVideoId;
	}

	@Override
	protected void writeImpl()
	{
		writeEx(0x10E);
		writeD(_usmVideoId);
	}
}