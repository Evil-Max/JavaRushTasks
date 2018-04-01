package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;
import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {
    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextPane htmlTextPane = new JTextPane();
    private JEditorPane plainTextPane = new JEditorPane();
    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);


    public View() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            ExceptionHandler.log(e);
        };
    }

    public void initMenuBar()  {
        JMenuBar jMenuBar = new JMenuBar();
        MenuHelper.initFileMenu(this,jMenuBar);
        MenuHelper.initEditMenu(this,jMenuBar);
        MenuHelper.initStyleMenu(this,jMenuBar);
        MenuHelper.initAlignMenu(this,jMenuBar);
        MenuHelper.initColorMenu(this,jMenuBar);
        MenuHelper.initFontMenu(this,jMenuBar);
        MenuHelper.initHelpMenu(this,jMenuBar);
        getContentPane().add(jMenuBar,BorderLayout.NORTH);
    }
    public void selectedTabChanged() {
        if (tabbedPane.getSelectedIndex()==0) {
           controller.setPlainText(plainTextPane.getText());
        } else if (tabbedPane.getSelectedIndex()==1) {
           plainTextPane.setText(controller.getPlainText());
        }
        resetUndo();
    }
    public void initEditor() {
        htmlTextPane.setContentType("text/html");
        JScrollPane jScrollPane1 = new JScrollPane(htmlTextPane);
        tabbedPane.addTab("HTML",jScrollPane1);
        JScrollPane jScrollPane2 = new JScrollPane(plainTextPane);
        tabbedPane.addTab("Текст",jScrollPane2);
        tabbedPane.setPreferredSize(new Dimension(800,700));
        tabbedPane.addChangeListener(new TabbedPaneChangeListener(this));

        getContentPane().add(tabbedPane,BorderLayout.CENTER);
        Image image = Toolkit.getDefaultToolkit().createImage( getClass().getResource("icon.png") );
        setIconImage( image );
    }
    public void initGui() {
        initMenuBar();
        initEditor();
        pack();
    }

    public Controller getController() {
        return controller;
    }
    public void init() {
        initGui();
        addWindowListener(new FrameListener(this));
        setVisible(true);
    }
    public void exit() {
        controller.exit();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Новый":
                controller.createNewDocument();
                break;
            case "Открыть":
                controller.openDocument();
                break;
            case "Сохранить":
                controller.saveDocument();
                break;
            case "Сохранить как...":
                controller.saveDocumentAs();
                break;
            case "Выход":
                controller.exit();
                break;
            case "О программе":
                showAbout();
                break;
        }
    }
    public boolean canUndo() {
        return undoManager.canUndo();
    }
    public boolean canRedo() {
        return undoManager.canRedo();
    }
    public void redo() {
        try {
            undoManager.redo();
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }
    public void undo() {
        try {
            undoManager.undo();
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }
    public void resetUndo() {
        undoManager.discardAllEdits();
    }

    public UndoListener getUndoListener() {
        return undoListener;
    }

    public boolean isHtmlTabSelected() {
        return (tabbedPane.getSelectedIndex()==0);
    }

    public void selectHtmlTab() {
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }
    public void update() {
        htmlTextPane.setDocument(controller.getDocument());
    }
    public void showAbout() {
        JOptionPane.showMessageDialog(this,"HTML Editor","About",JOptionPane.INFORMATION_MESSAGE);
    }

}
