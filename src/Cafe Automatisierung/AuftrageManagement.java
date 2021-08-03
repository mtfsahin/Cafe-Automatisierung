/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmyr;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.awt.event.KeyEvent;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author musta
 */
public class AuftrageManagement extends javax.swing.JFrame {

    /**
     * Creates new form Aufträge
     */
    public AuftrageManagement() {
       
        initComponents();
        showKunden();
        showGetränkekarte();
        showAuftrage();
    }

    
    
     public void Insert() throws SQLException {
        Connection connect = null;
        db db = new db();
        PreparedStatement statement = null;

        try {
            connect = db.getConnection();
            String sql = "insert into oopgruppenprojekt.auftrage (getranke_name,kunden_name,preis)" + "values (?,?,?)";
            
            statement = connect.prepareStatement(sql);
            
            
            statement.setString(1, Gtranke_name.getText());
            statement.setString(2, Kunden_name.getText());
            statement.setString(3, preis.getText());
         //   statement.setString(4, gesamt.getText());
            
            statement.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Hinzufügen zur Datenbank erfolgreich abgeschlossen.");

        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();
            connect.close();
        }

    }
    
    
    
     
     public ArrayList<Auftrage> auftrageList() {

        ArrayList<Auftrage> auftrageList = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/oopgruppenprojekt?useSSL=false", "root", "hum1234");
            Statement st = (Statement) con.createStatement();
            String sql = "select * from auftrage";
            ResultSet rs = st.executeQuery(sql);
            Auftrage auftrage;

            while (rs.next()) {
                auftrage = new Auftrage(rs.getString("getranke_name"), rs.getString("kunden_name"), rs.getInt("preis"),rs.getInt("menge") , rs.getInt("gesamt"));
                auftrageList.add(auftrage);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetränkekarteManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return auftrageList;
    }

    public void showAuftrage() {
        ArrayList<Auftrage> list = auftrageList();
        DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
        Object[] row = new Object[5];
        for (int i = 0;i<list.size(); i++) {
            row[0] = list.get(i).getGetrankeName();
            row[1] = list.get(i).getKundenName();
            row[2] = list.get(i).getPreis();
            row[3] = list.get(i).getGesammt();      
            model.addRow(row);
        }
    }
        public ArrayList<Kunden> kundenList() {

        ArrayList<Kunden> kundenList = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/oopgruppenprojekt?useSSL=false", "root", "hum1234");
            Statement st = (Statement) con.createStatement();
            String sql = "select * from kunden";
            ResultSet rs = st.executeQuery(sql);
            Kunden kunden;

            while (rs.next()) {
                kunden = new Kunden(rs.getInt("id"), rs.getString("name"), rs.getString("geburtsdatum"), rs.getString("adresse"));
                kundenList.add(kunden);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetränkekarteManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kundenList;
    }

    public void showKunden() {
        ArrayList<Kunden> list = kundenList();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        Object[] row = new Object[4];
        for (int i = 0; i < 2; i++) {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getName();
            model.addRow(row);
        }
    }
    
    
    
    public ArrayList<Getränkekarte> GetränkekarteList() {

        ArrayList<Getränkekarte> GetränkekarteList = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/oopgruppenprojekt?useSSL=false", "root", "hum1234");
            Statement st = (Statement) con.createStatement();
            String sql = "select * from getränke";
            ResultSet rs = st.executeQuery(sql);
            Getränkekarte getränkekarte;

            while (rs.next()) {
                getränkekarte = new Getränkekarte(rs.getInt("id_getränke"), rs.getInt("preis"), rs.getString("name"), rs.getString("Materialien"), rs.getString("getränkecol"));
                GetränkekarteList.add(getränkekarte);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetränkekarteManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return GetränkekarteList;
    }

    public void showGetränkekarte() {
        ArrayList<Getränkekarte> list = GetränkekarteList();
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        Object[] row = new Object[5];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getName();
            row[2] = list.get(i).getMaterialien();
            row[3] = list.get(i).getGetränkecol();
            row[4] = list.get(i).getPreis();
            model.addRow(row);
        }
    }
    
    
    public void sales(){
        
        String ngesamt = gesamt.getText(); 
        String nzahlen = zahlen.getText(); 
        String ngkosten = gkosten.getText(); 
        
        int lastid = 0;
        
         Connection con;
         PreparedStatement pst;
        PreparedStatement pst1;
         ResultSet rs;
        
        
     try {    

         
        Class.forName("com.mysql.jdbc.Driver");
        con =  (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/oopgruppenprojekt?useSSL=false", "root", "hum1234");
        String query = "insert into umsatz(gesamt,zahlen,balance)values(?,?,?)";
        pst = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        pst.setString(1, ngesamt);
        pst.setString(2, nzahlen);
        pst.setString(3, ngkosten);
        pst.executeUpdate();
        rs = pst.getGeneratedKeys();
        
      /*  
        if(rs.next()){ 
            lastid = rs.getInt(1);
        }
        int rows = jTable3.getRowCount();
        
        String query1 = "insert into auftrage(id,getranke_name,kunden_name,preis,gesamt)values(?,?,?,?,?)";
        pst1 = con.prepareStatement(query1);
        
        String getranke_name = "";
        String kudnen_name = "";
        String preis;
        int gesamt = 0;
        
        for(int i = 0 ; i<jTable3.getRowCount();i++){
            getranke_name = (String) jTable3.getValueAt(i,0);
            kudnen_name = (String) jTable3.getValueAt(i,1);
            preis = (String) jTable3.getValueAt(i,2);
            gesamt = (int) jTable3.getValueAt(i,3);
            
            pst1.setInt(1, lastid);
            pst1.setString(2, getranke_name);
            pst1.setString(3, kudnen_name);
            pst1.setString(4, preis);
            pst1.setInt(5, gesamt);
            pst1.executeUpdate();    
        }
         */      
        JOptionPane.showMessageDialog(this, "Verkauf abgeschlossen.");
        
     }catch(SQLException ex){
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AuftrageManagement.class.getName()).log(Level.SEVERE, null, ex);
            }

    }
    
    
      public void delete() throws SQLException {
        Connection connect = null;
        db db = new db();
        PreparedStatement statement = null;
        try {
            connect = db.getConnection();          
            String sql = "delete from oopgruppenprojekt.auftrage";
            statement = connect.prepareStatement(sql);
            statement.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Löschung aus Datenbank erfolgreich abgeschlossen.");

        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();
            connect.close();
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        KundenId = new javax.swing.JTextField();
        preis = new javax.swing.JTextField();
        gesamt = new javax.swing.JTextField();
        zahlen = new javax.swing.JTextField();
        gkosten = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        Gtranke_name = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        Kunden_name = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButton2_anmeldung2 = new javax.swing.JButton();
        jButton2_anmeldung3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(75, 123, 236));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Candara Light", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Aufträge");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 17, Short.MAX_VALUE)
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kunden ID", "Kunden Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Getranke id", "Name", "Materilien", "Getränkecol", "Preis"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Getranke Name", "Kunden Name", "Preis"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Kunden ID");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Getranke ID");

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Preis");

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Zahlen");

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Gesamtkosten");

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Gesamt");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        KundenId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KundenIdKeyPressed(evt);
            }
        });

        preis.setEditable(false);
        preis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preisActionPerformed(evt);
            }
        });

        gesamt.setEditable(false);
        gesamt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gesamtActionPerformed(evt);
            }
        });

        zahlen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zahlenActionPerformed(evt);
            }
        });

        gkosten.setEditable(false);
        gkosten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gkostenActionPerformed(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Getranke Name");

        Gtranke_name.setEditable(false);

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Kunden Name");

        jButton1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton1.setText("Hinzufügen");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton2.setText("Rechnung drucken");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 371, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 9, Short.MAX_VALUE)
        );

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Rechung");

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Auftrag");

        Kunden_name.setEditable(false);
        Kunden_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Kunden_nameActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jButton2_anmeldung2.setBackground(new java.awt.Color(255, 204, 102));
        jButton2_anmeldung2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2_anmeldung2.setForeground(new java.awt.Color(89, 98, 117));
        jButton2_anmeldung2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Bilder/arrow-81-48.png"))); // NOI18N
        jButton2_anmeldung2.setText("Zurück");
        jButton2_anmeldung2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2_anmeldung2MouseClicked(evt);
            }
        });
        jButton2_anmeldung2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2_anmeldung2ActionPerformed(evt);
            }
        });

        jButton2_anmeldung3.setBackground(new java.awt.Color(255, 204, 204));
        jButton2_anmeldung3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2_anmeldung3.setForeground(new java.awt.Color(89, 98, 117));
        jButton2_anmeldung3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Bilder/exit-48 (1).png"))); // NOI18N
        jButton2_anmeldung3.setText("Ausfahrt");
        jButton2_anmeldung3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2_anmeldung3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(913, Short.MAX_VALUE)
                .addComponent(jButton2_anmeldung2)
                .addGap(18, 18, 18)
                .addComponent(jButton2_anmeldung3)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2_anmeldung2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2_anmeldung3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 75, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(7, 7, 7)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, Short.MAX_VALUE)
                    .addGap(8, 8, 8)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 859, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(preis, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel1))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(KundenId, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel13)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel11))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Gtranke_name, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Kunden_name, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(1193, 1193, 1193)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(gesamt, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(zahlen, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(gkosten, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(jLabel12))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(540, 540, 540)
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1460, 1460, 1460))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(KundenId, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(Gtranke_name, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(Kunden_name, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(preis, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(317, 317, 317))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(gesamt, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(zahlen, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(gkosten, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1263, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void preisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_preisActionPerformed

    private void gesamtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gesamtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gesamtActionPerformed

    private void zahlenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zahlenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_zahlenActionPerformed

    private void gkostenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gkostenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gkostenActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            
       
        
        Insert();
        DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
        model.setRowCount(0); 
        showAuftrage();
        
        int sum = 0;
       
        for(int i =0; i<jTable3.getRowCount();i++){
            sum = sum + Integer.parseInt(jTable3.getValueAt(i, 2).toString());
        }
        
        gesamt.setText(String.valueOf(sum));
        jTextField1.setText("");
        KundenId.setText("");
        Gtranke_name.setText("");
        Kunden_name.setText("");
        preis.setText("");
        jTextField1.requestFocus();
      
        
        } catch (SQLException ex) {
            Logger.getLogger(AuftrageManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void Kunden_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Kunden_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kunden_nameActionPerformed

    private void jButton2_anmeldung2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2_anmeldung2MouseClicked

    }//GEN-LAST:event_jButton2_anmeldung2MouseClicked

    private void jButton2_anmeldung2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2_anmeldung2ActionPerformed
        Hauptbildschirm frame = new Hauptbildschirm();
        frame.setVisible(true);
        this.hide();
    }//GEN-LAST:event_jButton2_anmeldung2ActionPerformed

    private void jButton2_anmeldung3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2_anmeldung3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2_anmeldung3ActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
       
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            
            try {    
           
            Connection con;
            PreparedStatement pst;
            ResultSet rs;
            
            
            Class.forName("com.mysql.jdbc.Driver");
            con =  (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/oopgruppenprojekt?useSSL=false", "root", "hum1234");
            
            String bcode = jTextField1.getText();
            pst = con.prepareStatement("select * from getränke where id_getränke = ?");
            pst.setString(1, bcode);
            rs=pst.executeQuery();
            
            
            if(rs.next() == false){
                JOptionPane.showMessageDialog(this,"Getränkecode nicht gefunden.");       
            }
            else{
                
                String bname = rs.getString("name");
                Gtranke_name.setText(bname.trim());
                
                String bpreis = rs.getString("preis");
                preis.setText(bpreis.trim());
                
                
                
            }
            
            }catch(SQLException ex){
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AuftrageManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            
        }
        
        
        
        
        
    }//GEN-LAST:event_jTextField1KeyPressed

    private void KundenIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KundenIdKeyPressed
        
         if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            
            try {    
           
            Connection con;
            PreparedStatement pst;
            ResultSet rs;
            
            Class.forName("com.mysql.jdbc.Driver");
            con =  (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/oopgruppenprojekt?useSSL=false", "root", "hum1234");
            
            String bcode = KundenId.getText();
            pst = con.prepareStatement("select * from kunden where id = ?");
            pst.setString(1, bcode);
            rs=pst.executeQuery();
            
            if(rs.next() == false){
                JOptionPane.showMessageDialog(this,"Buchcode nicht gefunden.");       
            }
            else{
                String bname = rs.getString("name");
                Kunden_name.setText(bname.trim());            
            }
            
            }catch(SQLException ex){
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AuftrageManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
 
        }
  
        
    }//GEN-LAST:event_KundenIdKeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        int ggesamt = Integer.parseInt(gesamt.getText());
        int ggesamtkosten = Integer.parseInt(zahlen.getText());
        int bal = ggesamtkosten - ggesamt;
        gkosten.setText(String.valueOf(bal));
        sales();
         try {
            delete();
        } catch (SQLException ex) {
            Logger.getLogger(AuftrageManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
        model.setRowCount(0);
        showAuftrage();
         
         
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AuftrageManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AuftrageManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AuftrageManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AuftrageManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AuftrageManagement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Gtranke_name;
    private javax.swing.JTextField KundenId;
    private javax.swing.JTextField Kunden_name;
    private javax.swing.JTextField gesamt;
    private javax.swing.JTextField gkosten;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton2_anmeldung2;
    private javax.swing.JButton jButton2_anmeldung3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField preis;
    private javax.swing.JTextField zahlen;
    // End of variables declaration//GEN-END:variables
}
