package iug;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ImageCarte extends JPanel {
  BufferedImage image;

  public ImageCarte(BufferedImage image) {
    this.image = image;
  }

  @Override
  protected void paintComponent(Graphics g) {
    g.drawImage(image, 151, 210, this);
  }
}