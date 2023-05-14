package function;

import java.awt.Graphics;  
import javax.swing.JLabel;  
public class MarqueeLabel extends JLabel {  
  /**
	 * 
	 */
	private static final long serialVersionUID = 5988801350961864921L;

public static int LEFT_TO_RIGHT = 1;  
  public static int RIGHT_TO_LEFT = 2;  
  String text;  
  int Option;  
  int Speed;  
  public MarqueeLabel(String text, int Option, int Speed) {  
    this.Option = Option;  
    this.Speed = Speed;  
    this.setText(text);  
  }  
  @Override  
  protected void paintComponent(Graphics g) {  
    if (Option == LEFT_TO_RIGHT) {  
      g.translate((int) ((System.currentTimeMillis() / Speed) % (getWidth() * 2) - getWidth()), 0);  
    } else if (Option == RIGHT_TO_LEFT) {  
      g.translate((int) (getWidth() - (System.currentTimeMillis() / Speed) % (getWidth() * 2)), 0);  
    }  
    super.paintComponent(g);  
    repaint(5);  
  }  
}  