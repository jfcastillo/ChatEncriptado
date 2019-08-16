package hilos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import clases.Cliente;

public class HiloLeerUsuario extends Thread {
	
	private Cliente cliente;
	private DataInputStream in;
	private DataOutputStream out;
	private BufferedReader br;
	private BufferedWriter bw;
	
	
	public HiloLeerUsuario(Cliente cliente, DataInputStream in, DataOutputStream out, BufferedReader br,
			BufferedWriter bw) {
		super();
		this.cliente = cliente;
		this.in = in;
		this.out = out;
		this.br = br;
		this.bw = bw;
	}
	@Override
	public void run() {
		while (true) {
			
			try {
				String mensaje = in.readUTF();
				
				if (mensaje.contains("Clave")) {
					
					cliente.setClaveEncriptada(mensaje.split(" ")[1]);
				}
				else if (mensaje.contains("Usted es el cliente")) {
					bw.write(mensaje);
				}
				else {
					cliente.setMensaje(mensaje);
					bw.write(cliente.desEncriptar(mensaje));
				}
				
				
				
				bw.flush();
//				String envio = br.readLine();
//				out.writeUTF(envio);
				

						
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}

}
