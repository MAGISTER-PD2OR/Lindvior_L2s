package l2s.gameserver.network.l2.s2c;

/**
 * @author VISTALL
 */
public class ExPVPMatchUserDie extends L2GameServerPacket
{
	private int _blueKills, _redKills;

	public ExPVPMatchUserDie()
	{

	}

	@Override
	protected final void writeImpl()
	{
		writeEx(0x80);
		writeD(_blueKills);
		writeD(_redKills);
	}
}