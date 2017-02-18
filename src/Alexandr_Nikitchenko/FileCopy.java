package Alexandr_Nikitchenko;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FileCopy extends JFrame {

    // Объявление компонентов окна
    JPanel north, center, south;
    JButton browse1, browse2, copyButton;
    JTextField edit1, edit2;
    JFileChooser fileChooserFrom, fileChooserTo;
    JLabel copyFrom, copyTo, inform;
    Font fontButton;


    public static void main(String[] args) {

        // Ссылка на конструктор окна
	    new FileCopy();
    }

        // Создание конструктора окна
        FileCopy(){
        super("Копирование файлов");

        // Размер окна
            setSize(400, 220);

            // Цвет окна
            setBackground(Color.GRAY);

            // Расположение окна
            setLocationRelativeTo(null);

            // Запрет на изменение размеров окна
            setResizable(false);

            // Действие при закритии окна
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            // Создаем северную панель контента
            north = new JPanel(new GridLayout(1,3, 5, 5));
            north.setBorder(new EmptyBorder(10,10,10,10));

            // Создаем текстовое поле, поле ввода и кнопку browse1
            copyFrom = new JLabel("<html><p> Копировать из: ", SwingConstants.CENTER);
            north.add(copyFrom);

            // Поле ввода
            edit1 = new JTextField();
            edit1.setEditable(false);
            north.add(edit1);

            // Кнопка
            browse1 = new JButton("Обзор");
            fontButton = new Font(Font.MONOSPACED, Font.BOLD, 15);
            browse1.setFont(fontButton);
            north.add(browse1);

            // Создаем центральную панель контента
            center = new JPanel(new GridLayout(1,3, 5, 5));
            center.setBorder(new EmptyBorder(10,10,10,10));

            // Создаем текстовое поле, поле ввода и кнопку browse1
            copyTo = new JLabel("<html><p> Копировать в: ", SwingConstants.CENTER);
            center.add(copyTo);

            // Поле ввода
            edit2 = new JTextField();
            edit2.setEditable(false);
            center.add(edit2);

            // Кнопка
            browse2 = new JButton("Обзор");
            fontButton = new Font(Font.MONOSPACED, Font.BOLD, 15);
            browse2.setFont(fontButton);
            center.add(browse2);

            // Создаем южную панель контента
            south = new JPanel(new GridLayout(2,1, 5, 5));
            south.setBorder(new EmptyBorder(10,10,10,10));

            // Поле информации
            inform = new JLabel("<html><p> ", SwingConstants.CENTER);
            south.add(inform);


            // Кнопка
            copyButton = new JButton("Копировать");
            fontButton = new Font(Font.MONOSPACED, Font.BOLD, 15);
            copyButton.setFont(fontButton);
            south.add(copyButton);

            // Добавляем панель north в окно
            this.add(north,"North");
            this.add(center,"Center");
            this.add(south,"South");

            // Делаем окно видимым
            setVisible(true);

            FileCopyEngine engine = new FileCopyEngine(this);
            browse1.addActionListener(engine);
            browse2.addActionListener(engine);
            copyButton.addActionListener(engine);
    }
}
