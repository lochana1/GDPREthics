package com.comparision;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class QueryWindow extends JFrame {

    private QueryHub hub;

    private String[] questions;

    public QueryWindow() {
        super();
        hub = new QueryHub();
        initQuestions();
        setupUI();
    }

    public void launch() {
        this.setVisible(true);
    }

    private void initQuestions() {
        try {
//            File dir = new File(ClassLoader.getSystemResources("questions").nextElement().getFile());
            File dir = new File("resources/questions");
//            todo:  make sure that all the questions are hit by getting next element

            File[] files = dir.listFiles();
            Arrays.sort(files);

            questions = new String[files.length];

            for (int i = 0; i < files.length; i++) {
                Path path = Paths.get(files[i].getPath());
                byte[] bytes = Files.readAllBytes(path);
                String queryTxt = new String(bytes);
                questions[i] = queryTxt;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String query(int idx) {
        return hub.executeQuery(idx);
    }

    private void setupUI() {
        int windowWidth = 800;
        int windowHeight = 900;
        int margin = 20;

        JList questionList = new JList<>(questions);
        questionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        questionList.setVisibleRowCount(0);
        questionList.setSelectedIndex(0);
        questionList.setToolTipText("Queries");

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(questionList);
        scrollPane.setBounds(margin, margin, windowWidth - margin * 2, 350);

        JButton executeButton = new JButton();
        executeButton.setText("Execute");
        executeButton.setBounds(350, 400, 100, 40);

        JTextArea resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        JScrollPane resultPane = new JScrollPane();
        resultPane.setViewportView(resultTextArea);
        resultPane.setBackground(Color.black);
        resultTextArea.setBackground(Color.lightGray);
        resultPane.setBounds(margin, 400 + margin * 2, windowWidth - margin * 2, windowHeight - 400 - margin * 4);

        executeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int idx = questionList.getSelectedIndex();
                String resultTxt = query(idx);
                resultTextArea.setText(resultTxt);
            }
        });

        this.setTitle("**G-DIEP : Execute Queries**");
        this.getContentPane().setLayout(null);
        this.setBounds(100, 100, windowWidth, windowHeight);
        this.setResizable(false);
        this.add(scrollPane);
        this.add(executeButton);
        this.add(resultPane);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(false);


    }
}
