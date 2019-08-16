package hilos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import clases.Cliente;

public class HiloEscribirUsuario extends Thread{

	private Cliente cliente;
	private DataInputStream in;
	private DataOutputStream out;
	private BufferedReader br;
	private BufferedWriter bw;
	
	
	public HiloEscribirUsuario(Cliente cliente, DataInputStream in, DataOutputStream out, BufferedReader br,
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
				
				
				String envio = br.readLine();
				cliente.setMensaje(envio);
				out.writeUTF(cliente.encriptarMensajeCesar());
				

						
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}

}