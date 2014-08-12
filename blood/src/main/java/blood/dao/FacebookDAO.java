package blood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javolution.util.FastMap;
import l2s.commons.dbutils.DbUtils;
import l2s.gameserver.database.DatabaseFactory;
import l2s.gameserver.database.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FacebookDAO
{
	private static final Logger _log = LoggerFactory.getLogger(FacebookDAO.class);

	private static FacebookDAO _instance ;
	
	private static FastMap<String, String> _wallPost = new FastMap<String, String>();
	
	public static FacebookDAO getInstance()
	{
		if(_instance == null)
		{
			_instance = new FacebookDAO();
		}
		return _instance;
	}
	
	private FacebookDAO()
	{
		findAllPost();
	}
	
	public static FastMap<String, String> getWallPost()
	{
		return _wallPost;
	}
	
	public class FacebookRecord
	{
		public int id;
		public String account_name;
		public String type;
		public String facebook_id;
		public int like_count;
		public int like_reward;
		public int post_date;
	}
	
	public static class FacebookPost
	{
		public String message;
		public String facebook_id;
		
		public FacebookPost(String facebook_id, String message)
		{
			this.message = message;
			this.facebook_id = facebook_id;
			
			_wallPost.put(facebook_id, message);
		}
		
	}
	
	public boolean findAllPost()
	{
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rset = null;
		try
		{
			con = DatabaseFactory.getInstance().getConnection();
			statement = con.prepareStatement("SELECT * FROM facebook_post ORDER BY facebook_id DESC");
			rset = statement.executeQuery();
			while(rset.next())
			{
				new FacebookPost(rset.getString("facebook_id"), rset.getString("message"));
			}
		}
		catch(final Exception e)
		{
			_log.error("", e);
			return false;
		}
		finally
		{
			DbUtils.closeQuietly(con, statement);
		}
		return true;
	}
	
	public boolean savePost(FacebookPost post)
	{
		
		Connection con = null;
		PreparedStatement statement = null;
		try
		{
			con = DatabaseFactory.getInstance().getConnection();
			statement = con.prepareStatement("INSERT INTO facebook_post (facebook_id, message) VALUES (?,?) ON DUPLICATE KEY UPDATE message = ?");
			statement.setString(1, post.facebook_id);
			statement.setString(2, post.message);
			statement.setString(3, post.message);
			statement.executeUpdate();
		}
		catch(final Exception e)
		{
			_log.error("", e);
			return false;
		}
		finally
		{
			DbUtils.closeQuietly(con, statement);
		}
		return true;
	}
	
	public boolean insert(String account_name, String type)
	{
		return insert(account_name, type, "");
	}
	
	public boolean insert(String account_name, String type, String facebook_id)
	{
		return insert(account_name, type, facebook_id, (int) (System.currentTimeMillis() / 1000L));
	}
	
	public boolean insert(String account_name, String type, String facebook_id, int post_date)
	{
		Connection con = null;
		PreparedStatement statement = null;
		try
		{
			con = DatabaseFactory.getInstance().getConnection();
			statement = con.prepareStatement("INSERT INTO `facebook` (account_name, type, facebook_id, post_date) VALUES (?,?,?,?)");
			statement.setString(1, account_name);
			statement.setString(2, type);
			statement.setString(3, facebook_id);
			statement.setInt(4, post_date);
			statement.executeUpdate();
		}
		catch(final Exception e)
		{
			_log.error("", e);
			return false;
		}
		finally
		{
			DbUtils.closeQuietly(con, statement);
		}
		return true;
	}
	
	public void setLikeCount(int id, int like_count)
	{
		mysql.set("UPDATE `facebook` set `like_count` = ?, `like_reward` = ? WHERE `id` = ? LIMIT 1", like_count, 1, id);
	}
	
	public Integer getId(String account_name, String type)
	{
		return getId(account_name, type, 0);
	}
	
	public Integer getId(String account_name, String type, int time)
	{
		int result = 0;

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rset = null;
		try
		{
			con = DatabaseFactory.getInstance().getConnection();
			statement = con.prepareStatement("SELECT id FROM `facebook` WHERE account_name = ? and type = ? and post_date > ?");
			statement.setString(1, account_name);
			statement.setString(2, type);
			statement.setInt(3, time);
			_log.info("query: "+"SELECT id FROM `facebook` WHERE account_name = ? and type = ? and post_date > ?"); 
			rset = statement.executeQuery();
			if(rset.next())
				result = rset.getInt(1);
		}
		catch(Exception e)
		{
			_log.error("facebook.getId(account_name, type): " + e, e);
		}
		finally
		{
			DbUtils.closeQuietly(con, statement, rset);
		}

		return result;
	}
	
	public Integer getIdByPost(String account_name, String type, String facebook_id)
	{
		int result = 0;

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rset = null;
		try
		{
			con = DatabaseFactory.getInstance().getConnection();
			statement = con.prepareStatement("SELECT id FROM `facebook` WHERE account_name = ? and type = ? and facebook_id = ?");
			statement.setString(1, account_name);
			statement.setString(2, type);
			statement.setString(3, facebook_id);
			rset = statement.executeQuery();
			if(rset.next())
				result = rset.getInt(1);
		}
		catch(Exception e)
		{
			_log.error("facebook.getId(account_name, type): " + e, e);
		}
		finally
		{
			DbUtils.closeQuietly(con, statement, rset);
		}

		return result;
	}
	
	public FacebookRecord getRecord(String facebook_id)
	{
		FacebookRecord result = new FacebookRecord();
		
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rset = null;
		try
		{
			con = DatabaseFactory.getInstance().getConnection();
			statement = con.prepareStatement("SELECT *  FROM `facebook` WHERE facebook_id = ? LIMIT 1");
			statement.setString(1, facebook_id);
			rset = statement.executeQuery();
			if(rset.next())
			{
				result.id 			= rset.getInt("id");
				result.account_name = rset.getString("account_name");
				result.type			= rset.getString("type");
				result.facebook_id	= rset.getString("facebook_id");
				result.like_count	= rset.getInt("like_count");
				result.like_reward	= rset.getInt("like_reward");
				result.post_date	= rset.getInt("post_date");
			}
				
		}
		catch(Exception e)
		{
			_log.error("facebook.getId(account_name, type): " + e, e);
		}
		finally
		{
			DbUtils.closeQuietly(con, statement, rset);
		}
		
		return result;
	}
	
	
	
	
	
}
