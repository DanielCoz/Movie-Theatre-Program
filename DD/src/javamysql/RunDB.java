package javamysql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class RunDB extends Application{
    
    /** Creates a new instance of RunDB */
    public RunDB() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	
        launch(args);
    }
    
    @Override
	public void start(Stage primaryStage) throws IOException {
		
    	 try {
    			
    	        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
    	        
    	        //Connection to database
    	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_theatre?useLegacyDatetimeCode=false&serverTimezone=GMT","root","root" );
    	        System.out.println ("Database connection established");
    	          
    	         
    	        try { 
    	    		
    				BorderPane mainPane = new BorderPane();
    				Group root = new Group();
    				Scene scene = new Scene(root,650,500);
    				
    			    TabPane tp = new TabPane();
    			  
    			    //The four tabs are created.
    			    Movies movieTab = new Movies(con);
    			    Theatres theatreTab = new Theatres(con);
    			    Employees employeeTab = new Employees(con);
    			    Screenings screeningsTab = new Screenings(con);
    			    
    			    tp.getTabs().add(screeningsTab);
    			    tp.getTabs().add(movieTab);
    			    tp.getTabs().add(theatreTab);
    			    tp.getTabs().add(employeeTab);
    			   
    			    
    			    mainPane.setCenter(tp);  
    			    mainPane.prefHeightProperty().bind(scene.heightProperty());
    			    mainPane.prefWidthProperty().bind(scene.widthProperty());
    			    
    			    root.getChildren().add(mainPane);
    				primaryStage.setScene(scene);
    				primaryStage.show();
    			
    				}catch(Exception e) {
    					e.printStackTrace();
    			}

    	        }catch (Exception ex) {
    	            System.out.println("SQLException: " + ex.getMessage());
    	            
    	        };
	}
}
