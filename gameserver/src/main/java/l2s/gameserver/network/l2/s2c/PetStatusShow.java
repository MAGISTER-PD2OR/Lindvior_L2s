package l2s.gameserver.network.l2.s2c;

import l2s.gameserver.model.Servitor;

public class PetStatusShow extends L2GameServerPacket
{
	private int _summonType, _summonObjId;

	public PetStatusShow(Servitor summon)
	{
		_summonType = summon.getServitorType();
		_summonObjId = summon.getObjectId();
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0xb1);
		writeD(_summonType);
		writeD(_summonObjId);
	}
}