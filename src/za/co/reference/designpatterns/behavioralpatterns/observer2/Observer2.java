package za.co.reference.designpatterns.behavioralpatterns.observer2;

public class Observer2 implements IObserver{
	
	@Override
	public void update(String state){
		setState(state);
		System.out.println("Observer2 updated with state " + state);
	}
	
	public String getState(){
		return state;
	}

	public void setState(String state){
		this.state = state;
	}

	private String state;

}
