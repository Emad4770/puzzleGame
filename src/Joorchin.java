
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JApplet;

public class Joorchin extends JApplet implements KeyListener{
    
    Image m;
    int board[][];
    int brow, bcol, n = 3;
    int w = getWidth()/n;
    int h = getHeight()/n;
    int x, y, a = 1, k = 35;
    
   public void init(){
      
       setSize(1600,900);
       initboard();
       shuffle(k);     
       this.setFocusable(true);
       requestFocus();
       this.addKeyListener(this);
       m = getImage(getCodeBase(),"pic.jpg");
       
   } 
   
   void initboard(){
       
       board = new int[n][n];
       for ( int i = 0; i < board.length; i ++){
           for( int j = 0; j < board[i].length; j++){
               
               board[i][j] = i * n + j;
           }   
       }
       brow = bcol = n-1;
       
   }
   
   void shuffle(int k){
       
       for(int i = 0; i < k; i++){
           
           int d = (int) (4 * Math.random());
           switch(d){
               
               case 0 :
                   up();
                   break;
               case 1 :
                   down();
                   break;
                   
                case 2 :
                   right();
                   break;
                   
                case 3 :
                   left();
                   break;   
           }
       }
   }
   
   void up(){
       
       if (brow < n-1){
           
           board[brow][bcol] = board [brow + 1][bcol];
           brow++;
           
       }
   }
       void down(){
       
       if (brow > 0){
           
           board[brow][bcol] = board [brow - 1][bcol];
           brow--;
           
       }
   }
       void right(){
       
       if (bcol > 0){
           
           board[brow][bcol] = board [brow][bcol - 1];
           bcol--;
           
       }
   }
       void left(){
       
       if (bcol < n-1){
           
           board[brow][bcol] = board [brow][bcol + 1];
           bcol++;
           
       }
   }
    
       boolean won(){
           
           for(int i = 0; i < board.length; i++){
               for(int j= 0; j<board[i].length; j++){
                   
                   if (board[i][j] != i * n + j){
                       if ( i != brow || j != bcol){
                           return false;
                       }
                   }   
               }
           }
           return true;
       }
   
       
       
       
    public void paint (Graphics g){
        
       super.paint(g);
        
       showStatus("Level : " + a + "  Shuffle = " + k + "  n = " + n );
       
       int w = getWidth()/n;
       int h = getHeight()/n;
       int w1 = m.getWidth(this)/n;
       int h1 = m.getHeight(this)/n;
       
        
        for ( int i = 0; i < board.length; i++){
            for( int j = 0; j < board[i].length; j++){
                
                g.setColor(Color.blue);
                g.fillRect(j * w, i * h, w, h);
                g.setColor(Color.yellow);
                g.drawRect(j * w, i * h, w, h);
                
                g.setFont(new Font("Arial", Font.BOLD, h/3));
                
                int d = g.getFontMetrics().stringWidth(board[i][j] + "");
                
                x = board[i][j] / n;
                y = board[i][j] % n;
                
                if(a < 4){
                    
                    g.drawString(board[i][j] + "", j * w + (w - d)/2 , i * h + (h * 2)/3);
                    
                }
                
                  if(a == 4){
                      
                  g.drawImage(m, j * w, i * h, (j + 1) * w, (i + 1) * h, y * w1, x * h1, (y + 1) * w1, (x + 1) * h1, this);
                  g.drawString(board[i][j] + "", j * w + (w - d)/2 , i * h + (h * 2)/3);
                
                  }
                  
                  if ( a > 4 && a < 8){
                      
                      g.drawImage(m, j * w, i * h, (j + 1) * w, (i + 1) * h, y * w1, x * h1, (y + 1) * w1, (x + 1) * h1, this);
                  }
            }
        }
        
        g.setColor(Color.gray);
        g.fillRect(bcol * w, brow * h, w, h);
        g.setColor(Color.yellow);
        g.drawRect(bcol * w, brow * h, w, h);
           
        
        if(won()){
          
          g.setColor(Color.red);
          g.setFont(new Font("Arial",Font.BOLD, 60));
          
          if(a < 7){
          g.drawString("You Passed Level " + a + " !" , (getWidth())/3 , (getHeight())/2);
          }
          
          a++;
          
          switch(a){
              
              case 1:
                  k = 35;
                  break;
                  
              case 2:
                  k = 55;
                  break;
                  
              case 3:
                  k = 85;
                  n = 4;
                  break;
                  
              case 4:            // Pic + Num
                  k = 100;
                  n = 4;
                  break;
                  
              case 5:           // Pic
                  k = 150;
                  n = 3;
                  break;
                  
              case 6:
                  k = 200;
                  n = 4;
                  break;
                  
               case 7:
                  k = 255;
                  n = 5;
                  break;
                   
               case 8:
                   
                   int l = g.getFontMetrics().stringWidth("You Beat The Game!");
                   g.drawString("You Beat The Game!" , (getWidth()-l)/2 , (getHeight())/2);
                   showStatus("Congratulations!");
                   break;
              
          }
          
          initboard();
          shuffle(k);
          
          if (a >= 8){
              
              this.removeKeyListener(this);
              
          }
      }    
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            
            case KeyEvent.VK_UP:
                up();
                break;
                
            case KeyEvent.VK_DOWN:
                down();
                break;
                    
            case KeyEvent.VK_RIGHT:
                right();
                break;
                    
            case KeyEvent.VK_LEFT:
                left();
                break;     
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }  
    
}

