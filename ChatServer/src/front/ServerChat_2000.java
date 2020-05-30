
package front;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServerChat_2000 extends javax.swing.JFrame {
    
    ArrayList clientOutputStreams;
    ArrayList<String> users;
       
    public class ClientHandler implements Runnable{
        BufferedReader reader;
        Socket sock;
        PrintWriter client;
            
        public ClientHandler(Socket clientSocket, PrintWriter user){
            client = user;
            try{
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
            }
            catch (Exception ex){
                taServer.append("Erro Inesperado... \n");
            }
        }

        @Override
        public void run() {
            String aviso, connect = "Conectado", disconnect = "Desconectado", chat = "Chat" ;
            String[] dados;
                
            try{
                while ((aviso = reader.readLine()) != null){
                    taServer.append("Recebido: " + aviso + "\n");
                    dados = aviso.split(":");
                
                    for (String token:dados){
                        taServer.append(token + "\n");
                    }

                    if (dados[2].equals(connect)){
                        tellEveryone((dados[0] + ":" + dados[1] + ":" + chat));
                        addUsua(dados[0]);
                    } 
                    else if (dados[2].equals(disconnect)){
                        tellEveryone((dados[0] + ":desconectou." + ":" + chat));
                        removerUsua(dados[0]);
                    } 
                    else if (dados[2].equals(chat)){
                        tellEveryone(aviso);
                    } 
                    else{
                        taServer.append("Nenhuma condição foi atendida. \n");
                    }
                } 
            }
            catch (Exception ex){
                taServer.append("Perda de conexão. \n");
                ex.printStackTrace();
                clientOutputStreams.remove(client);
            }
        }
    }
  
    public ServerChat_2000() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        taServer = new javax.swing.JTextArea();
        btnIniciar = new javax.swing.JButton();
        btnParar = new javax.swing.JButton();
        btnUsers = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(0, 0));
        setName("server"); // NOI18N
        setResizable(false);

        taServer.setColumns(20);
        taServer.setRows(5);
        taServer.setName("taServer"); // NOI18N
        jScrollPane1.setViewportView(taServer);

        btnIniciar.setBackground(new java.awt.Color(204, 204, 204));
        btnIniciar.setText("Iniciar");
        btnIniciar.setName("btnIniciar"); // NOI18N
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        btnParar.setBackground(new java.awt.Color(204, 204, 204));
        btnParar.setLabel("Parar");
        btnParar.setName("btnParar"); // NOI18N
        btnParar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPararActionPerformed(evt);
            }
        });

        btnUsers.setBackground(new java.awt.Color(204, 204, 204));
        btnUsers.setLabel("Usuários");
        btnUsers.setName("btnUsers"); // NOI18N
        btnUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsersActionPerformed(evt);
            }
        });

        btnLimpar.setBackground(new java.awt.Color(204, 204, 204));
        btnLimpar.setLabel("Limpar");
        btnLimpar.setName("btnLimpar"); // NOI18N
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Swis721 Ex BT", 1, 14)); // NOI18N
        jLabel1.setText("ServerChat 2000");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnParar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnUsers, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                            .addComponent(btnLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIniciar)
                    .addComponent(btnUsers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnParar)
                    .addComponent(btnLimpar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        Thread inicializador = new Thread(new IniciarServidor());   //chama o método de iniciar o servidor
        inicializador.start();    //executa o método de iniciar o servidor
        
        taServer.append("Servidor iniciado! :) ...\n");
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void btnPararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPararActionPerformed
        try 
        {
            Thread.sleep(5000);                 //5000 millisegundos são 5 segundos de delay
        } 
        catch(InterruptedException ex) {Thread.currentThread().interrupt();}
        
        tellEveryone("Servidor: estou parando e desconectando todos oas usuários.\n:Chat");
        taServer.append("Servidor Parando! :( ... \n");
        
        taServer.setText("");
    }//GEN-LAST:event_btnPararActionPerformed

    private void btnUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsersActionPerformed
        taServer.append("\n Usuários Online: \n");
        for (String current_user : users) //busca os usuários
        {
            taServer.append(current_user); //adiciona o usuario encontrado na area de texto
            taServer.append("\n");
        }
    }//GEN-LAST:event_btnUsersActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        taServer.setText(""); //Limpa a area de texto
    }//GEN-LAST:event_btnLimparActionPerformed
  
    
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerChat_2000().setVisible(true);
            }
        });
    }

    
    // SUBCLASSE INCIARSERVIDOR
    public class IniciarServidor implements Runnable 
    {
        @Override
        public void run() 
        {
            clientOutputStreams = new ArrayList();
            users = new ArrayList();  

            try 
            {
                ServerSocket serverSock = new ServerSocket(2222);

                while (true) 
                {
				Socket clientSock = serverSock.accept();
				PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
				clientOutputStreams.add(writer);

				Thread listener = new Thread(new ClientHandler(clientSock, writer));
				listener.start();
				taServer.append("Possui uma conexão. \n");
                }
            }
            catch (Exception ex)
            {
                taServer.append("Erro ao tentar criar o servidor. \n");
            }
        }
    }
    
    //MÉTODOS UTILIZADOS ----------------
    public void tellEveryone(String aviso){
        Iterator it = clientOutputStreams.iterator();
        while (it.hasNext()) 
        {
            try{
                PrintWriter writer = (PrintWriter) it.next();
		writer.println(aviso);
		taServer.append("Enviando: " + aviso + "\n");
                writer.flush();
                taServer.setCaretPosition(taServer.getDocument().getLength());
            } 
            catch (Exception ex){
		taServer.append("Erro ao enviar a todos. \n");
            }
        } 
    }
    
    public void addUsua (String dados) 
    {
        String aviso, add = ": :Conectado", concluido = "Servidor: :concluido", nome = dados;
        taServer.append("Antes " + nome + " adicionado. \n");
        users.add(nome);
        taServer.append("Depois " + nome + " adicionado. \n");
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);

        for (String token:tempList) 
        {
            aviso = (token + add);
            tellEveryone(aviso);
        }
        tellEveryone(concluido);
    }
    
    public void removerUsua (String dados) 
    {
        String aviso, add = ": :Conectado", concluido = "Servidor: :concluido", nome = dados;
        users.remove(nome);
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);

        for (String token:tempList) 
        {
            aviso = (token + add);
            tellEveryone(aviso);
        }
        tellEveryone(concluido);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnParar;
    private javax.swing.JButton btnUsers;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea taServer;
    // End of variables declaration//GEN-END:variables
}
