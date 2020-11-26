package vendingmachinecontrolsystem.model;

import vendingmachinecontrolsystem.controller.MachineryController;

import java.util.Observable;

public class DoorState extends Observable {

	private boolean isLocked;

	private  static  DoorState doorState;
	private DoorState(){
		isLocked=true;
	}
	public static DoorState getInstance() {
		if (doorState == null) {
			synchronized (DoorState.class) {
				if (doorState == null) {
					doorState = new DoorState();
				}
			}
		}
		return doorState;
	}
	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
        setChanged();
        notifyObservers();
	}
	
	
}
