package controlador;


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
 
public class juego implements ActionListener{
    
    Random aleatorio = new Random ();
    String numIngresado="", resul="RESULTADO:";
    char numGen[] = new char[ 10 ]; //arreglo que almacenara el numero de 4 digitos generado 
    int fijas, picas, paso;
    boolean estado = false;   
    
    //objetos para la GUI de la APP.
    Panel p1, p2;
    JButton jb1, jb2;
    JLabel jl1, jl2, tituloFrame;
    JTextField jt1,jt2;
    TextArea jtA1;
    
    public juego(){
        
        JFrame fMain = new JFrame("Profe funcionaa :D ");
        fMain.setLayout( new BorderLayout(4, 6));
        
        iz();
        der();
        
        fMain.add(p1, BorderLayout.WEST);
        fMain.add(p2, BorderLayout.EAST);        
        
        fMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fMain.setResizable(false);
        fMain.setSize(850, 450);
        fMain.setLocation(100, 60);
        fMain.setVisible(true);        
    }
    
    public void iz(){
        
        p1 = new Panel();
        p1.setLayout(null);
        
        jl1 = new JLabel("JUGADOR VS MAQUINA");
        jl2 = new JLabel("Digite los valores:  ");
        
        jt1 = new JTextField(5);     
        jt2 = new JTextField(5); 
        
        jb1 = new JButton("Jugar");
        jb2 = new JButton("Reiniciar");        
        
        jl1.setFont(new Font("Verdana", Font.BOLD, 20));        
        jt1.setHorizontalAlignment(JTextField.RIGHT);
        
        jb2.setEnabled(false); jt1.setEditable(false);
 
        
        jl1.setBounds(10, 15, 125, 25);
        
        jl2.setBounds(5, 60, 95, 25);
        jt1.setBounds(110, 60, 60, 25);
                
        jb1.setBounds(10, 120, 80, 25);
        jb2.setBounds(10, 150, 97, 25);
        
        p1.add(jl1); p1.add(jl2); p1.add(jt1); p1.add(jb1); p1.add(jb2);
        
        jb1.addActionListener(this); jb2.addActionListener(this);
        
        p1.setSize(300, 200);
        p1.setVisible(true);        
    }
    
    public void der(){
        
        p2 = new Panel();
        p2.setLayout(null);
        
        jtA1 = new TextArea(20, 25);
        
        jtA1.append(resul);
        jtA1.setEditable(false);
        
        jtA1.setBounds(5, 45, 330, 250);
        p2.add(jtA1);
        
        p2.setSize(350, 200);
        p2.setVisible(true);        
    }
    
    public void genera(){
        int i=0, num;
        while( i < 10 ){
            paso = 0;
            num= aleatorio.nextInt(10);
            for(int j=0;j<i;j++){
                if( numGen[j] == (char) (num+48) ){
                    paso = 1;
                }
            }
            if( paso == 0 ){
                numGen[ i ] = (char)( num + 48 );
                i++;
            }
        }
    }
    
    public boolean compara(){
        
        int i, j; 
        char[] arrIngr = numIngresado.toCharArray();
        fijas = picas = 0 ;
        for( i = 0 ; i < numGen.length; i++ ){
            for( j = 0 ; j < 10 ; j++ ){
               if( i == j ){
                  if( numGen[i] == arrIngr[j] ){
                     fijas++;     
                     break;
                  }
               }
               else{
                  if( numGen[i] == arrIngr[j] ){
                    picas++;        
                    break;
                  }           
               }     
            }
          }
        return false;
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == jb1){
            if(!estado){
                estado=true;   jb2.setEnabled(true); jt1.setEditable(true);
                genera();
                JOptionPane.showMessageDialog(null, "Numero generado: "+String.valueOf(numGen));
            }
            else{
                if(jt1.getText().length() == 10){
                    numIngresado = jt1.getText();
                    compara();
                    if(fijas==10){
                        jtA1.append("Numero: "+numIngresado+"   Picas: "+picas+"   Fijas:"+fijas+"\n");
                        jtA1.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Has ganado!!!", "JUEGO FINALIZADO :)", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else
                        jtA1.append("Numero: "+numIngresado+"   Picas: "+picas+"   Fijas:"+fijas+"\n");
                }
                else
                    JOptionPane.showMessageDialog(null, "Son 10 Digitos!!!!", "Cantidad de Digitos", JOptionPane.ERROR_MESSAGE);
            }
                
        }
        else if(e.getSource() == jb2){
            estado=false;
            resul="RESULTADO \n";
            jtA1.setEnabled(true);
            jtA1.setText(resul);
        }
    }
    
    public static void main(String[] args) {
       
        String ax = System.getProperty("os.name"); //variable que toma el nombre del sistema operativo que se tenga instalado
        
       
        }
    }
    

