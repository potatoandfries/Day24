package Day24.Day24Workshop.repo;

public class Queries {
    
    public static final String SQL_INSERT_ORDERS="""
        INSERT INTO orders(customer_name, ship_address, notes)
        VALUES (?,?,?);

    """;
    public static final String SQL_INSERT_ORDER_DETAILS="""
        INSERT INTO order_details(order_id, product, quantity)    
        VALUES 
            (?,?,?)
    """;
    
}
