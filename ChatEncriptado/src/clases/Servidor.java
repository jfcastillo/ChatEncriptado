package clases;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import hilos.HiloChat;

public class Servidor {
	
	
	/**
	 * Puerto por donde el servidor atendera a los clientes
	 */
	public static final int PORT = 8000;
	/**
	 * El servidor dispone de un serversocket, para permitir la conexion a los clientes
	 */
	private static  ServerSocket serverSocket;
	/**
	 * El servidor dispone de un socket para atender a cada cliente por individual
	 */
	private static Socket socket;
	
	/**
	 * Mapa que contiene cada cliente conectado al servidor
	 */
	private static HashMap<Integer, Socket> clientes;
	/**
	 * Mapa que contiene el input de cada cliente 
	 */
	private static HashMap<Integer, DataInputStream> inCliente;
	/**
	 * Mapa que contiene el output de cada cliente 
	 */
	private static HashMap<Integer, DataOutputStream> outCliente;
	
	/**
	 * Cantidad de clientes conectados y lista de ids.
	 */
	private static int numClientes;
	
	/**
	 * Constructor del servidor.
	 */
	public Servidor() {
		numClientes = 0;
		clientes = new HashMap<>();
		inCliente = new HashMap<>();
		outCliente = new HashMap<>();
	}
	
	
	
	
	public static void main(String[] args) {
		
		Servidor ser = new Servidor();
		DataInputStream in;
		DataOutputStream out;
		int clave = (int) (Math.random() * 5) + 1;
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HiloChat hiloConexion = new HiloChat(ser);
		hiloConexion.run();
		
//		try {
//			serverSocket = new ServerSocket(PORT);
//			System.out.println("::Servidor escuchando a los posibles clientes::");
//			
//			
//			while(true) {
//				
//				socket = serverSocket.accept();
//				
//				
//				
//				System.out.println(clave);
//				System.out.println("El cliente se ha conectado!");
//				
//				numClientes++;
//				clientes.put(numClientes, socket);
//				
//				
//				in = new DataInputStream(socket.getInputStream());
//				out = new DataOutputStream(socket.getOutputStream());
//				out.writeUTF(clave+"");
//				String mensajeObtenidoCliente = in.readUTF();
//				System.out.println("El mensaje enviado por el cliente fue : " + mensajeObtenidoCliente);
//				String respuestaServer = metodoServicioServer(mensajeObtenidoCliente);
//				out.writeUTF(respuestaServer);
//				socket.close();
//				System.out.println("::El cliente fue desconectado del server::");
//			
//			}			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
			
	}

	/**
	 * Metodo encargado de realizar la encriptacion de Cesar, sumando 2 posiciones al ASCII
	 * de cada caracter
	 * @param mensajeObtenidoCliente != Null && != ""
	 * @return
	 */
	private static String metodoServicioServer(String mensajeObtenidoCliente) {
		
		String respuesta = "";
		char caracter ;
		
		if(mensajeObtenidoCliente != "") {
			for (int i = 0; i < mensajeObtenidoCliente.length(); i++) {
				caracter = mensajeObtenidoCliente.charAt(i);
				caracter = (char) (caracter +3);
				respuesta += Character.toString((caracter)) + "";
			}
		}
		else {
			respuesta = "::El cliente no envio texto para encriptar::";
		}
		return respuesta;
		
	}

	public static ServerSocket getServerSocket() {
		return serverSocket;
	}

	public static void setServerSocket(ServerSocket serverSocket) {
		Servidor.serverSocket = serverSocket;
	}

	public static HashMap<Integer, Socket> getClientes() {
		return clientes;
	}

	public static void setClientes(HashMap<Integer, Socket> clientes) {
		Servidor.clientes = clientes;
	}

	public static int getNumClientes() {
		return numClientes;
	}

	public static void setNumClientes(int numClientes) {
		Servidor.numClientes =+ numClientes;
	}

	public static HashMap<Integer, DataInputStream> getInCliente() {
		return inCliente;
	}

	public static void setInCliente(HashMap<Integer, DataInputStream> inCliente) {
		Servidor.inCliente = inCliente;
	}

	public static HashMap<Integer, DataOutputStream> getOutCliente() {
		return outCliente;
	}

	public static void setOutCliente(HashMap<Integer, DataOutputStream> outCliente) {
		Servidor.outCliente = outCliente;
	}
	
}