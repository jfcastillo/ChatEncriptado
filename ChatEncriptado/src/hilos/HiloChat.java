package hilos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import clases.Servidor;

public class HiloChat implements Runnable {

	private Servidor server;

	public HiloChat(Servidor server) {
		this.server = server;
	}

	@Override
	public void run() {
		
		DataInputStream in;
		DataOutputStream out;
		System.out.println("::Servidor escuchando a los posibles clientes::");
		while(true) {
			
			try {		

				Socket cliente = server.getServerSocket().accept();
				server.aumentarClientes();
				server.getClientes().put(server.getNumClientes(), cliente);
				in = new DataInputStream(cliente.getInputStream());
				out = new DataOutputStream(cliente.getOutputStream());
				
				System.out.println("Conectado el cliente "+server.getNumClientes());
				
				out.writeUTF("Usted es el cliente "+server.getNumClientes()+"\n");
				out.writeUTF("Clave "+server.claveHexadecimal(server.getClave()+"")+"\n");
				
				server.getInCliente().put(server.getNumClientes(), in);
				server.getOutCliente().put(server.getNumClientes(), out);
				if (server.getNumClientes() == 2) {
					System.out.println("Dirijase a la consola 3 (Cliente 2) para enviar el primer mensaje");
					HiloComunicacion hiloComunicacion = new HiloComunicacion(server);
					hiloComunicacion.run();

				}
				

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		}
		
		

	}

}
