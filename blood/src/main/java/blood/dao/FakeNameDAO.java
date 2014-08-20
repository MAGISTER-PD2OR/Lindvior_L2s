package blood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import l2s.commons.dbutils.DbUtils;
import l2s.gameserver.database.DatabaseFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FakeNameDAO {
	private static final Logger _log = LoggerFactory.getLogger(FakeNameDAO.class);

	private static FakeNameDAO _instance ;
	
	public static FakeNameDAO getInstance()
	{
		if(_instance == null)
		{
			_instance = new FakeNameDAO();
		}
		return _instance;
	}
	
	private FakeNameDAO()
	{
		// TODO
	}
	
	// race by name
	
	public static boolean isHumanName(String name)
	{
		return name.toLowerCase().contains("human");
	}
	
	public static boolean isElfName(String name)
	{
		return (name.toLowerCase().contains("elf") && !name.toLowerCase().contains("darkelf"))
				|| isSingerName(name);
	}
	
	public static boolean isDarkElfName(String name)
	{
		return name.toLowerCase().contains("darkelf")
				|| isDancerName(name);
	}
	
	public static boolean isDwarfName(String name)
	{
		return name.toLowerCase().contains("lun") 
				|| name.toLowerCase().contains("dwarf")
				|| isSmithName(name)
				|| isSpoilerName(name);
	}
	
	public static boolean isKamaelName(String name)
	{
		return name.toLowerCase().contains("kamael");
	}
	
	public static boolean isOrcName(String name)
	{
		return name.toLowerCase().contains("orc");
	}
	
	// sex by name
	
	public static boolean isMaleName(String name)
	{
		return name.toLowerCase().contains("male");
	}
	
	public static boolean isFemaleName(String name)
	{
		return name.toLowerCase().contains("female");
	}
	
	// class by name	
	public static boolean isMysticName(String name)
	{
		return name.toLowerCase().contains("mystic")
				|| isNukerName(name)
				|| isHealerName(name)
				|| isSummonerName(name);
	}
	
	public static boolean isFighterName(String name)
	{
		return name.toLowerCase().contains("fighter")
				|| isTankerName(name)
				|| isWarriorName(name)
				|| isDaggerName(name)
				|| isRangerName(name)
				|| isDancerName(name)
				|| isSingerName(name);
	}
	
	public static boolean isSmithName(String name)
	{
		return name.toLowerCase().contains("che")
				|| name.toLowerCase().contains("tao");
	}
	
	public static boolean isSpoilerName(String name)
	{
		return name.toLowerCase().contains("spoil");
	}
		
	public static boolean isTankerName(String name)
	{
		return name.toLowerCase().contains("tank");
	}
	
	public static boolean isWarriorName(String name)
	{
		return name.toLowerCase().contains("warrior");
	}
	
	public static boolean isDaggerName(String name)
	{
		return name.toLowerCase().contains("dagger")
				|| name.toLowerCase().contains("dao")
				|| name.toLowerCase().contains("assasin")
				|| name.toLowerCase().contains("satthu");
	}
	
	public static boolean isRangerName(String name)
	{
		return name.toLowerCase().contains("ranger")
				|| name.toLowerCase().contains("bow")
				|| name.toLowerCase().contains("cung");
	}
	
	public static boolean isNukerName(String name)
	{
		return name.toLowerCase().contains("nuker")
				|| name.toLowerCase().contains("phep")
				|| name.toLowerCase().contains("magic");
	}
	
	public static boolean isSummonerName(String name)
	{
		return name.toLowerCase().contains("sum");
	}
	
	public static boolean isHealerName(String name)
	{
		return name.toLowerCase().contains("heal");
	}
	
	public static boolean isBufferName(String name)
	{
		return name.toLowerCase().contains("support")
				|| name.toLowerCase().contains("buff")
				|| name.toLowerCase().contains("iss")
				|| isSingerName(name)
				|| isDancerName(name);
	}
	
	public static boolean isSingerName(String name)
	{
		return name.toLowerCase().contains("sing")
				|| name.toLowerCase().contains("song");
	}
	
	public static boolean isDancerName(String name)
	{
		return name.toLowerCase().contains("dance")
				|| name.toLowerCase().contains("nhay");
	}
	
	public String getName()
	{
		return getName(1).get(0);
	}
	
	public List<String> getName(int limit)
	{
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rset = null;
		List<String> list_name = new ArrayList<String>();
		try
		{
			con = DatabaseFactory.getInstance().getConnection();
			statement = con.prepareStatement("SELECT char_name FROM fake_name WHERE is_used = ?  ORDER BY RAND() LIMIT ?");
			statement.setInt(1, 0);
			statement.setInt(2, limit);
			rset = statement.executeQuery();
			while(rset.next())
			{
				list_name.add(rset.getString("char_name"));
			}
		}
		catch(final Exception e)
		{
			_log.error("", e);
		}
		finally
		{
			DbUtils.closeQuietly(con, statement);
		}
		return list_name;
	}
	
	public void useName(String char_name)
	{
		Connection con = null;
		PreparedStatement statement = null;
		try
		{
			con = DatabaseFactory.getInstance().getConnection();
			statement = con.prepareStatement("UPDATE fake_name set is_used = ? WHERE char_name = ?");
			statement.setInt(1, 1);
			statement.setString(2, char_name);
			statement.executeQuery();
			
		}
		catch(final Exception e)
		{
			_log.error("", e);
		}
		finally
		{
			DbUtils.closeQuietly(con, statement);
		}
	}
}
