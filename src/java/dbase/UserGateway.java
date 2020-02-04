package dbase;

import bean.AdminUserBean;
import bean.ShoppingCart;
import dto.UserDTO;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author gdm1
 */
public class UserGateway extends DB_Manager {

    @Inject
    ShoppingCart shoppingCart;

    public boolean isUsernameInDB(String userName) {
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM USERS WHERE U_NAME = ?");
            stmt.setString(1, userName);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return true;
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public int insertUser(UserDTO user) {
        //need number of user of newly created user
        //to be able to Register and Login at once
        try {
            
            String password1 = user.getPassword();
            byte[] hash = null;
            try {
                hash = MessageDigest.getInstance("SHA-256")
                        .digest(password1.getBytes(StandardCharsets.UTF_8));
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UserGateway.class.getName()).log(Level.SEVERE, null, ex);
            }
            password1 = Base64.getEncoder().encodeToString(hash);

            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO USERS("
                    + "NAME, U_NAME, PASSWORD, ADDRESS "
                    + ") VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getUname());
            stmt.setString(3, password1);
            stmt.setString(4, user.getAddress());
            int numRowsInserted = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();

            int auto_id = rs.getInt(1);
            UserDTO newUser = new UserDTO(auto_id, user.getName(), user.getUname(), user.getPassword(), user.getAddress());
            System.out.println("sdsd" + shoppingCart);
            //shoppingCart.setUser(newUser);

            stmt.close();
            conn.close();

            return auto_id;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public UserDTO checkCredentials(String uname, String password) {
        boolean credentialsOK = false;
        UserDTO user = null;

        try {
            String password1 = password;
            byte[] hash
                    = MessageDigest.getInstance("SHA-256")
                            .digest(password1.getBytes(StandardCharsets.UTF_8));
            password1 = Base64.getEncoder().encodeToString(hash);

            Connection conn = getConnection();

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Users WHERE U_Name = ?");
            stmt.setString(1, uname);
            ResultSet rs = stmt.executeQuery();

            if (rs.next() && rs.getString("password").equals(password1)) {
                credentialsOK = true;
                user = new UserDTO(
                        rs.getInt("USER_ID"),
                        rs.getString("NAME"),
                        rs.getString("U_NAME"),
                        rs.getString("PASSWORD"),
                        rs.getString("ADDRESS"));
                //shoppingCart.setUser(user);

            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            Logger.getLogger(AdminUserBean.class.getName()).log(Level.SEVERE, e.toString());
        }

        if (credentialsOK) {
            return user;

        } else {
            return null;
        }

    }

}
