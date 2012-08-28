
package lvq;

import java.util.ArrayList;
import java.util.List;

public class Neuron {
	private List<Double> agirliklar;
	private String hedefSİnif;
	private int agirlikSayisi;
	
	public Neuron(int agirlikSayisi, String hedefcikis) {
		this.agirlikSayisi = agirlikSayisi;
		hedefSİnif = hedefcikis;
		
		agirliklar = new ArrayList<Double>();
		for(int i=0; i<agirlikSayisi; i++) {
			agirliklar.add(Math.random());
		}
	}
	
	public List<Double> getAgirliklar() {
		return agirliklar;
	}
	
	public String getHedefSinif() {
		return hedefSİnif;
	}
	
	public void agirlikyaz() {
		for(int i=0; i<agirlikSayisi; i++) {
			System.out.print(agirliklar.get(i)+" ");
		}
		System.out.println();
	}
}
