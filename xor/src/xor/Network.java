
package xor;

    


public class Network {

 
  protected double globalHata;
  protected int girisSayisi;
  protected int arakatmanSayisi;
  protected int cikisSayisi;
  protected int neuronSayisi;
  protected int agirlikSayisi;
  protected double ogrenmeOrani;
  protected double seviyeCikislari[];
  protected double matris[];
  protected double hata[];
  protected double birikimliDelta[];
  protected double esikDegerler[];
  protected double matrisDelta[];
  protected double birikimliEsikDegerDelta[];
  protected double esikDeltasi[];
  protected double momentum;
  protected double hataDelta[];


  
  public Network(int girisSyisi,
                 int araKatmanSayisi,
                 int cikisSayisi,
                 double ogrenmeOrani,
                 double momentum) {

    this.ogrenmeOrani = ogrenmeOrani;
    this.momentum = momentum;

    this.girisSayisi = girisSyisi;
    this.arakatmanSayisi = araKatmanSayisi;
    this.cikisSayisi = cikisSayisi;
    neuronSayisi = girisSyisi + araKatmanSayisi + cikisSayisi;
    agirlikSayisi = (girisSyisi * araKatmanSayisi) + (araKatmanSayisi * cikisSayisi);

    seviyeCikislari        = new double[neuronSayisi];
    matris      = new double[agirlikSayisi];
    matrisDelta = new double[agirlikSayisi];
    esikDegerler  = new double[neuronSayisi];
    hataDelta  = new double[neuronSayisi];
    hata       = new double[neuronSayisi];
    birikimliEsikDegerDelta = new double[neuronSayisi];
    birikimliDelta = new double[agirlikSayisi];
    esikDeltasi = new double[neuronSayisi];

    reset();
  }

public double getError(int len) {
    double err = Math.sqrt(globalHata / (len * cikisSayisi));
    globalHata = 0;  
    return err;
}

  
  public double sigmoid(double sum) {
    return 1.0 / (1 + Math.exp(-1.0 * sum));
  }

  public double [] cikislariHesapla(double input[]) {
    int i, j;
    final int arakatman = girisSayisi;
    final int cikisİndeksi = girisSayisi + arakatmanSayisi;

    for (i = 0; i < girisSayisi; i++) {
      seviyeCikislari[i] = input[i];
    }

    
    int inx = 0;
    for (i = arakatman; i < cikisİndeksi; i++) {
      double sum = esikDegerler[i];
      for (j = 0; j < girisSayisi; j++) {
        sum += seviyeCikislari[j] * matris[inx++];
      }
      seviyeCikislari[i] = sigmoid(sum);
    }
    double sonuc[] = new double[cikisSayisi];
    for (i = cikisİndeksi; i < neuronSayisi; i++) {
      double sum = esikDegerler[i];
        for (j = arakatman; j < cikisİndeksi; j++) {
        sum += seviyeCikislari[j] * matris[inx++];
      }
      seviyeCikislari[i] = sigmoid(sum);
      sonuc[i-cikisİndeksi] = seviyeCikislari[i];
    }

    return sonuc;
  }


  
  public void hataHesapla(double ideal[]) {
    int i, j;
    final int arakatmanİndeksi = girisSayisi;
    final int cikisİndeksi = girisSayisi + arakatmanSayisi;

    
    for (i = girisSayisi; i < neuronSayisi; i++) {
      hata[i] = 0;
    }
    for (i = cikisİndeksi; i < neuronSayisi; i++) {
      hata[i] = ideal[i - cikisİndeksi] - seviyeCikislari[i];
      globalHata += hata[i] * hata[i];
      hataDelta[i] = hata[i] * seviyeCikislari[i] * (1 - seviyeCikislari[i]);
    }
    int winx = girisSayisi * arakatmanSayisi;
    for (i = cikisİndeksi; i < neuronSayisi; i++) {
      for (j = arakatmanİndeksi; j < cikisİndeksi; j++) {
        birikimliDelta[winx] += hataDelta[i] * seviyeCikislari[j];
        hata[j] += matris[winx] * hataDelta[i];
        winx++;
      }
      birikimliEsikDegerDelta[i] += hataDelta[i];
    }

   
    for (i = arakatmanİndeksi; i < cikisİndeksi; i++) {
      hataDelta[i] = hata[i] * seviyeCikislari[i] * (1 - seviyeCikislari[i]);
    }

  
    winx = 0; 
    for (i = arakatmanİndeksi; i < cikisİndeksi; i++) {
      for (j = 0; j < arakatmanİndeksi; j++) {
        birikimliDelta[winx] += hataDelta[i] * seviyeCikislari[j];
        hata[j] += matris[winx] * hataDelta[i];
        winx++;
      }
      birikimliEsikDegerDelta[i] += hataDelta[i];
    }
  }

  
  public void ogren() {
    int i;
    for (i = 0; i < matris.length; i++) {
      matrisDelta[i] = (ogrenmeOrani * birikimliDelta[i]) + (momentum * matrisDelta[i]);
      matris[i] += matrisDelta[i];
      birikimliDelta[i] = 0;
    }

    
    for (i = girisSayisi; i < neuronSayisi; i++) {
      esikDeltasi[i] = ogrenmeOrani * birikimliEsikDegerDelta[i] + (momentum * esikDeltasi[i]);
      esikDegerler[i] += esikDeltasi[i];
      birikimliEsikDegerDelta[i] = 0;
    }
  }

 
  public void reset() {
    int i;

    for (i = 0; i < neuronSayisi; i++) {
      esikDegerler[i] = 0.5 - (Math.random());
      esikDeltasi[i] = 0;
      birikimliEsikDegerDelta[i] = 0;
    }
    for (i = 0; i < matris.length; i++) {
      matris[i] = 0.5 - (Math.random());
      matrisDelta[i] = 0;
      birikimliDelta[i] = 0;
    }
  }
}

