package Alexandr_Nikitchenko;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
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

        // Действия при нажатии кнопок browse1 и browse2
        if (theButton == parent.browse1) {
            copyFrom();
            parent.inform.setText(null);
        } else if (theButton == parent.browse2) {
            copyTo();
            parent.inform.setText(null);
        }

        // Действие при нажатии кнопки copybutton
        if (theButton == parent.copyButton) {
            try {
                buttonCopy();
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(null,"Ошибка копирования!",
                        "Сообщение об ошибке", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

        // Метод CopyFrom
        void copyFrom(){

            // Создание окна для выбора файла
            parent.fileChooserFrom = new JFileChooser();
            int ret = parent.fileChooserFrom.showDialog(null, "Выбрать файл...");

            // Если файл выбран, то добавить путь к в файлу в поле edit1
            if (ret == JFileChooser.APPROVE_OPTION) {
                parent.fileChooserFrom.setMultiSelectionEnabled(true);
                File file = parent.fileChooserFrom.getSelectedFile();
                parent.edit1.setText(String.valueOf(file.getAbsoluteFile()));
            }
        }

         // Метод copyTo
         void copyTo() {

             // Создание окна для выбора места для копирования
            parent.fileChooserTo = new JFileChooser();
            parent.fileChooserTo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            parent.fileChooserTo.setAcceptAllFileFilterUsed(false);

            int ret1 = parent.fileChooserTo.showDialog(null, "Выбрать папку...");

            // Если указано место для копирования, то прописать путь к директории, в поле edit2
            if (ret1 == JFileChooser.APPROVE_OPTION) {
            File file1 = parent.fileChooserTo.getSelectedFile();
            parent.edit2.setText(file1.getAbsolutePath());
        }

    }

        // Метод buttonCopy
        void buttonCopy() throws IOException {

            // Источник для копирования
            File sourceFile = parent.fileChooserFrom.getSelectedFile();

            // Конечный файл
            String destinationFile = parent.fileChooserTo.getSelectedFile() + "\\" + sourceFile.getName();

            // Открытие потоков считывания и записи файла
            FileInputStream fileInputStream = new FileInputStream(sourceFile);
            FileOutputStream fileOutputStream = new FileOutputStream(destinationFile);

            // Пока считываемый файл больше ноля, записываем новый файл: 0 - конец файла
            int bufferSize;
            byte[] buffer = new byte[1024];
            while ((bufferSize = fileInputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, bufferSize);

                // Как только будет завершено копирование, выдать сообщение в поле inform
                parent.inform.setText("Копирование завершено!" );
            }

            // Закрыть потоки
            fileInputStream.close();
            fileOutputStream.close();

        }
}
