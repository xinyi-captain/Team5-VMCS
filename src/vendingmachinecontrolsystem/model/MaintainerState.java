package vendingmachinecontrolsystem.model;

import java.util.Observable;

public class MaintainerState extends Observable{
	private boolean isLogIn;

	private  static  MaintainerState maintainerState;
	
	private MaintainerState(){
		isLogIn=true;
	}
	
	public static MaintainerState getInstance() {
		if (maintainerState == null) {
			synchronized (DoorState.class) {
				if (maintainerState == null) {
					maintainerState = new MaintainerState();
				}
			}
		}
		return maintainerState;
	}
	
	public boolean isLocked() {
		return isLogIn;
	}

	public void setLogIn(boolean isLogIn) {
		this.isLogIn = isLogIn;
        setChanged();
        notifyObservers();
	}
	
}
