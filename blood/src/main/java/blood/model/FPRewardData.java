package blood.model;

public class FPRewardData {
	
	public int _item_id = 0;
	public int _item_count = 0;
	
	public FPRewardData(int item_id, int item_count){
		_item_id = item_id;
		_item_count = item_count;
	}
	
	public int getItemId(){
		return _item_id;
	}
	
	public int getItemCount(){
		return _item_count;
	}
}
