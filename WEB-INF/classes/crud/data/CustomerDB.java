











package crud.data;

import java.sql.*;
import java.util.ArrayList;

import crud.business.Customer;

public class CustomerDB
{
    //insert method
    public static int insert(Customer customer)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        // asfasfas
        String query
        = "INSERT INTO customer (cus_fname, cus_lname, cus_street, cus_city, cus_state, cus_zip, cus_phone, cus_email, cus_balance, cus_total_sales, cus_notes)"
        + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    try
    {
        ps = connection.prepareStatement(query);
        ps.setString(1, customer.getFname());
        ps.setString(2, customer.getLname());
        ps.setString(3, customer.getStreet());
        ps.setString(4, customer.getCity());
        ps.setString(5, customer.getState());
        ps.setString(6, customer.getZip());
        ps.setString(7, customer.getPhone());
        ps.setString(8, customer.getEmail());
        ps.setString(9, customer.getBalance());
        ps.setString(10, customer.getTotalSales());
        ps.setString(11, customer.getNotes());

        return ps.executeUpdate();
    }
    catch (SQLException e)
    {
        System.out.println(e);
        return 0;
    }
    finally
    {
    DBUtil.closePreparedStatement(ps);
    pool.freeConnection(connection);
    }
}

//update method
public static int update(Customer customer)
{
    ConnectionPool pool = ConnectionPool.getInstance();
    Connection connection = pool.getConnection();
    PreparedStatement ps = null;
    // asfasfas
    String query = "UPDATE customer SET "
        + "cus_fname = ?, "
        + "cus_lname = ?, "
        + "cus_street = ?, "
        + "cus_city = ?, "
        + "cus_state = ?, "
        + "cus_zip = ?, "
        + "cus_phone = ?, "
        + "cus_email = ?, "
        + "cus_balance = ?, "
        + "cus_total_sales = ?, "
        + "cus_notes = ? "
        + "WHERE cus_id = ?";
    try
    {
        ps = connection.prepareStatement(query);
        ps.setString(1, customer.getFname());
        ps.setString(2, customer.getLname());
        ps.setString(3, customer.getStreet());
        ps.setString(4, customer.getCity());
        ps.setString(5, customer.getState());
        ps.setString(6, customer.getZip());
        ps.setString(7, customer.getPhone());
        ps.setString(8, customer.getEmail());
        ps.setString(9, customer.getBalance());
        ps.setString(10, customer.getTotalSales());
        ps.setString(11, customer.getNotes());
        ps.setString(12, customer.getId());

        return ps.executeUpdate();
    }
    catch (SQLException e)
    {
        System.out.println(e);
        return 0;
    }
    finally
    {
        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);
    }
    }

//delete method
public static int delete(Customer customer)
{
    ConnectionPool pool = ConnectionPool.getInstance();
    Connection connection = pool.getConnection();
    PreparedStatement ps = null;

    String query = "DELETE FROM customer WHERE cus_id = ?";
    try
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, customer.getId());

            return ps.executeUpdate();
        }
        catch (SQLException e)
    {
        System.out.println(e);
        return 0;
    }
    finally
    {
        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);
    }
    }

    public static Customer selectCustomer(String id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM customer WHERE cus_id = ?";
        try
            {
                ps = connection.prepareStatement(query);
                ps.setString(1, id);
                rs = ps.executeQuery();
                Customer customer = null;
                if (rs.next())
                {
                    customer = new Customer();
                    customer.setId(id);
                    customer.setFname(rs.getString("cus_fname"));
                    customer.setLname(rs.getString("cus_lname"));
                    customer.setStreet(rs.getString("cus_street"));
                    customer.setCity(rs.getString("cus_city"));
                    customer.setState(rs.getString("cus_state"));
                    customer.setZip(rs.getString("cus_zip"));
                    customer.setPhone(rs.getString("cus_phone"));
                    customer.setEmail(rs.getString("cus_email"));
                    customer.setBalance(rs.getString("cus_balance"));
                    customer.setTotalSales(rs.getString("cus_total_sales"));
                    customer.setNotes(rs.getString("cus_notes"));
                }
            return customer;
        }
        catch (SQLException e)
    {
        System.out.println(e);
        return null;
    }
    finally
    {
        DBUtil.closeResultSet(rs);
        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);
    }
    }

    /*
    retrieve all customer methods (using ArrayList)
    NOTE:
    sadf
    sdf
    sdf
    */
public static ArrayList<Customer> selectCustomers()
{
    ConnectionPool pool = ConnectionPool.getInstance();
    Connection connection = pool.getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;

    String query = "SELECT * FROM customer";
    try
    {
        ps = connection.prepareStatement(query);
        rs = ps.executeQuery();
        ArrayList<Customer> customers = new ArrayList<Customer>();
        while (rs.next())
        {
            Customer customer = new Customer();
            customer.setId(rs.getString("cus_id"));
            customer.setFname(rs.getString("cus_fname"));
            customer.setLname(rs.getString("cus_lname"));
            customer.setStreet(rs.getString("cus_street"));
            customer.setCity(rs.getString("cus_city"));
            customer.setState(rs.getString("cus_state"));
            customer.setZip(rs.getString("cus_zip"));
            customer.setPhone(rs.getString("cus_phone"));
            customer.setEmail(rs.getString("cus_email"));
            customer.setBalance(rs.getString("cus_balance"));
            customer.setTotalSales(rs.getString("cus_total_sales"));
            customer.setNotes(rs.getString("cus_notes"));
            customers.add(customer);
        }
        return customers;
    }
        catch (SQLException e)
    {
        System.out.println(e);
        return null;
    }
    finally
    {
        DBUtil.closeResultSet(rs);
        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);
    }
}

//check if email exist method
public static boolean emailExists(String email)
{
    ConnectionPool pool = ConnectionPool.getInstance();
    Connection connection = pool.getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;

    String query = "SELECT cus_email FROM customer WHERE cus_email = ?";
    try
    {
        ps = connection.prepareStatement(query);
        ps.setString(1,email);
        rs = ps.executeQuery();
        return rs.next();
    }
        catch (SQLException e)
    {
        System.out.println(e);
        return false;
    }
    finally
    {
        DBUtil.closeResultSet(rs);
        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);
    }
}
}
