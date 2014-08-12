package l2s.gameserver.utils;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Clients 
{
	private static final Logger _log = LoggerFactory.getLogger(Clients.class);
	
	private static List<String> _knownIps = new ArrayList<String>();
	private static String client0 = "127.0.0.1";  // local
	private static String client1 = "127.0.0.1";  // denta - block
	private static String client2 = "127.0.0.1"; // 
	private static String client3 = "127.0.0.1"; // 
	private static String client4 = "203.146.215.212"; // fenixz
	private static String client5 = "91.204.122.80"; // l2gold
	private static String client6 = "127.0.0.1"; // ankam26 - block
	private static String client7 = "127.0.0.1"; // lineage2media - block
	private static String client8 = "127.0.0.1"; // Hades (akcia) - block
	private static String client9 = "80.71.255.29"; // Bonux server
	private static String client10 = "212.220.1.38"; // ivkadre
	private static String client11 = "188.138.84.236"; // jssom
	private static String client12 = "188.165.193.90"; // Tweety
	private static String client13 = "95.138.224.86"; // Scorpius - hf>lind	
	private static String client14 = "94.198.109.166"; // 166		
	
	public static void Load()
	{
		_knownIps.add(client0);
		_knownIps.add(client1);
		_knownIps.add(client2);
		_knownIps.add(client3);
		_knownIps.add(client4);
		_knownIps.add(client5);
		_knownIps.add(client6);
		_knownIps.add(client7);
		_knownIps.add(client8);
		_knownIps.add(client9);
		_knownIps.add(client10);
		_knownIps.add(client11);
		_knownIps.add(client12);
		_knownIps.add(client13);	
		_knownIps.add(client14);			
	}
	
	public static List<String> getIps()
	{
		return _knownIps;
	}
}
