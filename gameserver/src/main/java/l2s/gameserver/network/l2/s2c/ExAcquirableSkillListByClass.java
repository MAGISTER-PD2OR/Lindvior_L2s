package l2s.gameserver.network.l2.s2c;

import java.util.Collection;

import l2s.gameserver.data.xml.holder.SkillAcquireHolder;
import l2s.gameserver.model.Player;
import l2s.gameserver.model.Skill;
import l2s.gameserver.model.SkillLearn;
import l2s.gameserver.tables.SkillTable;

/**
 * @author VISTALL
 * @date 22:22/25.05.2011
 */
public class ExAcquirableSkillListByClass extends L2GameServerPacket
{
	private Player _player;
	private Collection<SkillLearn> _skills;

	public ExAcquirableSkillListByClass(Player player)
	{
		_player = player;
		_skills = SkillAcquireHolder.getInstance().getAcquirableSkillListByClass(player);
	}

	@Override
	protected void writeImpl()
	{
		writeEx(0xFA);
		writeD(_skills.size());
		for(SkillLearn sk : _skills)
		{
			Skill skill = SkillTable.getInstance().getInfo(sk.getId(), sk.getLevel());
			if(skill == null)
				continue;

			writeD(sk.getId());
			writeD(sk.getLevel());
			writeD(sk.getCost());
			writeH(sk.getMinLevel());
			writeH(sk.getDualClassMinLvl()); // Dual-class min level.
			boolean haveItem = sk.getItemId() > 0;
			writeD(haveItem ? 1 : 0); //getRequiredItems()
			if(haveItem)
			{
				writeD(sk.getItemId());
				writeQ(sk.getItemCount());
			}

			if(skill.haveAnalogSkills())
			{
				Skill[] analogSkills = skill.getAnalogSkills(_player);
				writeD(analogSkills.length);
				for(Skill analogSkill : analogSkills)
				{
					writeD(analogSkill.getId());
					writeD(analogSkill.getLevel());
				}
			}
			else
				writeD(0);
		}
	}
}
