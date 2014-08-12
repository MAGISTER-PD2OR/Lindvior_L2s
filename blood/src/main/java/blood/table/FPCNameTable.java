package blood.table;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import l2s.gameserver.database.DatabaseFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FPCNameTable
{
	private static final Logger 		_log = LoggerFactory.getLogger(FPCNameTable.class);
    private static FPCNameTable		 	_instance;
	private static List<String> 		_unusedNames	 		= new ArrayList<String>();
	private static List<String> 		_titleList		 		= new ArrayList<String>();
    private static List<String> 		_usedNames 				= new ArrayList<String>();
    
    public static FPCNameTable getInstance() {
        if (_instance == null) {
            _instance =
                new FPCNameTable();
        }
        return _instance;
    }
    
    private FPCNameTable()
    {
    	UpdateUsedNameList();
    	_unusedNames 	= parseNameListFromFile("config/fake_players.list");
    	_titleList 		= parseNameListFromFile("config/title.list");
    }
    
    private static void UpdateUsedNameList()
	{
		Connection con = null;
		try{
			con = DatabaseFactory.getInstance().getConnection();
	        PreparedStatement stm = con.prepareStatement("SELECT char_name FROM characters");
	        ResultSet rs = stm.executeQuery();
	        while (rs.next()) {
	        	_usedNames.add(rs.getString("char_name"));
            	//nPlayers++;
            }
	        _log.info("Used Name: "+_usedNames.size());
		}catch (Exception e) {
            //_log.error("Fake Players Engine: Error loading player: ", e);
        }
		
	}
    
    public static String getRandomName()
	{
    	Random random = new Random();
		int index = random.nextInt(_unusedNames.size());
		String name = _unusedNames.get(index);
		_unusedNames.remove(index);
		_usedNames.add(name);
		return name;
	}
    
    public static String getRandomTitle()
	{
    	if(_titleList == null)
    	{
    		_log.info("title list is null" );
    		return "";
    	}
    	_log.info("_titleList " + _titleList.size());
    	Random random = new Random();
		int index = random.nextInt(_titleList.size());
		String title = _titleList.get(index);
		return title;
	}
    
    private static List<String> parseNameListFromFile(String filepath)
	{
    	_log.info("Parse names from file: " + filepath);
    	List<String> 		nameList	= new ArrayList<String>();
		LineNumberReader 	lnr 		= null;
		
		try
		{
			File doorData = new File(filepath);
			lnr = new LineNumberReader(new BufferedReader(new FileReader(doorData)));
			
			String line;
			while ((line = lnr.readLine()) != null)
			{
				if ((line.trim().length() > 2) && (!line.startsWith("#"))  && (!_usedNames.contains(line)))
				{
					nameList.add(line);
				}
			}
			
			_log.info("Loaded " + nameList.size() + " names.");
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (lnr != null)
				{
					lnr.close();
				}
			}
			catch (Exception e1)
			{
			}
		}
		
		return nameList;
	}
}
