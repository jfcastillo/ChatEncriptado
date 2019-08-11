package clases;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
	
	private static int clave;

	
	
	public static void main(String[] args) {
		
		DataInputStream in;
		DataOutputStream out;
		
		try {
			serverSocket = new ServerSocket(PORT);
			System.out.println("::Servidor escuchando a los posibles clientes::");
			
			while(true) {
				
				socket = serverSocket.accept();
				
				clave = (int) (Math.random() * 5) + 1;
				System.out.println(clave);
				System.out.println("El cliente se ha conectado!");
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
				out.writeUTF(clave+"");
				String mensajeObtenidoCliente = in.readUTF();
				System.out.println("El mensaje enviado por el cliente fue : " + mensajeObtenidoCliente);
				String respuestaServer = metodoServicioServer(mensajeObtenidoCliente);
				out.writeUTF(respuestaServer);
				socket.close();
				System.out.println("::El cliente fue desconectado del server::");
			
			}			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
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
	
	
	
	

}