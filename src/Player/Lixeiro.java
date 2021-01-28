package Player;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Lixeiro {

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    private double x;
    private double y;
    private double dx;
    private double dy;
    private int pos;
    private int lixeira;
    private ImageIcon referencia;
    private Image imagem;
    private boolean isVisible;
    private final int largura = 34;
    private final int altura = 70;

    public Lixeiro() {

        referencia = new ImageIcon("src/res/lixeiro.png");
        referencia.setImage(referencia.getImage().getScaledInstance(102, 280, 100));
        imagem = referencia.getImage();
        pos = 1;
        lixeira = 0;
        x = 285;
        y = 500;
        isVisible = true;
    }

    public void mover() {
        setX(x + dx);
        setY(y + dy);
        if (this.x < 1) {
            this.setX(1);
        }
        if (this.x > 402) {
            this.setX(402);
        }
        if (this.y < 1) {
            this.setY(1);
        }
        if (this.y > 530) {
            this.setY(530);
        }
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Image getImage() {
        return imagem;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getAlt() {
        return altura;
    }

    public int getLar() {
        return largura;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visivel) {
        this.isVisible = visivel;
    }

    public int getLixeira() {
        return lixeira;
    }

    public void setLixeira(int lixeira) {
        this.lixeira = lixeira;
    }
}
