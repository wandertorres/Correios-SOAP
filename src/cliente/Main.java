package cliente;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Scanner;

import javax.xml.rpc.ServiceException;

import org.tempuri.CResultado;
import org.tempuri.CServico;
import org.tempuri.CalcPrecoPrazoWS;
import org.tempuri.CalcPrecoPrazoWSLocator;
import org.tempuri.CalcPrecoPrazoWSSoap;

public class Main {
	
	private static Scanner teclado;

	public static void main(String[] args) throws ServiceException, RemoteException {
		teclado = new Scanner(System.in);
		CalcPrecoPrazoWSSoap interfaceProxy;
		CalcPrecoPrazoWS calcPrecoPrazo = new CalcPrecoPrazoWSLocator();
		interfaceProxy = calcPrecoPrazo.getCalcPrecoPrazoWSSoap();
		
		
		String nCdEmpresa = teclado.nextLine();
		String sDsSenha = teclado.nextLine();
		String nCdServico = teclado.nextLine(); //PAC 41106 | SEDEX 40010
		String sCepOrigem = teclado.nextLine(); //"01310909";
		String sCepDestino = teclado.nextLine(); //"59856000";
		String nVlPeso = teclado.nextLine(); //"5,5";
		int nCdFormato = teclado.nextInt(); //1; //1 caixa/pacote | 2 rolo/prisma | 3 envelope
		BigDecimal nVlComprimento = teclado.nextBigDecimal(); //new BigDecimal(16.0);
		BigDecimal nVlAltura = teclado.nextBigDecimal(); //new BigDecimal(20.0);
		BigDecimal nVlLargura = teclado.nextBigDecimal(); //new BigDecimal(20.0);
		BigDecimal nVlDiametro = teclado.nextBigDecimal(); //new BigDecimal(50.0);
		String sCdMaoPropria = teclado.nextLine(); //"S";
		BigDecimal nVlValorDeclarado = teclado.nextBigDecimal(); //new BigDecimal(0);
		String sCdAvisoRecebimento = teclado.nextLine(); //"S";
		
		CResultado resultado = interfaceProxy.calcPrecoPrazo(nCdEmpresa, sDsSenha, nCdServico, sCepOrigem, sCepDestino, nVlPeso, nCdFormato, nVlComprimento, nVlAltura,nVlLargura, nVlDiametro, sCdMaoPropria, nVlValorDeclarado, sCdAvisoRecebimento);
	
		CServico[] lista = resultado.getServicos();
		String prazo = "";
        String preco = "";
		for (CServico cServico : lista) {
            prazo = cServico.getPrazoEntrega();
            preco = cServico.getValor();
        }
		
		System.out.println("Dias: " +prazo+ "\nValor: " +preco);
	}

}
