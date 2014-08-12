package l2s.gameserver.network.l2.s2c;

import l2s.gameserver.model.Player;
import l2s.gameserver.model.actor.instances.player.Block;

/**
 * @author Bonux
 */
public class BlockList extends L2GameServerPacket
{
	private Block[] _blockList;

	public BlockList(Player player)
	{
		_blockList = player.getBlockList().values();
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0xD5);
		writeD(_blockList.length);
		for(Block b : _blockList)
		{
			writeS(b.getName());
			writeS(b.getMemo());
		}
	}
}