/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JOptionPane;

/**
 *
 * @author Irana
 */
public class AddUrl extends javax.swing.JFrame {

    Main main;

    /**
     * Creates new form AddUrl
     */
    public AddUrl(Main main) {
        initComponents();
        this.setResizable(false);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((int) (d.getWidth() - this.getWidth()) / 2, (int) (d.getHeight() - this.getHeight()) / 2);
        this.setTitle("Add URL");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.main = main;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        url_tf = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        depth_tf = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        path_tf = new javax.swing.JTextField();
        ok_btn = new javax.swing.JButton();
        cancel_btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Enter new address to download.");

        jLabel2.setText("Addres:");

        jLabel3.setText("Enter depth to crawl.");

        jLabel4.setText("depth:");

        jLabel5.setText("Enter directory to save files.");

        jLabel6.setText("path:");

        ok_btn.setText("OK");
        ok_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ok_btnMouseClicked(evt);
            }
        });

        cancel_btn.setText("Cancel");
        cancel_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancel_btnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cancel_btn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ok_btn))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(18, 18, 18)
                                    .addComponent(path_tf))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(url_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(depth_tf))
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)))))
                        .addContainerGap(15, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(url_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(depth_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(path_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ok_btn)
                    .addComponent(cancel_btn))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancel_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancel_btnMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_cancel_btnMouseClicked

    private void ok_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ok_btnMouseClicked
        // TODO add your handling code here:
        if (depth_tf.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "please fill all of the fields.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int depth=-1;
        try {
            depth = Integer.parseInt(depth_tf.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "depth should be a number", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!url_tf.getText().isEmpty() && !path_tf.getText().isEmpty() && depth >= 0) {
            main.dispose();
            Main main2 = new Main(url_tf.getText(), Integer.parseInt(depth_tf.getText()), path_tf.getText());
            main2.setVisible(true);
            this.dispose();
        }else {
            if(depth<0){
                JOptionPane.showMessageDialog(rootPane, "depth should be a positive number", "ERROR", JOptionPane.ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(rootPane, "please fill all of the fields.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            
        }
    }//GEN-LAST:event_ok_btnMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancel_btn;
    private javax.swing.JTextField depth_tf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton ok_btn;
    private javax.swing.JTextField path_tf;
    private javax.swing.JTextField url_tf;
    // End of variables declaration//GEN-END:variables
}
