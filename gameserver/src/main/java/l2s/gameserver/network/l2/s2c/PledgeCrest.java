package l2s.gameserver.network.l2.s2c;

import l2s.gameserver.Config;

public class PledgeCrest extends L2GameServerPacket
{
	private int _crestId;
	private int _crestSize;
	private byte[] _data;

	public PledgeCrest(int crestId, byte[] data)
	{
		_crestId = crestId;
		_data = data;
		_crestSize = _data.length;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x6a);
		writeD(Config.REQUEST_ID);
		writeD(_crestId);
		writeD(_crestSize);
		writeB(_data);
	}
}