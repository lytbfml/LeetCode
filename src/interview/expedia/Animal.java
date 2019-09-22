package interview.expedia;

/**
 * @author Yangxiao on 11/1/2018.
 */

abstract class Animal {
	protected boolean isMammal;
	protected boolean isCarnivorous;
	
	public Animal(boolean isMammal, boolean isCarnivorous) {
		this.isMammal = isMammal;
		this.isCarnivorous = isCarnivorous;
	}
	
	public boolean getIsMammal() {
		return this.isMammal;
	}
	
	public boolean getIsCarnivorous() {
		return this.isCarnivorous;
	}
	
	abstract public String getGreeting();
	
	public void printAnimal(String name) {
		System.out.println(
				"A " + name + " says '" + this.getGreeting()
						+ "', is " + (this.getIsCarnivorous() ? "" : "not ")
						+ "carnivorous, and is " + (this.getIsMammal() ? "" : "not ") + "a mammal."
		                  );
	}
}

class Dog extends Animal {
	
	
	Dog(boolean isMammal, boolean isCarnivorous) {
		super(isMammal, isCarnivorous);
	}
	
	@Override
	public String getGreeting() {
		return null;
	}
}