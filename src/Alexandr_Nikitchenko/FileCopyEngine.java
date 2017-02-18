package Alexandr_Nikitchenko;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
/**
 * Created by Александр on 18.02.2017.
 */
public class FileCopyEngine implements ActionListener {
    FileCopy parent;

    FileCopyEngine(FileCopy parent) {
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Создаем источник действий
        Object theButton = e.getSource();

        // Логика работы программы
        if (theButton == parent.browse1) {
            copyFrom();
            parent.inform.setText(null);
        }

        if (theButton == parent.browse2) {
            copyTo();
            parent.inform.setText(null);
        }

        if (theButton == parent.copyButton) {
            try {
                buttonCopy();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

        // Метод CopyFrom
        void copyFrom(){
            parent.fileChooserFrom = new JFileChooser();
            int ret = parent.fileChooserFrom.showDialog(null, "Выбрать файл...");

            if (ret == JFileChooser.APPROVE_OPTION) {
                parent.fileChooserFrom.setMultiSelectionEnabled(true);
                File file = parent.fileChooserFrom.getSelectedFile();
                parent.edit1.setText(String.valueOf(file.getAbsoluteFile()));
            }
        }

         // Метод copyTo
         void copyTo() {
            parent.fileChooserTo = new JFileChooser();
            parent.fileChooserTo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            parent.fileChooserTo.setAcceptAllFileFilterUsed(false);
            int ret1 = parent.fileChooserTo.showDialog(null, "Выбрать папку...");
            if (ret1 == JFileChooser.APPROVE_OPTION) {
            File file1 = parent.fileChooserTo.getSelectedFile();
            parent.edit2.setText(file1.getAbsolutePath());
        }

    }

        // Метод buttonCopy
        void buttonCopy() throws IOException {
            File sourceFile = parent.fileChooserFrom.getSelectedFile();
            String destinationFile = parent.fileChooserTo.getSelectedFile() + "\\" + sourceFile.getName();
            FileInputStream fileInputStream = new FileInputStream(sourceFile);
            FileOutputStream fileOutputStream = new FileOutputStream(destinationFile);
            parent.inform.setText("Копирование завернено!" );

            int bufferSize;
            byte[] bufffer = new byte[1024];
            while ((bufferSize = fileInputStream.read(bufffer)) > 0) {
                fileOutputStream.write(bufffer, 0, bufferSize);
            }
            fileInputStream.close();
            fileOutputStream.close();

        }
}
