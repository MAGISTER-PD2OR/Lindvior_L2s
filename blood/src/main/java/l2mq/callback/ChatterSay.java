package l2mq.callback;

import l2s.gameserver.Config;
import l2s.gameserver.model.GameObjectsStorage;
import l2s.gameserver.model.Player;
import l2s.gameserver.network.l2.s2c.Say2;
import l2s.gameserver.network.l2.components.ChatType;
import l2s.gameserver.utils.MapUtils;

import org.gearman.GearmanFunction;
import org.gearman.GearmanFunctionCallback;


/**
 * The echo worker polls jobs from a job server and execute the echo function.
 *
 * The echo worker illustrates how to setup a basic worker
 */
public class ChatterSay implements GearmanFunction {

	@Override
    public byte[] work(String function, byte[] data, GearmanFunctionCallback callback) throws Exception 
    {

		String result 	= "";
		String dataStr = new String(data);
		String[] wordList = dataStr.split(";", 4);
		
		if(wordList.length < 4)
			return "error".getBytes();
		
		String 	senderName 		= wordList[0];
//		int		senderId		= Integer.parseInt(wordList[1]);
		String	receiverName 	= wordList[1];
		int		chatTypeOrd		= Integer.parseInt(wordList[2]);
		String	msg				= wordList[3];
//		int		receiverId		= Integer.parseInt(wordList[4]);
		
		
		ChatType chatType = ChatType.TELL;
		
		for(ChatType _chatType: ChatType.VALUES)
		{
			if(_chatType.ordinal() == chatTypeOrd)
			{
				chatType = _chatType;
				break;
			}
		}
		
		Player sender = GameObjectsStorage.getPlayer(senderName);
		Player receiver = GameObjectsStorage.getPlayer(receiverName);
		
		if(sender == null || receiver == null){
			return "error".getBytes();
		}
		
		Say2 cs = new Say2(sender.getObjectId(), chatType, senderName, msg);
		
		switch(chatType)
		{
			case TELL:
//				Player receiver = GameObjectsStorage.getPlayer(receiverId);
				if(receiver != null)
					receiver.sendPacket(cs);
				break;
				
			case SHOUT:
//				Player sender = GameObjectsStorage.getPlayer(senderId);
				
				if(sender != null)
				{
					if(Config.GLOBAL_TRADE_CHAT)
						announce(sender, cs);
					else
						shout(sender, cs);
				}
				
				break;
			
			default:
		}
		
		return result.getBytes();
    }
	
	private static void shout(Player activeChar, Say2 cs)
	{
		int rx = MapUtils.regionX(activeChar);
		int ry = MapUtils.regionY(activeChar);
		int offset = Config.SHOUT_OFFSET;

		for(Player player : GameObjectsStorage.getAllPlayersForIterate())
		{
			if(player == activeChar || activeChar.getReflection() != player.getReflection() || player.isBlockAll()) /* TODO fix block people */
				continue;

			int tx = MapUtils.regionX(player);
			int ty = MapUtils.regionY(player);

			if(tx >= rx - offset && tx <= rx + offset && ty >= ry - offset && ty <= ry + offset || activeChar.isInRangeZ(player, Config.CHAT_RANGE))
				player.sendPacket(cs);
		}
	}

	private static void announce(Player activeChar, Say2 cs)
	{
		for(Player player : GameObjectsStorage.getAllPlayersForIterate())
		{
			if(player == activeChar || activeChar.getReflection() != player.getReflection() || player.isBlockAll())
				continue;

			player.sendPacket(cs);
		}
	}

}
