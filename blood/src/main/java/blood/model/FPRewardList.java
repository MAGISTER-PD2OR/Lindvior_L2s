package blood.model;

import java.util.ArrayList;
import java.util.List;

public class FPRewardList 
{
	public List<Integer> _class_ids = new ArrayList<Integer>();
	
	public boolean _is_mage = false;
	
	public List<FPRewardData> _rewards = new ArrayList<FPRewardData>();
	
	public FPRewardList(boolean is_mage)
	{
		_is_mage = is_mage;
	}
	
	public void addClass(int class_id){
		_class_ids.add(class_id);
	}
	
	public boolean hasClass(int class_id){
		return _class_ids.contains(class_id);
	}
	
	public void addItem(FPRewardData item){
		_rewards.add(item);
	}
	
	public List<FPRewardData> getReward(){
		return _rewards;
	}
	
	public boolean is_mage(){
		return _is_mage;
	}

}
