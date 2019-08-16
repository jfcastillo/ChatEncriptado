package hilos;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import clases.Servidor;

public class HiloComunicacion implements Runnable {

	private Servidor server;
	
	private int id;
	
	private Socket cliente;
	
	public HiloComunicacion(Servidor server) {
		this.server = server;
		id = 2;
	}
	
	@Override
	public void run() {
		while(true) {
			
			try {
				cliente = server.getClientes().get(id);
				DataInputStream in = server.getInCliente().get(id);
				String mensaje = in.readUTF();
				if(id==1) {
					
					server.getOutCliente().get(2).writeUTF(mensaje);
					id = 2;
					
				} else if(id==2) {
					
					server.getOutCliente().get(1).writeUTF(mensaje);
					id = 1;
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}

}
