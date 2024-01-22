package Day24.Day24Workshop.errors;

public class OrderError extends Exception{
    
    public OrderError(){
        super();
    }

    public OrderError(String msg){
        super(msg);
    }
}
