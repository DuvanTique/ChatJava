package view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import viewModel.PaqueteEnvio;
/**
 *
 * @author Duvan
 */
public class ChatServer extends javax.swing.JFrame implements Runnable{

    public ChatServer() {
        initComponents();
        this.setVisible(true);
        Thread hilo1 = new Thread(this);
        hilo1.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaChat = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ChatServer");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTextAreaChat.setColumns(20);
        jTextAreaChat.setRows(5);
        jScrollPane1.setViewportView(jTextAreaChat);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaChat;
    // End of variables declaration//GEN-END:variables

    @Override
    @SuppressWarnings("ConvertToTryWithResources")
    public void run() {
        try {
            //Poner a la escucha la aplicacion
            ServerSocket servidor = new ServerSocket(9000);
            String nombre,mensaje,ip;
            PaqueteEnvio datosEntrada = new PaqueteEnvio();
            while (true) {                
                //Entrada
                Socket socket1 = servidor.accept();
                ObjectInputStream paqueteEntrada = new ObjectInputStream(socket1.getInputStream());
                datosEntrada = (PaqueteEnvio) paqueteEntrada.readObject();
                nombre = datosEntrada.getNombre();
                mensaje = datosEntrada.getMensaje();
                ip = datosEntrada.getIp();
                jTextAreaChat.append("\nIp"+ip+" - "+nombre+" - "+mensaje);
                //Salida
                Socket socket2 = new Socket(ip,9090);
                ObjectOutputStream paqueteSalida = new ObjectOutputStream(socket2.getOutputStream());
                paqueteSalida.writeObject(datosEntrada);
                socket2.close();
                socket1.close();
            }
        } catch (IOException ex) {
            System.out.println("Error:"+ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
