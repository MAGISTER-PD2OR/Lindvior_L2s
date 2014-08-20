package blood;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


//import l2s.gameserver.network.loginservercon.LoginServerCommunication;
import l2mq.L2MQ;
import l2s.commons.configuration.ExProperties;
import l2s.commons.dbutils.DbUtils;
import l2s.gameserver.Config;
import l2s.gameserver.GameServer;
import l2s.gameserver.ThreadPoolManager;
import l2s.gameserver.database.DatabaseFactory;
import l2s.gameserver.handler.admincommands.AdminCommandHandler;
import l2s.gameserver.network.authcomm.AuthServerCommunication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import blood.base.FPCItem;
import blood.base.FPCRole;
import blood.base.FPCSpawnStatus;
import blood.data.parser.FPItemParser;
import blood.data.parser.FarmZoneParser;
import blood.handler.admincommands.AdminFakePlayers;
public
class Blood {
	
    private static final String LOAD_OFFLINE_STATUS = "SELECT obj_id FROM fpc";
    private static final Logger 		_log = LoggerFactory.getLogger(Blood.class);
    private static Blood 	_instance;
    private static long _disconnect_timeout	= 60000L;  //every 10 minutes
    
    public static int FPC_IDLE = 0;
    public static int FPC_NEXUS = 0;
    public static int FPC_MARKET = 0;
    public static boolean MQ_ENABLE = false;
    
    public static boolean AI_ATTACK_ALLOW = true;
    public static boolean IS_FENCE = false;
    public static int FENCE_PARENT_ID = 0;
    
    public static Blood getInstance() {
        if (_instance == null) {
            _instance =
                new Blood();
        }
        return _instance;
    }
    
    
    private Blood() {
    	_log.info("Initiate BloodFakePlayers.");
    	loadConfig();
//    	FPCItem.getInstance();
//    	FPCMerchantTable.getInstance();
    	storeFakePlayers();
    	FPItemParser.getInstance().load();
    	FarmZoneParser.getInstance().load();
    	if(MQ_ENABLE)
    		L2MQ.getInstance();
    	
    	// add new comment
    	
        AdminCommandHandler.getInstance().registerAdminCommandHandler(new AdminFakePlayers());
		ThreadPoolManager.getInstance().scheduleAtFixedRate(new ManagerTask(), 30000L, 30000L); //every 30 seconds
		ThreadPoolManager.getInstance().scheduleAtFixedRate(new DisconnectTask(), _disconnect_timeout, _disconnect_timeout);
    }
    
    public static void loadConfig()
    {
    	ExProperties bfconfig = Config.load("config/blood.ini");
    	IS_FENCE = bfconfig.getProperty("fence", false);
    	FENCE_PARENT_ID = bfconfig.getProperty("fenceParentId", 0);
    	
    	FPC_IDLE = bfconfig.getProperty("FPC_IDLE", 10);
    	FPC_NEXUS = bfconfig.getProperty("FPC_NEXUS", 0);
    	FPC_MARKET = bfconfig.getProperty("FPC_MARKET", 0);
    	MQ_ENABLE = bfconfig.getProperty("MQ_ENABLE", false);
    }
    
    public static void storeFakePlayers() {
        _log.info("storeFakePlayers: get data from DB");
        Connection con = null;
        try {
            con = DatabaseFactory.getInstance().getConnection();
            PreparedStatement stm = con.prepareStatement(LOAD_OFFLINE_STATUS);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
            	FPCInfo newChar = new FPCInfo(rs.getInt("obj_Id"));
//            	newChar.spawn();
//            	newChar.uyThac();
            }
            rs.close();
            stm.close();
            
        } catch (Exception e) {
            _log.error("Fake Players Engine : Error while loading player: ", e);
        } finally {
            DbUtils.closeQuietly(con);
        }
    }
    
    public static void populationControl()
    {
    	int max_giving_birth = 200;
    	int max_move_role = 100;
    	int diff, i;
    	// birth
    	diff = FPCSpawnStatus.getDiff(); 
//    	_log.info("diff birth:"+diff);
    	i = 0;
    	while( i < diff && i < max_giving_birth && diff > 0)
    	{
    		FPCCreator.createNewChar();
    		i++;
    	}
    	
    	// check all group
    	for (FPCRole role : FPCRole.values()) 
    	{
    		diff = role.getPadding();
//    		_log.info("diff role:"+diff+" "+role);
        	i = 0;
        	
        	while( i < Math.abs(diff) && i < max_move_role)
        	{
        		//_log.info("i < Math.abs(diff) && i < max_move_role");
        		if(role == FPCRole.IDLE) // spawn/kick task
        		{
        			//_log.info("role ==  FPCRole.IDLE");
        			if(diff > 0) // spawn more
        			{
        				//_log.info("diff > 0");
        				FPCSpawnStatus.OFFLINE.getRandom().spawn();
        			}
        			else // kick more
        			{
        				//_log.info("kick");
        				role.getRandom().kick();
        			}
        				
        		}
        		else // change role
        		{
        			if(diff > 0 && FPCRole.IDLE.getSize() > 0) // idle -> role
        				FPCRole.IDLE.getRandom().setRole(role);
        			else if(diff < 0 && role.getSize() > 0) // role -> idle
        				role.getRandom().setRole(FPCRole.IDLE);
        		}
        		i++;
        	}
    	}
    	
    }
    
    public static void disconnectControl()
	{
		//make sure all the online FPC is totally replace after 6 hours
    	int disconnectCount	 	= FPCRole.getCCU()/36;
    	
    	for(int i=0;i<disconnectCount;i++)
    	{
    		FPCInfo actor = FPCSpawnStatus.ONLINE.getRandom();
    		
    		if(!actor.isInEvent()) actor.kick(); 
    	}
	
	}

    public static void debug()
    {
    	for(FPCSpawnStatus status: FPCSpawnStatus.values())
    	{
    		_log.info("Status: " + status + " size: " + status.getSize());
    	}
    	
    	for(FPCRole role: FPCRole.values())
    	{
    		_log.info("Role: " + role + " size: " + role.getSize() + " quota: "+role.getQuota());
    	}
    	
    }
    
    public static class ManagerTask implements Runnable
	{
		@SuppressWarnings("synthetic-access")
		@Override
		public void run()
		{
			try
			{
				populationControl();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
    
    public static class DisconnectTask implements Runnable
   	{
   		@SuppressWarnings("synthetic-access")
   		@Override
   		public void run()
   		{
   			try
   			{
   				for(FPCRole role: FPCRole.values())
   				{
   					role.quotaPadding();
   				}
   				//disconnectControl();
   				//mainEventChecker();
   			}
   			catch (Exception e)
   			{
   				e.printStackTrace();
   			}
   		}

   	}
    
    public static void main(String[] args) throws Exception
    {
    	Blood.loadConfig();
    	if(Blood.IS_FENCE){
    		_log.info("Fence is active");
    		Config.load();
    		AuthServerCommunication.getInstance().start();
    	}else{
    		GameServer.main(args);
    	    Blood.getInstance();
    	}
    }
    
}