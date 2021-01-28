package Player;

import java.awt.Image;
import javax.swing.ImageIcon;


public class Lixo {
    private double x,y;
    private Image imagem;
    private boolean isVisible;
    private static int largura = 25;
    private static int altura = 35;
    private ImageIcon referencia;
    private int tipoLixo = 0;
    private static int contador = 0;
    
    public Lixo(int x, int y,int cont){
        this.x = x;
        this.y = y;
        if(contador++ % 3 == 0){
            referencia = new ImageIcon("src/res/lixoVermelho.png");
            tipoLixo = 0;
        }else if(contador++ % 4 == 0){
            referencia = new ImageIcon("src/res/lixoAzul.png");
            tipoLixo = 1;   
        }else if(contador++ % 5 == 0){
            referencia = new ImageIcon("src/res/lixoVerde.png");
            tipoLixo = 2;
        }else{
            referencia = new ImageIcon("src/res/lixoAmarelo.png");
            tipoLixo = 3;
        }
        referencia.setImage(referencia.getImage().getScaledInstance(25, 35, 100));
        imagem = referencia.getImage();
        isVisible = true;
    }
    
    public void mover(double velocidade){
        this.y += velocidade;
    }
    
    public int getAlt(){
        return altura;
    }
    
    public int getLar(){
        return largura;
    }
    
    public Image getImagem(){
        return imagem;
    }
    
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
    
    public void setY(double y){
        this.y = y;
    }
    
    public boolean isVisible(){
        return isVisible;
    }
    
    public void setVisivel(boolean visivel){
        this.isVisible = visivel;
    }

    public int getTipoLixo() {
        return tipoLixo;
    }

    public void setTipoLixo(int aTipoLixo) {
        tipoLixo = aTipoLixo;
    }
}
