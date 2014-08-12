package l2s.gameserver.network.l2.s2c;

import l2s.gameserver.model.Player;

public class PartySmallWindowAdd extends L2GameServerPacket
{
	private final int _objectId, _loot;
	private final PartySmallWindowAll.PartyMember _member;

	public PartySmallWindowAdd(Player player, Player member)
	{
		_objectId = player.getObjectId();
		_loot = member.getParty().getLootDistribution();
		_member = new PartySmallWindowAll.PartySmallWindowMemberInfo(member).member;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x4F);
		writeD(_objectId);
		writeD(_loot);
		writeD(_member.objId);
		writeS(_member.name);
		writeD(_member.curCp);
		writeD(_member.maxCp);
		writeD(_member.curHp);
		writeD(_member.maxHp);
		writeD(_member.curMp);
		writeD(_member.maxMp);
		writeD(_member.vitality);
		writeD(_member.level);
		writeD(_member.classId);
		writeD(_member.sex);
		writeD(_member.raceId);
		writeD(0); // Hide Name
		writeD(0); // unk
		writeD(_member.isPartySubstituteStarted); // Идет поиск замены данному игроку.
	}
}