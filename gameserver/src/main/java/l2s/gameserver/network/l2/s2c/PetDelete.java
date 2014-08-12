package l2s.gameserver.network.l2.s2c;

public class PetDelete extends L2GameServerPacket
{
	private int _petId;
	private int _petnum;

	public PetDelete(int petId, int petnum)
	{
		_petId = petId;
		_petnum = petnum;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0xb7);
		writeD(_petnum);
		writeD(_petId);
	}
}