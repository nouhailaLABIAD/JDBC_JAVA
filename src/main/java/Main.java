import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Main {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/tp_jdbc";
        String username = "root";
        String mdp = "";
        Connection con = null;

        try {
            con = DriverManager.getConnection(url, username, mdp);
            System.out.println("Connexion reussie");
            //ajouterPersonne(con, "oumayma", "labiad", 22);
            
            //updatePerson(con);
           // DeleteEtud(con,5);
            getPerson(con);
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connexion échouée");
        }  
    }
	private static void ajouterPersonne(Connection con, String prenom, String nom, int age) {
        String sql = "INSERT INTO etudiant (nom,prenom, age) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, prenom);
            preparedStatement.setString(2, nom);
            preparedStatement.setInt(3, age);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Ligne ajoutee avec succes !");
            } else {
                System.out.println("Échec de l'ajout de la ligne.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void getPerson(Connection con){
        String sql = "SELECT * FROM etudiant";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                // Exécuter la requête
                ResultSet resultSet = preparedStatement.executeQuery();

                // Parcourir les résultats
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nom = resultSet.getString("nom");
                    String prenom = resultSet.getString("prenom");
                    int age = resultSet.getInt("age");

    
                    System.out.println("ID : " + id + ", Nom : " + nom + ", Prenom : " + prenom + ", Age : " + age);
                }
            }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void updatePerson(Connection con){
        String sql="UPDATE etudiant SET nom='nouhaila' WHERE nom='oumayma'";
        try(PreparedStatement PS=con.prepareStatement(sql)){
            //PS.setString(1, 'oumayma');
            int rowsAffected = PS.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Ligne modifiee avec succes !");
            } else {
                System.out.println("Échec de la modification de la ligne.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        
    }
    private static void DeleteEtud(Connection con,int id) {
    	String sql="DELETE FROM etudiant WHERE id=?";
    	try(PreparedStatement stm=con.prepareStatement(sql)){
    	stm.setInt(1,id );
    	int rowsAffected=stm.executeUpdate();
    	if(rowsAffected>0) {
    		System.out.println("ligne supprime");
    	}
    	else {
    		System.out.println("ligne nom supprime");
    	}
    }catch(SQLException e) {
    	e.printStackTrace();
    }

	}
}
