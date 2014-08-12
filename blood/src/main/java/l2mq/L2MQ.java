package l2mq;

import l2mq.callback.ChatterSay;
import l2mq.callback.MQMailer;
import l2s.gameserver.model.Player;
import l2s.gameserver.network.l2.components.ChatType;

import org.gearman.Gearman;
import org.gearman.GearmanClient;
import org.gearman.GearmanServer;
import org.gearman.GearmanWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import blood.Blood;

public class L2MQ
{
	@SuppressWarnings("unused")
	private static final Logger 		_log 			= LoggerFactory.getLogger(L2MQ.class);
	private static L2MQ 				_instance;
	
	private static String 				_mqServerIP		= "125.212.219.44";
	private static Integer 				_mqServerPort	= 4730;
	
	private static Gearman 				_gearman;
	private static GearmanClient 		_client;
	private static GearmanServer 		_server;
	private static GearmanWorker		_worker;
		
	public static L2MQ getInstance() {
        if (_instance == null) {
        	
            _instance =
                new L2MQ();
        }
        return _instance;
    }
	
	private L2MQ() {
		if(!Blood.MQ_ENABLE)
			return;
		getWorker(); // set worker
	}
	
	public static Gearman getGearman()
	{
		if(_gearman == null)
		{
			_gearman = Gearman.createGearman();
		}
		
		return _gearman;
	}
	
	public static GearmanServer getGearmanServer()
	{
		if(_server == null)
		{
			_server = getGearman().createGearmanServer(_mqServerIP, _mqServerPort);
		}
		
		return _server;
	}
	
	public static GearmanClient getClient()
	{
		if(_client == null)
		{
			_client = getGearman().createGearmanClient();
			_client.addServer(getGearmanServer());
		}
		
		return _client;
	}
	
	public static GearmanWorker getWorker()
	{
		if(_worker == null)
		{
			_worker = getGearman().createGearmanWorker();
			_worker.addServer(getGearmanServer());
			asignTaskForWorker(_worker);
		}
		
		return _worker;
	}
	
	public static void asignTaskForWorker(GearmanWorker worker)
	{
		worker.addFunction("gameMail_Kain", new MQMailer());
		worker.addFunction("gameSay_Kain", new ChatterSay());
	}
	
	
	public static class JobResult
	{
		public boolean result = false;
		public String msg;
	}
	
	public static void addFacebookStatus(Player player, String msg)
	{
		addBackgroundJob("newStatus", player.getAccountName()+";"+player.getObjectId()+";"+msg);
	}
	
	public void addChat(Player receiver, String msg, ChatType chatType, Player sender)
	{
//		if(receiver.isFakePlayer())
//			addBackgroundJob("chat", receiver.getAccountName()+";"+receiver.getName()+";"+sender.getName()+";"+chatType.ordinal()+";"+msg);
	}
	
	public static void requestAnswer(Player player, String msg)
	{
		addBackgroundJob("chatFromGame", player.getName()+";"+player.getObjectId()+";"+msg);
	}
	
	public static void addBackgroundJob(String jobName, String jobData)
	{
		if(!Blood.MQ_ENABLE)
			return;
		jobData = "Kain;" + jobData;
		System.out.println(jobName+"|"+jobData);
		getClient().submitBackgroundJob(jobName, jobData.getBytes());
	}
}
