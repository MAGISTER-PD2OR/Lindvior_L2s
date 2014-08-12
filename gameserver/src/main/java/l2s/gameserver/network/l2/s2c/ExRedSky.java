package l2s.gameserver.network.l2.s2c;

public class ExRedSky extends L2GameServerPacket
{
	private int _duration;

	public ExRedSky(int duration)
	{
		_duration = duration;
	}

	@Override
	protected final void writeImpl()
	{
		writeEx(0x42); // sub id
		writeD(_duration);
	}
}