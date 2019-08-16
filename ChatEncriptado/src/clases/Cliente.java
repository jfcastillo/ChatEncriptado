package clases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Cliente {
	
	/*
	 * 
	 * Direccion local de la maquina
	 */
	public static final String LOCAL_HOST = "localhost";
	/**
	 * Puerto por donde se establecera la conexion
	 */
	public static final int PORT = 8000;
	/**
	 * Socket que permitira la conexion con el servidor
	 */
	private static Socket socket;
	/**
	 * Clave para encriptar el mensaje.
	 */
	private static int claveEncriptar;
	/**
	 * Numero de identificacion del cliente respecto al servidor.
	 */
	private static int id;
	/**
	 * Mensaje a enviar a otro cliente.
	 */
	private static String mensaje;
	
	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		
		DataInputStream in;
		DataOutputStream out;

		try {
			
			BufferedReader br = new BufferedReader(new InputStreamReader( System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			
			System.out.println("::Cliente disponible para ser atendido:: \nIngrese "
					+ "el mensaje para encriptar!!::");
			
			socket = new Socket(LOCAL_HOST, PORT);
			String mensaje = br.readLine();
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(mensaje);
			claveEncriptar = Integer.parseInt(in.readUTF());
			System.out.println(claveEncriptar);
			String mensajeDelServidor = in.readUTF();
			mensajeDelServidor = desEncriptar(mensajeDelServidor);
			bw.write("Su encriptacion fue : " + mensajeDelServidor);
			bw.flush();
			bw.close();
			br.close();
			socket.close();
			in.close();
			out.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	public int getClave() {
		return claveEncriptar;
	}

	public void setClave(int clave) {
		this.claveEncriptar = clave;
	}

	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		Cliente.id = id;
	}
	
	private static String encriptarMensajeCesar() {
		String respuesta = "";
		char caracter ;
			for (int i = 0; i < mensaje.length(); i++) {
				caracter = mensaje.charAt(i);
				caracter = (char) (caracter + claveEncriptar);
				respuesta += Character.toString((caracter)) + "";
			}
		return respuesta;
	}
	
	/**
	 * Método que recibe un mensaje encriptado que envió otro cliente y lo desencripta.
	 * @param mensajeDelServidor
	 * @return mensajeDE
	 */
	private static String desEncriptar(String mensajeDelServidor) {
		String mensajeDE = "";
		char caracter ;
		for (int i = 0; i < mensaje.length(); i++) {
			caracter = mensaje.charAt(i);
			caracter = (char) (caracter - claveEncriptar);
			mensajeDE += Character.toString((caracter)) + "";
		}
		return mensajeDE;
	}
}