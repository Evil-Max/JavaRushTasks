package com.javarush.task.task32.task3209;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

public class Controller {
    private View view;
    private HTMLDocument document;
    private File currentFile;

    public HTMLDocument getDocument() {
        return document;
    }

    public void init() {
        createNewDocument();
    }
    public void exit() {
        System.exit(0);
    }

    public Controller(View view) {
        this.view = view;
    }

    public void resetDocument() {
        if (document!=null) {
            document.removeUndoableEditListener(view.getUndoListener());
        }
        document = (HTMLDocument) (new HTMLEditorKit()).createDefaultDocument();
        document.addUndoableEditListener(view.getUndoListener());
        view.update();
    }
    public void setPlainText(String text) {
        resetDocument();
        StringReader reader = new StringReader(text);
        try {
            (new HTMLEditorKit()).read(reader,document,0);
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }
    public String getPlainText() {
        StringWriter writer = new StringWriter();
        try {
            (new HTMLEditorKit()).write(writer,document,0,document.getLength());
        } catch (Exception e) {
            ExceptionHandler.log(e);
        } finally {
            return writer.toString();
        }

    }
    public void createNewDocument() {
        view.selectHtmlTab();
        resetDocument();
        view.setTitle("HTML редактор");
        view.resetUndo();
        currentFile = null;

    }
    public void openDocument() {
        view.selectHtmlTab();
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new HTMLFileFilter());
        int choose = jFileChooser.showOpenDialog(view);
        if (choose == JFileChooser.APPROVE_OPTION) {
            currentFile = jFileChooser.getSelectedFile();
            resetDocument();
            view.setTitle(currentFile.getName());
            try {
                FileReader fileReader = new FileReader(currentFile);
                new HTMLEditorKit().read(fileReader, document, 0);
                view.resetUndo();

            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
    }
    public void saveDocument() {
        if (currentFile == null) saveDocumentAs();
        else {
            view.selectHtmlTab();
            view.setTitle(currentFile.getName());

            try {
                FileWriter fileWriter = new FileWriter(currentFile);
                new HTMLEditorKit().write(fileWriter, document, 0, document.getLength());
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }

        }
    }
    public void saveDocumentAs() {
        view.selectHtmlTab();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new HTMLFileFilter());

        if (fileChooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            view.setTitle(currentFile.getName());
            try {
                FileWriter fileWriter = new FileWriter(currentFile);
                (new HTMLEditorKit()).write(fileWriter,document,0,document.getLength());
                fileWriter.flush();
                fileWriter.close();
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
    }


    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();

    }
}
