package za.co.reference.designpatterns.creationalpatterns.builder;

/*
 * Abstract Builder
 */
public abstract class PizzaBuilder{
	
	protected Pizza pizza;
	
	public Pizza getPizza(){
		return pizza;
	}
	
	public void createNewPizzaProduct(){
		pizza = new Pizza();
	}
	
	public abstract void buildDough();
	
	public abstract void buildSauce();
	
	public abstract void buildTopping();

}
