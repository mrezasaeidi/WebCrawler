package webcrawler;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.table.DefaultTableModel;

public class Main extends javax.swing.JFrame {

    public Main() {
        initComponents();
        this.setResizable(false);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((int) (d.getWidth() - this.getWidth()) / 2, (int) (d.getHeight() - this.getHeight()) / 2);
        this.setTitle("Web Crawler");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public Main(String url, int depth, String rootpath) {
        this();
        this.url = url;
        this.rootpath = rootpath;
        this.depth = depth;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    set_ui();
                } catch (InterruptedException ex) {

                }
            }
        }).start();

    }

    public void set_ui() throws InterruptedException {
        // start a circular progress bar here
        Tree tree = null;
        if (url.contains(".rar") || url.contains(".png") || url.contains(".jpg")
                || url.contains(".css") || url.contains(".zip") || url.contains(".png")
                || url.contains(".mp4") || url.contains(".mkv")) {
            try {
                Node root = new Node(new DownloadFile(url, rootpath));
                tree = new Tree(root);
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        } else {

            tree = new Tree(new Node(url, this.rootpath));
            LinkChecker li = new LinkChecker(tree.getRoot(), 1);
            li.join();
        }

        model = (DefaultTableModel) downloadList_tab.getModel();
        String a[] = new String[3];

        for (int i = 0; i < df.size(); i++) {
            model.addRow(a);
            int filesize = df.get(i).getFileSize();
            downloadList_tab.setValueAt(df.get(i).getFileName(), i, 0);
            downloadList_tab.setValueAt(((filesize != 0) ? "" + filesize : "Error with link"), i, 1);
            downloadList_tab.setValueAt((df.get(i).getStatus() ? "Downloaded" : "Not Downloaded"), i, 2);
        }
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < df.size(); i++) {
                    downloadList_tab.setValueAt((df.get(i).getStatus() ? "Downloaded" : "Not Downloaded"), i, 2);
                }
            }
        }, 0, 1000);

        System.out.println(allnodes.size());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        treeOfLinks = new javax.swing.JTree();
        jScrollPane3 = new javax.swing.JScrollPane();
        downloadList_tab = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        addUrl_item = new javax.swing.JMenu();
        downloadAll_item = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(treeOfLinks);

        downloadList_tab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "File Name", "Size", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        downloadList_tab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                downloadList_tabMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(downloadList_tab);

        addUrl_item.setText("Add URL");
        addUrl_item.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addUrl_itemMouseClicked(evt);
            }
        });
        jMenuBar1.add(addUrl_item);

        downloadAll_item.setText("Download All");
        downloadAll_item.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                downloadAll_itemMouseClicked(evt);
            }
        });
        jMenuBar1.add(downloadAll_item);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addUrl_itemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addUrl_itemMouseClicked
        // TODO add your handling code here:
        AddUrl add = new AddUrl(this);
        add.setVisible(true);
    }//GEN-LAST:event_addUrl_itemMouseClicked

    private void downloadAll_itemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_downloadAll_itemMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_downloadAll_itemMouseClicked

    private void downloadList_tabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_downloadList_tabMouseClicked
        // TODO add your handling code here:
        if (df == null) {
            return;
        }
        DownloadPage dp = new DownloadPage(df.get(downloadList_tab.getSelectedRow()));
        dp.setVisible(true);
    }//GEN-LAST:event_downloadList_tabMouseClicked

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });

    }

    private String url;
    private String rootpath;
    private int depth;
    private Node root = null;
    private DefaultTableModel model;
    protected static ArrayList<DownloadFile> df = new ArrayList<>();
    protected static ArrayList<Node> allnodes = new ArrayList<>();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu addUrl_item;
    private javax.swing.JMenu downloadAll_item;
    private javax.swing.JTable downloadList_tab;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTree treeOfLinks;
    // End of variables declaration//GEN-END:variables
}
