package veiculosd;

import java.rmi.registry.Registry;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.List;
import java.util.ArrayList;

public class VeiculoClient {
	
	private List<Veiculo> list;
	
	public VeiculoClient( ) {
		list = new ArrayList<Veiculo>( );
	}
	
	private void run( ) {
		Veiculo veiculo = new Veiculo( );
		veiculo.setNomeCliente("Fulano");
		veiculo.setMarcaVeiculo("Honda");
		veiculo.setValorVenda(56789.12);
		veiculo.setAno(2021);
		try {
			Registry registry = LocateRegistry.getRegistry();
			VeiculoInterface server = (VeiculoInterface) registry.lookup("VeiculoInterface");
			server.add(veiculo);
			list = server.search2Ano(2021);
			System.out.println(list.get(0));
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main( String[ ] args ) {
		VeiculoClient obj = new VeiculoClient( );
		obj.run( );
	}

}
