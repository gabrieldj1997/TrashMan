package Player;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.Timer;


public class Tiros {
    private int x, y;
    private final Image imagem;
    private boolean isVisible;
    private static final int velocidade = 8;
    private final int altura = 20;
    private final int largura = 20;
    private final int dir;
    private final Timer timer;
    private ImageIcon referencia;
    
    
    public Tiros(int x, int y, int dir){
        this.x = x;
        this.y = y;
        if(dir == 0){
            referencia = new ImageIcon("src/res/tiroCima.png");
        }else if(dir == 1){
            referencia = new ImageIcon("src/res/tiroDireita.png");
        }else if(dir == 2){
            referencia = new ImageIcon("src/res/tiroBaixo.png");
        }else if(dir == 3){
            referencia = new ImageIcon("src/res/tiroEsquerda.png");
        }
        imagem = referencia.getImage();
        isVisible = true;
        this.dir = dir;
        timer = new Timer(40, new Movimentacao());
        timer.start();
    }
    
    public void mover(){
        switch(this.dir){
            case 0:
                y -= velocidade;
                break;
            case 1: 
                x += velocidade;
                break;
            case 2:
                y += velocidade;
                break;
            case 3:
                x -= velocidade;
        }
    }
    
    public Image getImagem(){
        return imagem;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public int getLar(){
        return largura;
    }
    
    public int getAlt(){
        return altura;
    }
    
    public boolean isVisible(){
        return isVisible;
    }
    
    public void setVisible(boolean visivel){
        this.isVisible = visivel;
    }
    
    private class Movimentacao implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
            mover();
        }
    }
}
