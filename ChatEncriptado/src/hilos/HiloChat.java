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
		try {
			
			System.out.println("::Servidor escuchando a los posibles clientes::");
			
			Socket cliente = server.getServerSocket().accept();
			server.setNumClientes(1);
			server.getClientes().put(server.getNumClientes(), cliente);
			in = new DataInputStream(cliente.getInputStream());
			out = new DataOutputStream(cliente.getOutputStream());
			server.getInCliente().put(server.getNumClientes(), in);
			server.getOutCliente().put(server.getNumClientes(), out);
			if (server.getNumClientes() == 2) {
				HiloComunicacion hiloComunicacion = new HiloComunicacion(server);
				hiloComunicacion.run();
				
			}
			Thread.sleep(1);
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
		
		
		
	}

}
