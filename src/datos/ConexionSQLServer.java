package datos;

import com.Parametros;
import com.excepciones.ExcepcionConexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que permite ejecutar consultas a base de datos SQL Server
 * @author Jorge Silva Borda
 */
public class ConexionSQLServer {
    private final String servidor, baseDatos, usuario, password;
    private int puerto = -1;
    private Connection conn;

    /**
     * Constructor
     * @param servidor {@code String} - El nombre o IP del servidor.
     * @param baseDatos {@code String} - El nombre de la base de datos.
     * @param usuario {@code String} - El nombre de usuario.
     * @param password {@code Srting} - El password del usuario.
     */
    public ConexionSQLServer(String servidor, String baseDatos, String usuario, String password) {
	this.servidor = servidor;
	this.baseDatos = baseDatos;
	this.usuario = usuario;
	this.password = password;
    }

    /**
     * 
     * Constructor
     * @param servidor {@code String} - El nombre o IP del servidor.
     * @param puerto {@code int} - El puerto de la conexión SQL.
     * @param baseDatos {@code String} - El nombre de la base de datos.
     * @param usuario {@code String} - El nombre de usuario.
     * @param password {@code Srting} - El password del usuario.
     */
    public ConexionSQLServer(String servidor, int puerto, String baseDatos, String usuario, String password) {
	this.servidor = servidor;
	this.puerto = puerto;
	this.baseDatos = baseDatos;
	this.usuario = usuario;
	this.password = password;
    }
    
    /**
     * Obtiene la conexión {@link java.sql.Connection} configurada para poder ejecutar las operaciones.
     * @return {@code Connection}. {@code null} En caso de que no se pueda instanciar la conexión.
     * @throws com.excepciones.ExcepcionConexion
     */
    public Connection getConexion() throws ExcepcionConexion{
	if(this.servidor == null){
	    throw new ExcepcionConexion("Excepción al obtener la conexión." + Parametros.SEP + "El parámetro [servidor] no puede ser null.");
	}
	if(this.baseDatos == null){
	    throw new ExcepcionConexion("Excepción al obtener la conexión." + Parametros.SEP + "El parámetro [baseDatos] no puede ser null.");
	}
	if(this.usuario == null){
	    throw new ExcepcionConexion("Excepción al obtener la conexión." + Parametros.SEP + "El parámetro [usuario] no puede ser null.");
	}
	if(this.password == null){
	    throw new ExcepcionConexion("Excepción al obtener la conexión." + Parametros.SEP + "El parámetro [password] no puede ser null.");
	}
	
	try{
	    String str = "jdbc:sqlserver://" + this.servidor + (this.puerto == -1 ? "" : ":" + Integer.toString(this.puerto)) + ";databaseName=" + this.baseDatos + ";user=" + this.usuario + ";password=" + this.password;
	    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    this.conn = DriverManager.getConnection(str);
	    return this.conn;
	}catch (ClassNotFoundException | SQLException ex) {
	    System.out.println("No se puede establecer la conexion a la base de datos.");
	    System.out.println(ex);
	    return null;
	}
    }
    
}