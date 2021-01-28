package gameApp;

import Player.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JFrame {

    private Timer timer;
    private Fase fase;
    private Image fundo;
    private Graphics2D grafico;
    private Lixeiro gari;
    private List<Lixo> lixo;
    private Timer novoLixo;
    private double sx1, sx2, sy1, sy2;
    private double dx1, dx2, dy1, dy2;
    private Lixo lixoTemp;
    private Image telaPrincipal;
    private Image telaControle;
    private Image telaGameover;
    private boolean gameOver;
    private int vidas;
    private int vidasExtras;
    private long pontuacao;
    private int consecutivo = 0;
    private int multiplicador = 1;
    private int pixel = 640;
    private int velocidade;
    private boolean inicio;
    private boolean jogando;
    private boolean controles;
    private boolean pausar;
    private URL som;
    private AudioClip musicaMenu;
    private AudioClip musicaJogo;

    protected Game() {
        componentes();
        inicializar();
    }

    public static void main(String[] args) {
        new Game();
    }

    public void componentes() {
        setTitle("TRASH MAN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(480, 640);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private class NovoLixo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            lixo.add(new Lixo((int) (Math.random()*400+1), 0, 0));
        }
    }

    public void controle() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {

            }

            @Override
            public void keyPressed(KeyEvent ke) {
                int codigo = ke.getKeyCode();
                if (codigo == KeyEvent.VK_LEFT
                        || codigo == KeyEvent.VK_A) {
                    gari.setPos(0);
                    gari.setDx(-2);
                }
                if (codigo == KeyEvent.VK_RIGHT
                        || codigo == KeyEvent.VK_D) {
                    gari.setPos(2);
                    gari.setDx(2);
                }
                if (codigo == KeyEvent.VK_UP
                        || codigo == KeyEvent.VK_W) {
                    gari.setPos(1);
                    switch (velocidade) {
                        case 3:
                            gari.setDy(-0.2);
                            break;
                        case 2:
                            gari.setDy(-0.5);
                            break;
                        default:
                            gari.setDy(-velocidade);
                            break;
                    }
                }
                if (codigo == KeyEvent.VK_DOWN
                        || codigo == KeyEvent.VK_S) {
                    gari.setPos(1);
                    gari.setDy(velocidade);
                }
                if(codigo == KeyEvent.VK_ENTER){
                    if(inicio){
                        inicio = false;
                        jogando = true;
                        gameOver = false;
                        novoLixo.start();
                        gari.setX(285);
                        gari.setY(500);
                        vidas = 10;
                        musicaMenu.stop();
                        musicaJogo.loop();
                    }else if(gameOver){
                        gameOver = false;
                        inicio = true;
                        lixo.clear();
                        JOptionPane.showMessageDialog(fase, "CREDITOS\n"
                                + "Gabriel\n"
                                + "Yago\n"
                                + "Tiago\n"
                                + "Iskren\n"
                                + "Thiago");
                        musicaJogo.stop();
                        musicaMenu.loop();
                    }
                }
                if (codigo == KeyEvent.VK_Z 
                        || codigo == KeyEvent.VK_H) {
                    gari.setLixeira(0);
                }
                if (codigo == KeyEvent.VK_X
                        || codigo == KeyEvent.VK_J) {
                    gari.setLixeira(1);
                }
                if (codigo == KeyEvent.VK_C
                        || codigo == KeyEvent.VK_K) {
                    gari.setLixeira(2);
                }
                if (codigo == KeyEvent.VK_V
                        || codigo == KeyEvent.VK_L) {
                    gari.setLixeira(3);
                }
                if (codigo == KeyEvent.VK_SPACE) {
                    if(!controles){
                        controles = true;
                        inicio = false;
                    }else{
                        inicio = true;
                        controles = false;
                    }
                }
                if (codigo == KeyEvent.VK_P) {
                    if(jogando){
                        if(pausar){
                            pausar = false;
                            timer.start();
                        }else{
                            pausar = true;
                            timer.stop();
                        }
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                int codigo = ke.getKeyCode();
                if (codigo == KeyEvent.VK_LEFT
                        || codigo == KeyEvent.VK_A) {
                    gari.setPos(1);
                    gari.setDx(0);
                }
                if (codigo == KeyEvent.VK_RIGHT
                        || codigo == KeyEvent.VK_D) {
                    gari.setPos(1);
                    gari.setDx(0);
                }
                if (codigo == KeyEvent.VK_UP
                        || codigo == KeyEvent.VK_W) {
                    gari.setPos(1);
                    gari.setDy(0);
                }
                if (codigo == KeyEvent.VK_DOWN
                        || codigo == KeyEvent.VK_S) {
                    gari.setPos(1);
                    gari.setDy(0);
                }
                if (codigo == KeyEvent.VK_SPACE) {
                }
                if (codigo == KeyEvent.VK_ENTER) {

                }
            }

        });
    }

    public void inicializar() {
        fase = new Fase();
        add(fase);
        ImageIcon referencia = new ImageIcon("src/res/wallpaper.png");
        fundo = referencia.getImage();
        referencia = new ImageIcon("src/res/TelaGameOver.png");
        telaGameover = referencia.getImage();
        referencia = new ImageIcon("src/res/wallpaper2.png");
        telaPrincipal = referencia.getImage();
        referencia = new ImageIcon("src/res/controles.png");
        telaControle = referencia.getImage();
        som = Game.class.getResource("topGear.wav");
        musicaMenu= Applet.newAudioClip(som);
        som = getClass().getResource("megaMan.wav");
        musicaJogo = Applet.newAudioClip(som);
        vidasExtras = 0;
        velocidade = 1;
        gameOver = false;
        pontuacao = 0;
        gari = new Lixeiro();
        controle();
        controles = false;
        inicio = true;
        pausar = false;
        jogando = false;
        lixo = new ArrayList<>();
        novoLixo = new Timer(1000, new NovoLixo());
        timer = new Timer(5, new Listener());
        timer.start();
        musicaMenu.loop();
    }

    private void colisaoLixo() {
        sx1 = gari.getX();
        sx2 = gari.getX() + gari.getLar();
        sy1 = gari.getY();
        sy2 = gari.getY() + gari.getAlt();

        for (int i = 0; i < lixo.size(); i++) {
            lixoTemp = lixo.get(i);
            dx1 = (int) lixoTemp.getX();
            dx2 = (int) lixoTemp.getX() + lixoTemp.getLar();
            dy1 = (int) lixoTemp.getY();
            dy2 = (int) lixoTemp.getY() + lixoTemp.getAlt();
            if (sx1 < dx2 && sx2 > dx1 && 5 + sy1 < dy2 && 5 + sy2 > dy1) {
                if (lixoTemp.getTipoLixo() == gari.getLixeira()) {
                    consecutivo++;
                    if(multiplicador < 4){
                        if(consecutivo % 4 == 0){
                            multiplicador ++;
                        }
                    }
                    pontuacao += 1 * multiplicador;
                    
                } else {
                    consecutivo = 0;
                    multiplicador = 1;
                    this.vidas--;
                }
            
                lixo.remove(i);
            }else if(dy1 > 600){
                lixo.remove(i);
                vidas--;
            }
        }
    }

    private class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Lixo iniTemp;
            for (int i = 0; i < lixo.size(); i++) {
                iniTemp = lixo.get(i);
                iniTemp.mover(velocidade);
            }
            colisaoLixo();
            gari.mover();
            pixel -= velocidade;
            if (pixel < 290) {
                pixel = 640;
            }
            if(vidas < 0){
                gameOver = true;
                jogando = false;
                fase.repaint();
                novoLixo.stop();
                musicaJogo.stop();
                musicaMenu.loop();
            }
            switch (vidasExtras) {
                case 0:
                    if(pontuacao >= 100){
                        vidasExtras ++;
                        vidas += vidasExtras;
                        velocidade ++;
                    }   break;
                case 1:
                    if(pontuacao >= 500){
                        vidasExtras ++;
                        vidas += vidasExtras;
                    }   break;
                case 2:
                    if(pontuacao >= 1000){
                        vidasExtras ++;
                        vidas += vidasExtras;
                        velocidade++;
                    }   break;
                default:
                    break;
            }
            fase.repaint();
        }
    }

    public class Fase extends JPanel {

        protected static final int lar = 480;
        protected static final int alt = 640;

        public void paint(Graphics g) {
            grafico = (Graphics2D) g;
            if(inicio){
                grafico.drawImage(telaPrincipal, 0, 0, null);
            }else if(controles){
                grafico.drawImage(telaControle, 0, 0, null);
            }else if(jogando){
                grafico.drawImage(
                        fundo,
                        0,
                        0,
                        480,
                        640,
                        0,
                        pixel,
                        480,
                        pixel + 640,
                        null);
                Lixo lixoTemp;
                for (int i = 0; i < lixo.size(); i++) {
                    lixoTemp = lixo.get(i);
                    grafico.drawImage(
                            lixoTemp.getImagem(),
                            (int) lixoTemp.getX(),
                            (int) lixoTemp.getY(),
                            (int) lixoTemp.getX() + lixoTemp.getLar(),
                            (int) lixoTemp.getY() + lixoTemp.getAlt(),
                            0,
                            0,
                            lixoTemp.getLar(),
                            lixoTemp.getAlt(),
                            this
                    );
                }
                grafico.drawImage(gari.getImage(),
                        (int) gari.getX(),
                        (int) gari.getY(),
                        (int) gari.getX() + gari.getLar(),
                        (int) gari.getY() + gari.getAlt(),
                        gari.getPos() * gari.getLar(),
                        70 * gari.getLixeira(),
                        gari.getPos() * gari.getLar() + gari.getLar(),
                        70 * gari.getLixeira() + gari.getAlt(),
                        null
                );
                grafico.fill3DRect(0, 0, 480, 30, false);
                grafico.setColor(Color.white);
                grafico.drawString(String.format("Vidas = %d", vidas), 20, 20);
                grafico.drawString(String.format("Pontuação = %d", pontuacao), 300, 20);
                grafico.drawString(String.format("Velocidade = %d", velocidade), 100, 20);
            }else if(gameOver){
                grafico.drawImage(telaGameover, 0, 0, null);
                grafico.fill3DRect(165, 335, 160, 25, false);
                grafico.setColor(Color.white);
                grafico.drawString(String.format("Sua pontuação = %d", pontuacao), 170, 350);
            }
            g.dispose();
        }

        public int getLar() {
            return lar;
        }

        public int getAlt() {
            return alt;
        }
    }
   
}
