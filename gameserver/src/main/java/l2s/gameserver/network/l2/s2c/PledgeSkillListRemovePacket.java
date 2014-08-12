package l2s.gameserver.network.l2.s2c;

public class PledgeSkillListRemovePacket extends L2GameServerPacket
{
	public PledgeSkillListRemovePacket()
	{
	}

	@Override
	protected void writeImpl()
	{
		writeEx(0x3C);
	}
}