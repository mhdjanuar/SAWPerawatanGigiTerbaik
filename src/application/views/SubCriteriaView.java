/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package application.views;

import application.dao.CriteriaDao;
import application.dao.SubCriteriaDao;
import application.daoimpl.CriteriaDaoImpl;
import application.daoimpl.SubCriteriaDaoImpl;
import application.models.CriteriaModel;
import application.models.SubCriteriaModel;
import application.utils.DatabaseUtil;
import java.awt.Font;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author mhdja
 */
public class SubCriteriaView extends javax.swing.JPanel {
    public final SubCriteriaDao subCriteriaDao;
    public final CriteriaDao criteriaDao;
    private final Map<String, Integer> criteriaMap = new HashMap<>();
    
    public void getAllData() {
        List<SubCriteriaModel> subCriteriaList = subCriteriaDao.findAll();
        
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"ID", "Kriteria", "Deksripsi", "Bobot"}); // Adjust column names as needed

        // Populate the model with data from spareparts
        for (SubCriteriaModel subCriteria : subCriteriaList) {
            model.addRow(new Object[]{subCriteria.getId(), subCriteria.getNameCriteria(), subCriteria.getDeskripsi(), subCriteria.getBobot()}); // Add more attributes as needed
        }
        
        // Set the table model to jTable1
        jTable1.setModel(model);
        
        // ============== Tambahkan bagian ini untuk perbesar font dan row height ==============
        // Set font table
        Font tableFont = new Font("SansSerif", Font.PLAIN, 16); // Ganti ukuran sesuai keinginan
        jTable1.setFont(tableFont);

        // Set row height
        jTable1.setRowHeight(28); // Ganti sesuai kebutuhan, misal 28 pixel

        // Optional: set font header lebih besar juga
        JTableHeader header = jTable1.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 18));
    }
    
    public void getCriteriaComboBox() {
        List<CriteriaModel> criteriaList = criteriaDao.findAll();
        
        jComboBox1.removeAllItems(); // Clear previous items from combo box
        criteriaMap.clear(); // Clear previous mappings

        // Add new items to the combo box from the spareparts list
        for (CriteriaModel criteria : criteriaList) {
            jComboBox1.addItem(criteria.getName()); // Add name to combo box
            criteriaMap.put(criteria.getName(), criteria.getId()); // Map name to ID
        }
    }
    
    public void resetForm() {
        jTextFieldDeskripsi.setText(""); // kosongkan deskripsi
        jTextFieldBobot.setText("");     // kosongkan bobot
        jComboBox1.setSelectedIndex(0); // set ke item pertama
        jTable1.clearSelection(); // hapus seleksi di tabel
    }


    /**
     * Creates new form SubCriteriaView
     */
    public SubCriteriaView() {
        initComponents();
        
        this.subCriteriaDao = new SubCriteriaDaoImpl();
        this.criteriaDao = new CriteriaDaoImpl();
        
        getAllData();
        getCriteriaComboBox();
        
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int selectedRow = jTable1.getSelectedRow();
                if (selectedRow != -1) {
                    // Ambil nilai dari kolom tabel
                    String deskripsi = jTable1.getValueAt(selectedRow, 2).toString();
                    String jumlahBobot = jTable1.getValueAt(selectedRow, 3).toString();
                    String namaKriteria = jTable1.getValueAt(selectedRow, 1).toString();

                    // Set ke textfield
                    jTextFieldDeskripsi.setText(deskripsi);
                    jTextFieldBobot.setText(jumlahBobot);

                    // Set ke combobox
                    for (int i = 0; i < jComboBox1.getItemCount(); i++) {
                        String item = jComboBox1.getItemAt(i);
                        if (item.equals(namaKriteria)) {
                            jComboBox1.setSelectedIndex(i);
                            break;
                        }
                    }
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextFieldDeskripsi = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldBobot = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setText("Pilih Kriteria");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setText("Deskripsi");

        jTextFieldDeskripsi.setColumns(20);
        jTextFieldDeskripsi.setRows(5);
        jScrollPane1.setViewportView(jTextFieldDeskripsi);

        jLabel7.setText("Bobot");

        jTextFieldBobot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBobotActionPerformed(evt);
            }
        });

        jButton2.setText("Simpan");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Ubah");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Hapus");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4))
                    .addComponent(jTextFieldBobot)
                    .addComponent(jScrollPane1))
                .addContainerGap(263, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldBobot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(128, 128, 128))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 318, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String description = jTextFieldDeskripsi.getText();
        int bobot = Integer.parseInt(jTextFieldBobot.getText());
        String selectedName = (String) jComboBox1.getSelectedItem();
        Integer selectedId = null;
        if (selectedName != null) {
            selectedId = criteriaMap.get(selectedName); // Get ID based on name
        }
        
        SubCriteriaModel subCriteria = new SubCriteriaModel();
        subCriteria.setIdCriteria(selectedId);
        subCriteria.setDeskripsi(description);
        subCriteria.setBobot(bobot);
        
        int result = subCriteriaDao.create(subCriteria);
        
        if (result > 0) {
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan.");
            
            getAllData();
            resetForm();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable1.getSelectedRow(); // ganti jTable1 dengan nama tabel kamu
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin diupdate terlebih dahulu.");
            return;
        }

        int id = Integer.parseInt(jTable1.getValueAt(selectedRow, 0).toString()); // kolom ID
        String nameKriteria = (String) jComboBox1.getSelectedItem();       
        int jumlahBobot = Integer.parseInt(jTextFieldBobot.getText());           
        String deskripsi = jTextFieldDeskripsi.getText();       
        
        List<CriteriaModel> criteriaList = criteriaDao.findAll();
        
        int idKriteria = -1;
        for (CriteriaModel criteria : criteriaList) {
            if (criteria.getName().equals(nameKriteria)) {
                idKriteria = criteria.getId();
                break;
            }
        }

        SubCriteriaModel model = new SubCriteriaModel();
        model.setId(id);
        model.setIdCriteria(idKriteria);
        model.setBobot(jumlahBobot);
        model.setDeskripsi(deskripsi);

        int updated = subCriteriaDao.update(model);
        if (updated > 0) {
            JOptionPane.showMessageDialog(this, "Data berhasil diperbarui!");
            getAllData();
            resetForm();
        } else {
            JOptionPane.showMessageDialog(this, "Gagal memperbarui data.");
        }  
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextFieldBobotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBobotActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBobotActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int selectedRow = jTable1.getSelectedRow();
    
        if (selectedRow >= 0) {
            int idSubCriteria = (int) jTable1.getValueAt(selectedRow, 0); // kolom 0 diasumsikan kolom ID
            SubCriteriaModel subCriteria = new SubCriteriaModel();
            subCriteria.setId(idSubCriteria);

            int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                subCriteriaDao.delete(subCriteria);
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
                getAllData();
                resetForm();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus!");
        }
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldBobot;
    private javax.swing.JTextArea jTextFieldDeskripsi;
    // End of variables declaration//GEN-END:variables
}
