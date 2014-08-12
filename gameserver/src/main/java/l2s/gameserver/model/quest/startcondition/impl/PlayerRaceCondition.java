package l2s.gameserver.model.quest.startcondition.impl;

import l2s.gameserver.model.Player;
import l2s.gameserver.model.base.Race;
import l2s.gameserver.model.quest.startcondition.ICheckStartCondition;

/**
 * @author : Ragnarok
 * @date : 02.04.12  21:50
 */
public class PlayerRaceCondition implements ICheckStartCondition
{
	private boolean _human;
	private boolean _elf;
	private boolean _delf;
	private boolean _orc;
	private boolean _dwarf;
	private boolean _kamael;

	public PlayerRaceCondition(boolean human, boolean elf, boolean delf, boolean orc, boolean dwarf, boolean kamael)
	{
		_human = human;
		_elf = elf;
		_delf = delf;
		_orc = orc;
		_dwarf = dwarf;
		_kamael = kamael;
	}

	@Override
	public boolean checkCondition(Player player)
	{
		if(_human && player.getRace() == Race.HUMAN)
			return true;

		if(_elf && player.getRace() == Race.ELF)
			return true;

		if(_delf && player.getRace() == Race.DARKELF)
			return true;

		if(_orc && player.getRace() == Race.ORC)
			return true;

		if(_dwarf && player.getRace() == Race.DWARF)
			return true;

		if(_kamael && player.getRace() == Race.KAMAEL)
			return true;

		return false;
	}
}