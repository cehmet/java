
package lvq;

import java.util.List;

public class Lvq{
	private int sinifSayisi;
	private Neuron[] neuronlar;
	private int agirlikSayisi;
	private double alpha ;
        private double ilkOgrenmeOrani=0.1;
	private double ayrismaOrani = 0.05;
	private double basari;
        
	public Lvq(int sinifSayisi, int agirlikSayisi, String[] hedefCikis,double alfa, double  basari) {
		this.sinifSayisi = sinifSayisi;
		this.alpha=alfa;
                this.basari=basari;
		
		neuronlar = new Neuron[sinifSayisi];
		for(int i=0; i<sinifSayisi; i++) {
			neuronlar[i] = new Neuron(agirlikSayisi, hedefCikis[i]);
		}
	}

    
	
	
	
	
	public void iterasyon(List<List<Double>> giris, List<String> cikis) {
		
		
		
		
		for(int i=0; i<giris.size(); i++) {
			
			List<Double> vektor = giris.get(i);
			Neuron kazanan = kazananVektoruBul(vektor);
                       
			//vektorYazdir(vektor);
			//printVector(winner.getWeights());
			
			//System.out.println(winner.getClassTarget()+" "+(i)+".ci ornek icin    "+target.get(i));
			if(kazanan.getHedefSinif().equals(cikis.get(i))) {
                            
				List<Double> agirliklar = kazanan.getAgirliklar();
				for(int j=0; j<agirliklar.size(); j++) {
					double d = agirliklar.get(j);
					d = d + alpha*(vektor.get(j) - d);
					kazanan.getAgirliklar().set(j, d);
				}
			} else {
				List<Double> agirliklar = kazanan.getAgirliklar();
				for(int j=0; j<agirliklar.size(); j++) {
					double d = agirliklar.get(j);
					d = d - alpha*(vektor.get(j) - d);
					kazanan.getAgirliklar().set(j, d);
				}
			}
			 
			
			alpha = alpha - alpha*ayrismaOrani;
		}
                System.out.println("ornekler");
	}
	
	
	public Neuron kazananVektoruBul(List<Double> vector) {
		Neuron kazanan = null;
		double minUzaklik = Integer.MAX_VALUE;
		
		for(int i=0; i<neuronlar.length; i++) {
			double uzaklik = 0;
			List<Double> agirliklar = neuronlar[i].getAgirliklar();
			
		for(int j=0; j<vector.size(); j++) {
				uzaklik += Math.pow(vector.get(j) - agirliklar.get(j), 2);
			}	
                if(uzaklik < minUzaklik) {
		minUzaklik = uzaklik;
		kazanan = neuronlar[i];
			}
		}
		return kazanan;
	}
	
	public String getkazananSinif(List<Double> vector) {
		Neuron kazanan = kazananVektoruBul(vector);
		return kazanan.getHedefSinif();
	}
	
	public double test_et(List<List<Double>> giris, List<String> cikis) {
		int dogruSayisi = 0;
		for(int i=0; i<giris.size(); i++) {
			List<Double> vector = giris.get(i);
			String sonucSinif = getkazananSinif(vector);
			System.out.println(giris.get(i) +cikis.get(i)+" "+sonucSinif);
			if(sonucSinif.equalsIgnoreCase(cikis.get(i))) {
				dogruSayisi=dogruSayisi+1;
			}
		}
                double oran = (double)dogruSayisi / giris.size();
                System.out.println("test sonucları : "+dogruSayisi+"%"+oran*100);
		          
               
               return oran;
        }
	
	public void agirliklariYazdir() {
             
		for(int i=0; i<neuronlar.length; i++) {
                   
                    
			vektorYazdir(neuronlar[i].getAgirliklar());
		}
	}
	
	private void vektorYazdir(List<Double> vec) {
		for(int i=0; i<vec.size(); i++) {
                   // System.out.println(i+". noronun agirliklari");
		System.out.println(vec.get(i));
		}
		System.out.println();
	}
	
	public void setAlfa(double alfa) {
		ilkOgrenmeOrani = alfa;
	}
	
	public void setAyrisim(double ayrisim) {
		ayrismaOrani = ayrisim;
	}
}



      


